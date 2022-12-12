package io.github.mosser.arduinoml.kernel.structural;

import io.github.mosser.arduinoml.kernel.generator.Visitor;

public class AtomicCondition extends Condition {

    Sensor sensor;
    SIGNAL signal;


    public AtomicCondition(Sensor sensor, SIGNAL signal) {
        this.sensor = sensor;
        this.signal = signal;
    }

    public AtomicCondition() {
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SIGNAL getSignal() {
        return signal;
    }

    public void setSignal(SIGNAL signal) {
        this.signal = signal;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
