from model.statements.Statement import Statement

class TemporalStatement(Statement) :
    def __init__(self, time: int):
        self.time = time
    
    def __str__(self):
        return "(millis() - time > %s)" % (self.time)
    
    def __repr__(self):
        return self.__str__()

