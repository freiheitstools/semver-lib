package io.github.freiheitstools.semver.parser.implementation;

import java.util.Arrays;
import java.util.stream.Stream;

/*
    todo Der Name muss angepasst werden wegen dem negativen bla bla bla
 */
class TransitionCharSet {
    private char[] validChars;

    public TransitionCharSet(char[] validChars) {
        this.validChars = validChars;
        Arrays.sort(this.validChars);
    }

    static TransitionCharSet of(String validChars) {
        return new TransitionCharSet(validChars.toCharArray());
    }

    public static TransitionCharSet of(char character) {
        return new TransitionCharSet(new char[] {character});
    }

    public static TransitionCharSet ofNegationFor(TransitionCharSet... transitionCharSets) {
        char[] charArray = Stream.of(transitionCharSets).map(TransitionCharSet::getValidChars)
                                 .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                                 .toString()
                                 .toCharArray();

        return new TransitionCharSet(charArray) {
            @Override
            public boolean accepts(char input) {
                return !super.accepts(input);
            }
        };
    }

    public boolean accepts(char input) {
        return Arrays.binarySearch(validChars, input) >= 0;
    }

    private char[] getValidChars() {
        return validChars;
    }
}
