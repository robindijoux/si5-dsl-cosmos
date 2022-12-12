package io.github.mosser.arduinoml.kernel.generator;

import java.util.ArrayList;
import java.util.Map.Entry;

import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.*;
import io.github.mosser.arduinoml.kernel.structural.*;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {
	enum PASS {
		ONE, TWO
	}

	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s", s));
	}

	@Override
	public void visit(App app) {
		// first pass, create global vars
		context.put("pass", PASS.ONE);
		w("// Wiring code generated from an ArduinoML model\n");
		w(String.format("// Application name: %s\n", app.getName()) + "\n");

		w("long debounce = 200;\n");
		w("\nenum STATE {");
		String sep = "";
		for (State state : app.getStates()) {
			w(sep);
			state.accept(this);
			sep = ", ";
		}
		w("};\n");
		if (app.getInitial() != null) {
			w("STATE currentState = " + app.getInitial().getName() + ";\n");
		}

		for (Brick brick : app.getBricks()) {
			brick.accept(this);
		}

		// second pass, setup and loop
		context.put("pass", PASS.TWO);
		w("\nvoid setup(){\n");
		for (Brick brick : app.getBricks()) {
			brick.accept(this);
		}
		w("}\n");

		w("\nvoid loop() {\n" +
				"\tswitch(currentState){\n");
		for (State state : app.getStates()) {
			state.accept(this);
		}
		w("\t}\n" +
				"}");
	}

	@Override
	public void visit(Actuator actuator) {
		if (context.get("pass") == PASS.ONE) {
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			w(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]\n", actuator.getPin(), actuator.getName()));
			return;
		}
	}

	@Override
	public void visit(Sensor sensor) {
		if (context.get("pass") == PASS.ONE) {
			w(String.format("\nboolean %sBounceGuard = false;\n", sensor.getName()));
			w(String.format("long %sLastDebounceTime = 0;\n", sensor.getName()));
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			w(String.format("  pinMode(%d, INPUT);  // %s [Sensor]\n", sensor.getPin(), sensor.getName()));
			return;
		}
	}





	@Override
	public void visit(State state) {
		if (context.get("pass") == PASS.ONE) {
			w(state.getName());
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			w("\t\tcase " + state.getName() + ":\n");
			for (Action action : state.getActions()) {
				action.accept(this);
			}

			if (state.getTransition() != null) {
				for(Transition t : state.getTransition()){
					t.accept(this);
					w("\t\tbreak;\n");
				}
			}

			if (state.getTimer() != null) {
				state.getTimer().accept(this);
			}
			return;
		}

	}

	// @Override
	// public void visit(Transition transition) {
	// if (context.get("pass") == PASS.ONE) {
	// return;
	// }
	// if (context.get("pass") == PASS.TWO) {
	// String sensorName = transition.getSensor().getName();
	// w(String.format("\t\t\t%sBounceGuard = millis() - %sLastDebounceTime >
	// debounce;\n",
	// sensorName, sensorName));
	// w(String.format("\t\t\tif( digitalRead(%d) == %s && %sBounceGuard) {\n",
	// transition.getSensor().getPin(), transition.getValue(), sensorName));
	// w(String.format("\t\t\t\t%sLastDebounceTime = millis();\n", sensorName));
	// w("\t\t\t\tcurrentState = " + transition.getNext().getName() + ";\n");
	// w("\t\t\t}\n");
	// return;
	// }
	// }

	@Override
	public void visit(AtomicCondition condition) {
		w(String.format("\t\t\t%sBounceGuard = millis() - %sLastDebounceTime > debounce;\n",
				condition.getSensor().getName(), condition.getSensor().getName()));
		w("\t\t\tif( ");
		w(String.format("digitalRead(%d) == %s && %sBounceGuard",
				condition.getSensor().getPin(), condition.getSignal().name(), condition.getSensor().getName()));
		w(") {\n");
		String sensorName = condition.getSensor().getName();
		w(String.format("\t\t\t\t%sLastDebounceTime = millis();\n", sensorName));
	}

	@Override
	public void visit(BinaryCondition condition) {

	}
	/*
	@Override
	public void visit(Transition transition) {
		if (context.get("pass") == PASS.ONE) {
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			ArrayList<Entry<Sensor, SIGNAL>> sensorsAndSignals = new ArrayList<>(
					transition.getSensorAndSignal().entrySet());
			for (int i = 0; i < sensorsAndSignals.size(); i++) {
				Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
				String sensorName = e.getKey().getName();

				w(String.format("\t\t\t%sBounceGuard = millis() - %sLastDebounceTime > debounce;\n",
						sensorName, sensorName));
			}
			w("\t\t\tif( ");
			if (transition.getOperator() == null) {
				for (int i = 0; i < sensorsAndSignals.size(); i++) {
					Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
					String sensorName = e.getKey().getName();
					w(String.format("digitalRead(%d) == %s && %sBounceGuard",
							e.getKey().getPin(), e.getValue(), sensorName));
				}
			} else {
				if (transition.getOperator()== OPERATOR.AND) {
					for (int i = 0; i < sensorsAndSignals.size(); i++) {
						Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
						String sensorName = e.getKey().getName();
						if (i > 0) {
							w(" && ");
						}
						w(String.format("digitalRead(%d) == %s && %sBounceGuard",
								e.getKey().getPin(), e.getValue(), sensorName));
					}
				} else if (transition.getOperator()== OPERATOR.OR) {
					for (int i = 0; i < sensorsAndSignals.size(); i++) {
						Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
						String sensorName = e.getKey().getName();
						if (i > 0) {
							w("||");
						}
						w(String.format("digitalRead(%d) == %s && %sBounceGuard",
								e.getKey().getPin(), e.getValue(), sensorName));
					}
				}
			}
			w(") {\n");
			for (int i = 0; i < sensorsAndSignals.size(); i++) {
				Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
				String sensorName = e.getKey().getName();
				w(String.format("\t\t\t\t%sLastDebounceTime = millis();\n", sensorName));
			}
			w("\t\t\t\tcurrentState = " + transition.getNext().getName() + ";\n");
			w("\t\t\t}\n");
			return;
		}
	}
	 */

	@Override
	public void visit(Transition transition) {
		if (context.get("pass") == PASS.ONE) {
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			transition.getCondition().accept(this);
			w("\t\t\t\tcurrentState = " + transition.getNext().getName() + ";\n");
			w("\t\t\t}\n");
		/*		if (transition.getOperator()== OPERATOR.AND) {
					for (int i = 0; i < sensorsAndSignals.size(); i++) {
						Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
						String sensorName = e.getKey().getName();
						if (i > 0) {
							w(" && ");
						}
						w(String.format("digitalRead(%d) == %s && %sBounceGuard",
								e.getKey().getPin(), e.getValue(), sensorName));
					}
				}
			}
			w(") {\n");
			for (int i = 0; i < sensorsAndSignals.size(); i++) {
				Entry<Sensor, SIGNAL> e = sensorsAndSignals.get(i);
				String sensorName = e.getKey().getName();
				w(String.format("\t\t\t\t%sLastDebounceTime = millis();\n", sensorName));
			}

		 */
			return;
		}
	}







	@Override
	public void visit(Action action) {
		if (context.get("pass") == PASS.ONE) {
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			w(String.format("\t\t\tdigitalWrite(%d,%s);\n", action.getActuator().getPin(), action.getValue()));
			return;
		}
	}

	@Override
	public void visit(Timer timer) {
		if (context.get("pass") == PASS.ONE) {
			return;
		}
		if (context.get("pass") == PASS.TWO) {
			w(String.format("\t\t\tdelay(%d);\n", timer.getTimer()));
			w(String.format("\t\t\tcurrentState = %s;\n", timer.getNext().getName()));
			return;
		}
	}

}
