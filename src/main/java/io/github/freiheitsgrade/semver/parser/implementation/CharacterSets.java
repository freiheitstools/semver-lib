package io.github.freiheitsgrade.semver.parser.implementation;

/**
 * Definition of character groups like alphanumeric characters or digits used in the grammatic for
 * semantic version numbers.
 */
class CharacterSets {
    static char TERMINAL_SIGNAL = 0x0;

    final static String DIGITS = "0123456789";
    final static String DOT = ".";
    final static String HYPHEN = "-";
    final static String POSITIVE_DIGITS = "123456789";
    final static String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + HYPHEN;
    final static String ALPHANUMERIC = ALPHA + HYPHEN  + DIGITS + HYPHEN;
    final static String TERMINAL = "" + TERMINAL_SIGNAL;;
    final static String ZERO = "0";
    static final String PLUS = "+";
}
