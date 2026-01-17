package io.github.freiheitstools.semver.parser.implementation;


/**
 * Set of predefined transition character sets to be used in the parser.
 */
final class TransitionCharSets {
    static final TransitionCharSet PLUS = TransitionCharSet.of(CharacterSets.PLUS);
    static final TransitionCharSet DIGIT = TransitionCharSet.of(CharacterSets.DIGITS);
    static final TransitionCharSet DOT = TransitionCharSet.of(CharacterSets.DOT);
    static final TransitionCharSet POSITIVE_DIGIT = TransitionCharSet.of(CharacterSets.POSITIVE_DIGITS);
    static final TransitionCharSet ZERO_DIGIT = TransitionCharSet.of(CharacterSets.ZERO);
    static final TransitionCharSet HYPHEN = TransitionCharSet.of(CharacterSets.HYPHEN);
    static final TransitionCharSet ALPHA = TransitionCharSet.of(CharacterSets.ALPHA);
    static final TransitionCharSet ALPHANUM = TransitionCharSet.of(CharacterSets.ALPHANUMERIC);
    static final TransitionCharSet END_OF_INPUT = TransitionCharSet.of(CharacterSets.TERMINAL);
    static final TransitionCharSet NON_DIGIT = TransitionCharSet.ofNegationFor(DIGIT);
    static final TransitionCharSet NOT_A_DOT = TransitionCharSet.ofNegationFor(DOT);
    static final TransitionCharSet NEITHER_ALPHA_DOT_PLUS = TransitionCharSet.ofNegationFor(ALPHA, DOT, PLUS);
    static final TransitionCharSet NEITHER_DOT_DIGIT = TransitionCharSet.ofNegationFor(DOT, DIGIT);
    static final TransitionCharSet NEITHER_DIGIT_ALPHA_HYPHEN_DOT_PLUS = TransitionCharSet.ofNegationFor(DIGIT, ALPHA, HYPHEN, DOT, PLUS);
    static final TransitionCharSet NEITHER_ALPHA_ZERO_DIGIT = TransitionCharSet.ofNegationFor(ALPHA, ZERO_DIGIT, POSITIVE_DIGIT);
    static final TransitionCharSet NEITHER_ALPHANUM_DOT = TransitionCharSet.ofNegationFor(ALPHANUM, DOT);
    static final TransitionCharSet NO_ALPHANUM = TransitionCharSet.ofNegationFor(ALPHANUM);
    static final TransitionCharSet NO_ZERO_ALPHA_EOI = TransitionCharSet.ofNegationFor(ZERO_DIGIT, ALPHA, END_OF_INPUT);
}
