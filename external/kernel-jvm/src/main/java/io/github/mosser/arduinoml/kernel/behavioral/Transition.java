package io.github.mosser.arduinoml.kernel.behavioral;

import java.util.HashMap;
import java.util.Map;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.*;

public class Transition implements Visitable {

	private State next;


    Condition condition;


	public Transition() {
	}


	public Transition(State next, Condition condition) {
		this.next = next;
		this.condition = condition;
	}


	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
