package io.github.freiheitstools.semver.parser.implementation;

/**
 * Set of predefined transition character sets to be used in the parser.
 */
final class TransitionCharSets {
    static final TransitionCharSet ALPHA;
    static final TransitionCharSet ALPHANUM;
    static final TransitionCharSet DIGIT;
    static final TransitionCharSet DOT;
    static final TransitionCharSet END_OF_INPUT;
    static final TransitionCharSet HYPHEN;
    static final TransitionCharSet NEITHER_ALPHANUM_DOT;
    static final TransitionCharSet NEITHER_ALPHA_DOT_PLUS;
    static final TransitionCharSet NEITHER_ALPHA_ZERO_DIGIT;
    static final TransitionCharSet NEITHER_DIGIT_ALPHA_HYPHEN_DOT_PLUS;
    static final TransitionCharSet NEITHER_DOT_DIGIT;
    static final TransitionCharSet NON_DIGIT;
    static final TransitionCharSet NOT_A_DOT;
    static final TransitionCharSet NO_ALPHANUM;
    static final TransitionCharSet NO_ZERO_ALPHA_EOI;
    static final TransitionCharSet PLUS;
    static final TransitionCharSet POSITIVE_DIGIT;
    static final TransitionCharSet ZERO_DIGIT;

    static {
        PLUS = TransitionCharSet.of(CharacterSets.PLUS);
        DIGIT = TransitionCharSet.of(CharacterSets.DIGITS);
        DOT = TransitionCharSet.of(CharacterSets.DOT);
        POSITIVE_DIGIT = TransitionCharSet.of(CharacterSets.POSITIVE_DIGITS);
        ZERO_DIGIT = TransitionCharSet.of(CharacterSets.ZERO);
        HYPHEN = TransitionCharSet.of(CharacterSets.HYPHEN);
        ALPHA = TransitionCharSet.of(CharacterSets.ALPHA);
        ALPHANUM = TransitionCharSet.of(CharacterSets.ALPHANUMERIC);
        END_OF_INPUT = TransitionCharSet.of(CharacterSets.TERMINAL);
        NON_DIGIT = TransitionCharSet.ofNegationFor(DIGIT);
        NOT_A_DOT = TransitionCharSet.ofNegationFor(DOT);
        NEITHER_ALPHA_DOT_PLUS = TransitionCharSet.ofNegationFor(ALPHA, DOT, PLUS);
        NEITHER_DOT_DIGIT = TransitionCharSet.ofNegationFor(DOT, DIGIT);
        NEITHER_DIGIT_ALPHA_HYPHEN_DOT_PLUS = TransitionCharSet.ofNegationFor(DIGIT, ALPHA, HYPHEN, DOT, PLUS);
        NEITHER_ALPHA_ZERO_DIGIT = TransitionCharSet.ofNegationFor(ALPHA, ZERO_DIGIT, POSITIVE_DIGIT);
        NEITHER_ALPHANUM_DOT = TransitionCharSet.ofNegationFor(ALPHANUM, DOT);
        NO_ALPHANUM = TransitionCharSet.ofNegationFor(ALPHANUM);
        NO_ZERO_ALPHA_EOI = TransitionCharSet.ofNegationFor(ZERO_DIGIT, ALPHA, END_OF_INPUT);
    }
}
