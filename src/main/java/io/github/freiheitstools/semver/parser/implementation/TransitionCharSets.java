package io.github.freiheitstools.semver.parser.implementation;

import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.ALPHANUMERIC;
import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.DIGITS;
import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.POSITIVE_DIGITS;
import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.TERMINAL;
import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.ZERO;

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
    static final TransitionCharSet NEITHER_EOI_PLUS_ALPHANUM_DOT;
    static final TransitionCharSet NEITHER_HYPHEN_PLUS_EOI;
    static final TransitionCharSet NON_DIGIT;
    static final TransitionCharSet NOT_A_DOT;
    static final TransitionCharSet NO_ALPHANUM;
    static final TransitionCharSet NO_ZERO_ALPHA_EOI;
    static final TransitionCharSet PLUS;
    static final TransitionCharSet POSITIVE_DIGIT;
    static final TransitionCharSet ZERO_DIGIT;

    static {
        ALPHA = TransitionCharSet.of(CharacterSets.ALPHA);
        ALPHANUM = TransitionCharSet.of(ALPHANUMERIC);
        DIGIT = TransitionCharSet.of(DIGITS);
        DOT = TransitionCharSet.of(CharacterSets.DOT);
        END_OF_INPUT = TransitionCharSet.of(TERMINAL);
        HYPHEN = TransitionCharSet.of(CharacterSets.HYPHEN);
        NEITHER_ALPHANUM_DOT = TransitionCharSet.ofNegationFor(ALPHANUM, DOT);
        PLUS = TransitionCharSet.of(CharacterSets.PLUS);
        NEITHER_ALPHA_DOT_PLUS = TransitionCharSet.ofNegationFor(ALPHA, DOT, PLUS);
        ZERO_DIGIT = TransitionCharSet.of(ZERO);
        POSITIVE_DIGIT = TransitionCharSet.of(POSITIVE_DIGITS);
        NEITHER_ALPHA_ZERO_DIGIT = TransitionCharSet.ofNegationFor(ALPHA, ZERO_DIGIT, POSITIVE_DIGIT);
        NEITHER_DIGIT_ALPHA_HYPHEN_DOT_PLUS = TransitionCharSet.ofNegationFor(DIGIT, ALPHA, HYPHEN, DOT, PLUS);
        NEITHER_DOT_DIGIT = TransitionCharSet.ofNegationFor(DOT, DIGIT);
        NEITHER_EOI_PLUS_ALPHANUM_DOT = TransitionCharSet.ofNegationFor(END_OF_INPUT, ALPHANUM, DOT, PLUS);
        NEITHER_HYPHEN_PLUS_EOI = TransitionCharSet.ofNegationFor(HYPHEN, END_OF_INPUT, PLUS);
        NON_DIGIT = TransitionCharSet.ofNegationFor(DIGIT);
        NOT_A_DOT = TransitionCharSet.ofNegationFor(DOT);
        NO_ALPHANUM = TransitionCharSet.ofNegationFor(ALPHANUM);
        NO_ZERO_ALPHA_EOI = TransitionCharSet.ofNegationFor(ZERO_DIGIT, ALPHA, END_OF_INPUT);
    }
}
