package io.github.mosser.arduinoml.externals.antlr;
import io.github.mosser.arduinoml.externals.antlr.grammar.*;

import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.Action;
import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.behavioral.Timer;
import io.github.mosser.arduinoml.kernel.behavioral.Transition;
import io.github.mosser.arduinoml.kernel.structural.Actuator;
import io.github.mosser.arduinoml.kernel.structural.SIGNAL;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ModelBuilder extends ArduinomlBaseListener {

    /********************
     ** Business Logic **
     ********************/

    private App theApp = null;
    private boolean built = false;

    private ErrorHandler errorHandler = new ErrorHandler();

    public App retrieve() {
        if (built) {
            return theApp;
        }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    /*******************
     ** Symbol tables **
     *******************/

    private Map<String, Sensor> sensors = new HashMap<>();
    private Map<String, Actuator> actuators = new HashMap<>();
    private Map<String, State> states = new HashMap<>();
    private Map<String, Binding> bindings = new HashMap<>();
    private Map<String, TimerBinding> timerByState = new HashMap<>();

    private class Binding { // used to support state resolution for transitions
        String to; // name of the next state, as its instance might not have been compiled yet
        Map<Sensor, SIGNAL> sensorsAndSignals = new HashMap<>();
        String operator;
    }

    private class TimerBinding { // used to support timer
        String to; // the next state
        Integer timerValue; // the timer value to wait for before going to next state
    }

    private State currentState = null;

    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(ArduinomlParser.RootContext ctx) {
        built = false;
        theApp = new App();
    }

    @Override
    public void exitRoot(ArduinomlParser.RootContext ctx) {
        // Resolving states in transitions
        bindings.forEach((key, binding) -> {
            Transition t = new Transition();
            Map<Sensor, SIGNAL> m = binding.sensorsAndSignals;
            for (Entry<Sensor, SIGNAL> e : m.entrySet()) {
                t.addSensorAndSignal(e.getKey(), e.getValue());
                t.setOperator(binding.operator);
            }
            t.setNext(states.get(binding.to));
            states.get(key).setTransition(t);
        });

        timerByState.forEach((key, timerBinding) -> {
            Timer t = new Timer();
            t.setNext(states.get(timerBinding.to));
            t.setTimer(timerBinding.timerValue);
            states.get(key).setTimer(t);
        });
        this.built = true;
    }

    @Override
    public void enterDeclaration(ArduinomlParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }

  @Override
    public void enterSensor(ArduinomlParser.SensorContext ctx) throws PinException {
        Sensor sensor = new Sensor();
        sensor.setName(ctx.location().id.getText());
        int pin = Integer.parseInt(ctx.location().port.getText()) ;
        errorHandler.checkPinDuplication(pin);
        sensor.setPin(pin);
        this.theApp.getBricks().add(sensor);
        sensors.put(sensor.getName(), sensor);
    }

    @Override
    public void enterActuator(ArduinomlParser.ActuatorContext ctx) {
        Actuator actuator = new Actuator();
        actuator.setName(ctx.location().id.getText());
        actuator.setPin(Integer.parseInt(ctx.location().port.getText()));
        this.theApp.getBricks().add(actuator);
        actuators.put(actuator.getName(), actuator);
    }

    @Override
    public void enterState(ArduinomlParser.StateContext ctx) {
        State local = new State();
        local.setName(ctx.name.getText());

        this.currentState = local;
        this.states.put(local.getName(), local);
    }

    @Override
    public void exitState(ArduinomlParser.StateContext ctx) {
        this.theApp.getStates().add(this.currentState);
        this.currentState = null;
    }

    @Override
    public void enterAction(ArduinomlParser.ActionContext ctx) {
        Action action = new Action();
        action.setActuator(actuators.get(ctx.receiver.getText()));
        action.setValue(SIGNAL.valueOf(ctx.value.getText()));
        currentState.getActions().add(action);
    }

    @Override
    public void enterTransition(ArduinomlParser.TransitionContext ctx) {
        // Creating a placeholder as the next state might not have been compiled yet.
        Binding toBeResolvedLater = new Binding();
        if (ctx.operator != null) {
            toBeResolvedLater.operator = ctx.operator.getText();
        }
        toBeResolvedLater.to = ctx.next.getText();
        for (int i = 0; i < ctx.triggers.size(); i++) {
            toBeResolvedLater.sensorsAndSignals.put(sensors.get(ctx.triggers.get(i).getText()),
                    SIGNAL.valueOf(ctx.values.get(i).getText()));
        }
        bindings.put(currentState.getName(), toBeResolvedLater);
    }

    @Override
    public void enterInitial(ArduinomlParser.InitialContext ctx) {
        this.theApp.setInitial(this.currentState);
    }

    @Override
    public void enterTimer(ArduinomlParser.TimerContext ctx) {
        // TODO
        TimerBinding timerBinding = new TimerBinding();
        timerBinding.to = ctx.next.getText();
        timerBinding.timerValue = Integer.parseInt(ctx.timerValue.getText().split("ms")[0]);
        timerByState.put(currentState.getName(), timerBinding);
    }

}
