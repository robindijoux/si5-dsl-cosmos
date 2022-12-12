import sys
sys.path.append('../..')
from model.Sensor import Sensor
from model.SIGNAL import value
from model.statements.Statement import Statement


class BaseStatement(Statement) :
    def __init__(self, sensor: Sensor, signal):
        self.sensor = sensor
        self.signal = signal
    
    def __str__(self):
        return "digitalRead(%s) == %s" % (self.sensor, value(self.signal))
    
    def __repr__(self):
        return self.__str__()
