package io.github.mosser.arduinoml.kernel.behavioral;

import java.util.HashMap;
import java.util.Map;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.*;

public class Transition implements Visitable {

	private State next;
	private Sensor sensor;
	private SIGNAL value;

	private Map<Sensor, SIGNAL> sensorAndSignal = new HashMap<>();

	public Map<Sensor, SIGNAL> getSensorAndSignal() {
		return sensorAndSignal;
	}

	public void addSensorAndSignal(Sensor sensor, SIGNAL signal) {
		this.sensorAndSignal.put(sensor, signal);
	}

	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public SIGNAL getValue() {
		return value;
	}

	public void setValue(SIGNAL value) {
		this.value = value;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
