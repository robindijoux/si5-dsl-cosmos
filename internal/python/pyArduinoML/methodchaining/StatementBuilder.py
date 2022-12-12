from model.statements.BaseStatement import BaseStatement
from model.statements.TemporalStatement import TemporalStatement
from model.statements.ComposedStatement import ComposedStatement




class StatementBuilder:
    """
    Builder for expressions.
    """

    def __init__(self, root, sensor=None):
        """
        Constructor.

        :return:
        """
        self.root = root
        self.statement = BaseStatement(sensor, None) if sensor else None

    def having(self, sensor):
        if self.statement is None:
            self.statement = ComposedStatement()
        self.statement.left_statement = BaseStatement(sensor, None)
        return self

    def has_value(self, value):
        if type(self.statement) is BaseStatement:
            self.statement.signal = value
        else: 
            if self.statement.left_statement.signal is None:
                self.statement.left_statement.signal = value
            else:
                self.statement.right_statement.signal = value
        return self
    
    def and_op(self, sensor):
        self.statement.operator = "&&"
        self.statement.right_statement = BaseStatement(sensor, None)
        return self

    def or_op(self, sensor):
        self.statement.operator = "||"
        self.statement.right_statement = BaseStatement(sensor, None)
        return self

    def after(self, time):
        self.statement = TemporalStatement(time)
        return self
                
    def __str__(self) -> str:
        return str(self.statement)

    def __repr__(self) -> str:
        return self.__str__()

    def go_to_state(self, next_state):
        return self.root.go_to_state(next_state)
        
