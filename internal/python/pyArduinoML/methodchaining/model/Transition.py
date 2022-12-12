__author__ = 'pascalpoizat'

class Transition :
    """
    A transition between two states.
    """

    def __init__(self, nextstate, statement):
        self.nextstate = nextstate
        self.statement = statement
