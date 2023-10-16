# json-parser
A simple java project written to parse JSON text. 

So far works looks something like below:
```commandline
fileContent = {
    "key1" : "value1",
    "key2" : "value2",
    "key3" : {
        "key31" : "value31"
    },
    "key4" : [123, 345.78, 13.45e90, -345.909, +245],
    "key5" : true,
    "key6" : false
}

----Tokens----
Lexeme{tokenType=LEFT_BRACE, value={, start=0, end=0}
Lexeme{tokenType=STRING, value="key1", start=6, end=11}
Lexeme{tokenType=COLON, value=:, start=13, end=13}
Lexeme{tokenType=STRING, value="value1", start=15, end=22}
Lexeme{tokenType=COMMA, value=,, start=23, end=23}
Lexeme{tokenType=STRING, value="key2", start=29, end=34}
Lexeme{tokenType=COLON, value=:, start=36, end=36}
Lexeme{tokenType=STRING, value="value2", start=38, end=45}
Lexeme{tokenType=COMMA, value=,, start=46, end=46}
Lexeme{tokenType=STRING, value="key3", start=52, end=57}
Lexeme{tokenType=COLON, value=:, start=59, end=59}
Lexeme{tokenType=LEFT_BRACE, value={, start=61, end=61}
Lexeme{tokenType=STRING, value="key31", start=71, end=77}
Lexeme{tokenType=COLON, value=:, start=79, end=79}
Lexeme{tokenType=STRING, value="value31", start=81, end=89}
Lexeme{tokenType=RIGHT_BRACE, value=}, start=95, end=95}
Lexeme{tokenType=COMMA, value=,, start=96, end=96}
Lexeme{tokenType=STRING, value="key4", start=102, end=107}
Lexeme{tokenType=COLON, value=:, start=109, end=109}
Lexeme{tokenType=LEFT_SQUARE_BRACKET, value=[, start=111, end=111}
Lexeme{tokenType=NUMBER, value=123, start=112, end=114}
Lexeme{tokenType=COMMA, value=,, start=115, end=115}
Lexeme{tokenType=NUMBER, value=345.78, start=117, end=122}
Lexeme{tokenType=COMMA, value=,, start=123, end=123}
Lexeme{tokenType=NUMBER, value=13.45e90, start=125, end=132}
Lexeme{tokenType=COMMA, value=,, start=133, end=133}
Lexeme{tokenType=NUMBER, value=-345.909, start=135, end=142}
Lexeme{tokenType=COMMA, value=,, start=143, end=143}
Lexeme{tokenType=NUMBER, value=+245, start=145, end=148}
Lexeme{tokenType=RIGHT_SQUARE_BRACKET, value=], start=149, end=149}
Lexeme{tokenType=COMMA, value=,, start=150, end=150}
Lexeme{tokenType=STRING, value="key5", start=156, end=161}
Lexeme{tokenType=COLON, value=:, start=163, end=163}
Lexeme{tokenType=BOOLEAN, value=true, start=165, end=168}
Lexeme{tokenType=COMMA, value=,, start=169, end=169}
Lexeme{tokenType=STRING, value="key6", start=175, end=180}
Lexeme{tokenType=COLON, value=:, start=182, end=182}
Lexeme{tokenType=BOOLEAN, value=false, start=184, end=188}
Lexeme{tokenType=RIGHT_BRACE, value=}, start=190, end=190}

----JSON object----
jsonCorrect = {"key1"=Lexeme{tokenType=STRING, value="value1", start=15, end=22},
"key2"=Lexeme{tokenType=STRING, value="value2", start=38, end=45},
"key3"=Lexeme{tokenType=OBJECT, 
    value={"key31"=Lexeme{tokenType=STRING, value="value31", start=81, end=89}}, start=-1, end=-1}, 
"key4"=Lexeme{tokenType=ARRAY, value=[Lexeme{tokenType=NUMBER, value=+245, start=145, end=148}, 
    Lexeme{tokenType=NUMBER, value=-345.909, start=135, end=142}, 
    Lexeme{tokenType=NUMBER, value=13.45e90, start=125, end=132}, 
    Lexeme{tokenType=NUMBER, value=345.78, start=117, end=122}, 
    Lexeme{tokenType=NUMBER, value=123, start=112, end=114}], start=-1, end=-1}, 
"key5"=Lexeme{tokenType=BOOLEAN, value=true, start=165, end=168}, 
"key6"=Lexeme{tokenType=BOOLEAN, value=false, start=184, end=188}}

----Verdict----
Verdict = Valid JSON
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