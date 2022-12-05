from App import App
from Action import Action
from Actuator import Actuator
from Sensor import Sensor
from State import State
from Transition import Transition
from SIGNAL import HIGH, LOW

def demoScenario1():
    button = Sensor("BUTTON", 9)
    led = Actuator("LED", 12)

    on = State("on", [Action(HIGH, led)])
    off = State("off", [Action(LOW, led)])

    switchon = Transition(button, HIGH, on)
    switchoff = Transition(button, LOW, off)

    on.settransition(switchoff)
    off.settransition(switchon)

    app = App("Switch!", [button, led], [off, on])

    print(app)




def demoScenario3():
    button = Sensor("BUTTON", 9)
    led = Actuator("LED", 12)

    on = State("on", [Action(HIGH, led)])
    off = State("off", [Action(LOW, led)])

    switchon = Transition(button, HIGH, on)
    switchoff = Transition(button, HIGH, off)

    on.settransition(switchoff)
    off.settransition(switchon)

    app = App("Switch!", [button, led], [off, on])

    print(app)


def demoScenario4():
    button = Sensor("BUTTON", 9)
    led = Actuator("LED", 12)
    buzzer = Actuator("BUZZER", 11)

    on = State("on", [Action(HIGH, led), Action(LOW, buzzer)])
    buzzerOn = State("buzzerOn", [Action(HIGH, buzzer)])
    off = State("off", [Action(LOW, led)])

    switchBuzzOn = Transition(button, HIGH, buzzerOn)
    switchon = Transition(button, HIGH, on)
    switchoff = Transition(button, HIGH, off)

    off.settransition(switchBuzzOn)
    buzzerOn.settransition(switchon)
    on.settransition(switchoff)

    app = App("Switch!", [button, led,buzzer], [off, on,buzzerOn])

    print(app)




if __name__ == '__main__':
    demoScenario4()
