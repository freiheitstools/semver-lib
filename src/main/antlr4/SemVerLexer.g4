lexer grammar SemVerLexer;

@header {
    package io.github.freiheitstools.semver.parser.internal;
}


HYPHEN
    : '-'
    ;

DOT
    : '.'
    ;

PLUS
    : '+'
    ;

LETTER
    : [a-zA-Z]
    ;

fragment ZERO_DIGIT
    : '0'
    ;

fragment POSITIVE_DIGIT
    : [1-9]
    ;

fragment DIGIT
    : [0-9]
    ;

VERSION_NUMBER_FRAGMENT
    : ZERO_DIGIT
    | POSITIVE_DIGIT DIGIT*
    ;


