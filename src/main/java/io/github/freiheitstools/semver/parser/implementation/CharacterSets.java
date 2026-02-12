package io.github.freiheitstools.semver.parser.implementation;

/**
 * Definition of character groups like alphanumeric characters or digits as used
 * in the grammatic for semantic versioning.
 */
final class CharacterSets {
    static final String ALPHA;

    static final String ALPHANUMERIC;
    static final String DIGITS;
    static final String DOT;
    static final String HYPHEN;
    static final String PLUS;
    static final String POSITIVE_DIGITS;
    static final String TERMINAL;
    static final char TERMINAL_SIGNAL;
    static final String ZERO;

    static {
        DIGITS = "0123456789";
        DOT = ".";
        HYPHEN = "-";
        PLUS = "+";
        POSITIVE_DIGITS = "123456789";
        TERMINAL_SIGNAL = 0x0;
        ZERO = "0";

        ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ" + HYPHEN;
        ALPHANUMERIC = ALPHA + DIGITS;
        TERMINAL = "" + TERMINAL_SIGNAL;
    }
}
