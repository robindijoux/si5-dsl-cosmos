grammar Arduinoml;


/******************
 ** Parser rules **
 ******************/

root            :   declaration bricks states EOF;

declaration     :   'application' name=IDENTIFIER;

bricks          :   (sensor|actuator)+;
    sensor      :   'sensor'   location ;
    actuator    :   'actuator' location ;
    location    :   id=IDENTIFIER ':' port=PORT_NUMBER;

states          :   state+;
    state       :   initial? name=IDENTIFIER '{'  action+ transition+ timer?'}';
    action      :   receiver=IDENTIFIER '<=' value=SIGNAL;
    transition  :   triggers+=IDENTIFIER 'is' values+=SIGNAL  (operator = OPERATOR triggers+=IDENTIFIER 'is' values+=SIGNAL)* '=>' next=IDENTIFIER ;
    timer       :   'after' timerValue=INT 'ms =>' next=IDENTIFIER;
    initial     :   '->';

/*****************
 ** Lexer rules **
 *****************/

PORT_NUMBER     :   DIGIT | '11' | '12';
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIGNAL          :   'HIGH' | 'LOW';
OPERATOR        :   'AND' | 'OR';
INT             : DIGIT+ ; // references the DIGIT helper rule

/*************
 ** Helpers **
 *************/

fragment DIGIT      : [1-9] ; // not a token by itself
fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
