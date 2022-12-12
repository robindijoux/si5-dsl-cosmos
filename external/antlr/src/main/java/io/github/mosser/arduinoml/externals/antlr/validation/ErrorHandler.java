package io.github.mosser.arduinoml.externals.antlr.validation;

import io.github.mosser.arduinoml.kernel.App;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {


    private final List<Integer> usedPins  = new ArrayList<>();
    private final List<String> variables  = new ArrayList<>();

    public void checkPinDuplication(int pin) throws PinDuplicationException {
        if (usedPins.contains(pin)) {
            throw new PinDuplicationException(" \n Check Pin Duplication : pin  '"+     pin+"' already assigned! \n");
        }
        else usedPins.add(pin);
    }


    public void checkInitialState(App theApp) {
        if (theApp.getInitial() == null) {
            throw new InitStateException(" \n check Initial State :No initial state was found in the app. \n");
        }
    }

    public void checkVariableDuplication(String variableName) {
        if (variables.contains(variableName)) {
            throw new VariableDuplicationException ("\n check Variable Duplication : '"+variableName+"' already defined! \n");
        }
        else variables.add(variableName);
    }

}
