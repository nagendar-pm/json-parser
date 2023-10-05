# json-parser
A simple java project written to parse JSON text. 

## TODO
1. Divide the components into two and work on them
   1. Lexer
   2. Parser
2. In Lexer, we need to tokenize everything from the text and generate errors if 
any character outside of JSON alphabet is found
3. In Parser, we will try to build the parse tree (this is tentative and is not finalized yet)
and if we are able to build one, then the given text follows JSON constraints