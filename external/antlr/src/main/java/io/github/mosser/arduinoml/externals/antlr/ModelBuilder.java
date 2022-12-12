package io.github.mosser.arduinoml.externals.antlr;

import io.github.mosser.arduinoml.externals.antlr.grammar.*;


import io.github.mosser.arduinoml.externals.antlr.validation.ErrorHandler;
import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.Action;
import io.github.mosser.arduinoml.kernel.behavioral.State;
import io.github.mosser.arduinoml.kernel.behavioral.Timer;
import io.github.mosser.arduinoml.kernel.behavioral.Transition;
import io.github.mosser.arduinoml.kernel.structural.*;


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
    private Map<String, TimerBinding> timerByState = new HashMap<>();
    List<Transition> transitions = new ArrayList<>();
    List<String> to = new ArrayList<>();
    private String next = "";

    private class TransitionBinding { // used to support state resolution for transitions
        // name of the next state, as its instance might not have been compiled yet
        Condition condition;
    }

    public static class StateBinding{
    public static List<Transition> transitions = new ArrayList<>();
        public static List<Transition> getTransitions() {
            return transitions;
        }
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
        for(int i = 0 ; i < this.theApp.getStates().size() ; i++){
            for(int j = 0 ; j < this.theApp.getStates().get(i).getTransition().size(); j++){
                this.theApp.getStates().get(i).getTransition().get(j).setNext(states.get(to.get(0)));
                to.remove(0);
            }
        }
        timerByState.forEach((key, timerBinding) -> {
            Timer t = new Timer();
            t.setNext(states.get(timerBinding.to));
            t.setTimer(timerBinding.timerValue);
            states.get(key).setTimer(t);
        });
        this.built = true;

        errorHandler.checkInitialState(theApp);
    }

    @Override
    public void enterDeclaration(ArduinomlParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }


  @Override
    public void enterSensor(ArduinomlParser.SensorContext ctx)  {
        errorHandler.checkVariableDuplication(ctx.location().id.getText());
        Sensor sensor = new Sensor();
        sensor.setName(ctx.location().id.getText());
        sensor.setPin(Integer.parseInt(ctx.location().port.getText()));
        this.theApp.getBricks().add(sensor);
        sensors.put(sensor.getName(), sensor);
    }

    @Override
    public void enterActuator(ArduinomlParser.ActuatorContext ctx) {
        errorHandler.checkVariableDuplication(ctx.location().id.getText());
        Actuator actuator = new Actuator();
        actuator.setName(ctx.location().id.getText());
        actuator.setPin(Integer.parseInt(ctx.location().port.getText()));
        this.theApp.getBricks().add(actuator);
        actuators.put(actuator.getName(), actuator);
    }

    @Override
    public void enterState(ArduinomlParser.StateContext ctx) {
        errorHandler.checkVariableDuplication(ctx.name.getText());
        State local = new State();
        local.setName(ctx.name.getText());
        transitions = new ArrayList<>();
        this.currentState = local;
        this.states.put(local.getName(), local);
    }

    @Override
    public void exitState(ArduinomlParser.StateContext ctx) {
        this.currentState.setTransition(transitions);
        this.theApp.getStates().add(this.currentState);
        this.currentState = null;
    }

    @Override
    public void enterTransition(ArduinomlParser.TransitionContext ctx) {
        // Creating a placeholder as the next state might not have been compiled yet.
        TransitionBinding toBeResolvedLater = new TransitionBinding();
        to.add(ctx.next.getText());
        toBeResolvedLater.condition = createCondition(ctx.condition());
        Transition transition = new Transition( null , toBeResolvedLater.condition);
        transitions.add(transition);
    }



    @Override
    public void enterAction(ArduinomlParser.ActionContext ctx) {
        Action action = new Action();
        action.setActuator(actuators.get(ctx.receiver.getText()));
        action.setValue(SIGNAL.valueOf(ctx.value.getText()));
        currentState.getActions().add(action);
    }



    public Condition createTransitionCondition(ArduinomlParser.ConditionContext conditionContext){
        Condition cond;
        cond = createCondition(conditionContext);
        return cond;
    }

    public Condition createCondition(ArduinomlParser.ConditionContext conditionContext){
        if(conditionContext.operator == null){
            return  new AtomicCondition( sensors.get(conditionContext.atomicCondition().triger.getText()), SIGNAL.valueOf(conditionContext.atomicCondition().SIGNAL.getText()));
        }else{
            return  new BinaryCondition(OPERATOR.valueOf(conditionContext.operator.getText()) , new AtomicCondition( sensors.get(conditionContext.atomicCondition().triger.getText()), SIGNAL.valueOf(conditionContext.atomicCondition().SIGNAL.getText()))
                    ,createCondition(conditionContext.subCondition));
        }


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
