__author__ = 'pascalpoizat'
from App import App
from Action import Action
from Actuator import Actuator
from Sensor import Sensor
from State import State
from Transition import Transition
from SIGNAL import HIGH, LOW

"""
no DSL version of the demo application
"""


def demo():

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


if __name__ == '__main__':
    demo()
