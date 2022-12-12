package io.github.mosser.arduinoml.kernel.structural;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryCondition extends Condition   {

    OPERATOR operator;

    Condition cond1;

    Condition cond2;

    public BinaryCondition(OPERATOR operator, Condition cond1, Condition cond2) {
        this.operator = operator;
        this.cond1 = cond1;
        this.cond2 = cond2;
    }

    public OPERATOR getOperator() {
        return operator;
    }

    public void setOperator(OPERATOR operator) {
        this.operator = operator;
    }

    public Condition getCond1() {
        return cond1;
    }

    public void setCond1(Condition cond1) {
        this.cond1 = cond1;
    }

    public Condition getCond2() {
        return cond2;
    }

    public void setCond2(Condition cond2) {
        this.cond2 = cond2;
    }

    @Override
    public void accept(Visitor visitor) {
     visitor.visit(this);
    }

    @Override
    public Set<String> getSensorName(Set<String> sensors) {
        sensors.addAll(cond1.getSensorName(new HashSet<>()));
        sensors.addAll(cond2.getSensorName(new HashSet<>()));
        return  sensors;
    }


}
