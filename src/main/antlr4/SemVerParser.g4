parser grammar SemVerParser;

@header {
    package io.github.freiheitstools.semver.parser.internal;
}

options {
    tokenVocab = SemVerLexer;
}

semantic_version_number
    : core
    | core HYPHEN compound_pre_release_identifier
    ;

compound_pre_release_identifier
    : pre_release_identifier ( DOT pre_release_identifier)*
    ;

pre_release_identifier
    : LETTER
    ;

core
    : major DOT minor DOT patch
    ;

major
    : numberic_identifier
    ;

minor
    : numberic_identifier
    ;

patch
    : numberic_identifier
    ;


numberic_identifier
    : VERSION_NUMBER_FRAGMENT
    ;


