AND = 0
OR = 1

def value(operator):
    """
    Returns the string representation of an operator.

    :param operator: Operator, the operator
    :return: String, the representation of the operator in the Arduino language
    """
    if operator == AND:
        return "&&"
    if operator == OR:
        return "||"
    return "op"