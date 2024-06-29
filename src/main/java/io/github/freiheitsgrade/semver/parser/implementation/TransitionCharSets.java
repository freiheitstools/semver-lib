package io.github.freiheitsgrade.semver.parser.implementation;

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
}
