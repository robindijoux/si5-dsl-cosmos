grammar test;
/*****************
 ** Lexer rules **
 *****************/

 SALUTATION: 'Hello world';
 ENDSYMBOL : '!';

 /******************
  ** Parser rules **
  ******************/

  expression : SALUTATION ENDSYMBOL;
