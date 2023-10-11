# json-parser
A simple java project written to parse JSON text. 

So far works looks something like below:
```commandline
fileContent = {
    "key1" : "value1",
    "key2" : "value2"
    "key3" : {
        "key31" : "value31"
    }
    "key4" : [123, 345.78, 13.45e90]
}

Lexeme {tokenType=LEFT_BRACE, value={}
Lexeme {tokenType=STRING, value="key1"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=STRING, value="value1"}
Lexeme {tokenType=COMMA, value=,}
Lexeme {tokenType=STRING, value="key2"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=STRING, value="value2"}
Lexeme {tokenType=STRING, value="key3"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=LEFT_BRACE, value={}
Lexeme {tokenType=STRING, value="key31"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=STRING, value="value31"}
Lexeme {tokenType=RIGHT_BRACE, value=}}
Lexeme {tokenType=STRING, value="key4"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=LEFT_SQUARE_BRACKET, value=[}
Lexeme {tokenType=NUMBER, value=123}
Lexeme {tokenType=COMMA, value=,}
Lexeme {tokenType=NUMBER, value=345.78}
Lexeme {tokenType=COMMA, value=,}
Lexeme {tokenType=NUMBER, value=13.45e90}
Lexeme {tokenType=RIGHT_SQUARE_BRACKET, value=]}
Lexeme {tokenType=COMMA, value=,}
Lexeme {tokenType=STRING, value="key5"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=BOOLEAN, value=true}
Lexeme {tokenType=STRING, value="key6"}
Lexeme {tokenType=COLON, value=:}
Lexeme {tokenType=BOOLEAN, value=false}
Lexeme {tokenType=RIGHT_BRACE, value=}}
```

## TODO
1. Divide the components into two and work on them
   1. Lexer
   2. Parser
2. In Lexer, we need to tokenize everything from the text and generate errors if 
any character outside of JSON alphabet is found
3. In Parser, we will try to build the parse tree (this is tentative and is not finalized yet)
and if we are able to build one, then the given text follows JSON constraints

Use the below to write Automata for tokenizing the input into corresponding Token:
https://www.json.org/json-en.html