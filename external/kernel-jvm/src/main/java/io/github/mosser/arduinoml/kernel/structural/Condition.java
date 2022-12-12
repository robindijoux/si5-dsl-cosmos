package io.github.mosser.arduinoml.kernel.structural;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;

public abstract class Condition implements Visitable {

    public abstract void accept(Visitor visitor);


}
