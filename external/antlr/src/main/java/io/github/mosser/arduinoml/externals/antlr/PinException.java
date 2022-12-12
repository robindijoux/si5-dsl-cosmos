package io.github.mosser.arduinoml.externals.antlr;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class PinException  extends RuntimeException {
    public PinException(String message){
        super(message);
    }

}
