package io.github.mosser.arduinoml.kernel.structural;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;

import java.util.List;
import java.util.Set;

public abstract class Condition implements Visitable {

    public abstract void accept(Visitor visitor);

    public abstract Set<String> getSensorName(Set<String> sensors);


}
