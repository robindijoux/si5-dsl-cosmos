import sys

sys.path.append('../..')
from model.statements.BaseStatement import BaseStatement
from model.statements.Statement import Statement


class ComposedStatement(Statement):
    def __init__(self):
        self.left_statement = BaseStatement(None, None)
        self.right_statement = BaseStatement(None, None)
        self.operator = None

    def __str__(self):
        return "(%s %s %s)" % (self.left_statement, self.operator, self.right_statement)
    
    def __repr__(self):
        return self.__str__()
    