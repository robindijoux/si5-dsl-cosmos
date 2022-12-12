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

states              :   state+;
    state           :   initial? name=IDENTIFIER '{'  action+ (transition+ | timer) '}'; // only one transition xor one timer in a state
    action          :   receiver=IDENTIFIER '<=' value=SIGNAL;
    transition      :   condition   '=>' next=IDENTIFIER;
    condition       :   leaf=atomicCondition (operator=OPERATOR subCondition=condition)?;
    atomicCondition :   triger=IDENTIFIER 'is' value+=SIGNAL;
    timer           :   'after' timerValue=TIMER_VALUE '=>' next=IDENTIFIER;
    initial         :   '->';

/*****************
 ** Lexer rules **
 *****************/
TIMER_VALUE     :   ('0'..'9')* 'ms' ;
PORT_NUMBER     :   DIGIT | '11' | '12';
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIGNAL          :   'HIGH' | 'LOW';
OPERATOR        :   'AND';

/*************
 ** Helpers **
 *************/

fragment DIGIT      : [1-9] ; // not a token by itself
fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #