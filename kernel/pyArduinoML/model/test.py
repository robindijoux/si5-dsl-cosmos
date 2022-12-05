from pyparsing import *

LBRACE,RBRACE,EQ,SEMI = map(Suppress, "{}=;")
name = Word(printables, excludeChars="{}=;")
expr = Word(printables, excludeChars="{}=;") | quotedString

leaf = Group(name + EQ + expr + SEMI)
group = Group(LBRACE + ZeroOrMore(leaf) + RBRACE) | leaf
tree = OneOrMore(group)

sample = """
{
 stage = 3;
 some.param1 = [10,20];
}
{
 stage = 4;
 param3 = [100,150,200,250,300];
}
 endparam = [0,1];
 """

parsed = tree.parseString(sample)
parsed.pprint()