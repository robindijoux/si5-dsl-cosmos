package io.github.mosser.arduinoml.externals.antlr;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {


    private final List<Integer> usedPins  = new ArrayList<>();

    void checkPinDuplication(int pin) throws PinException {
        if (usedPins.contains(pin)) {
            throw new PinException(" \n pin : "+     pin+" already assigned! \n");
        }
        else usedPins.add(pin);
    }


}
