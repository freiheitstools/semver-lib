package io.github.freiheitstools.semver.parser.implementation;

import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.TERMINAL_SIGNAL;
import static io.github.freiheitstools.semver.parser.implementation.State.END_OF_SEMVER;
import static io.github.freiheitstools.semver.parser.implementation.State.S00_START;
import static io.github.freiheitstools.semver.parser.implementation.State.S01_MAJOR_STARTS_WITH_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S03_DOT_AFTER_MAJOR_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.State.S05_MINOR_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S06_DOT_AFTER_MINOR_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.State.S07_PATCH_STARTS_WITH_POSITIVE_DIGIT;
import static org.assertj.core.api.Assertions.assertThat;

class SemVerFiniteStateTransitionTableTest {
    SemVerFiniteStateTransitionTable classUnderTest = new SemVerFiniteStateTransitionTable();
    StateToLocationMapping errorLocationMapping = new StateToLocationMapping();


    @Test
    void semanticVersionNumberOneDotTwoDotThreeWillBeRecognizedCorrectly() throws Throwable {
        classUnderTest.accept('1');
        classUnderTest.accept('.');
        classUnderTest.accept('2');
        classUnderTest.accept('.');
        classUnderTest.accept('3');
        classUnderTest.accept(TERMINAL_SIGNAL);

        List<State> expectedStateSequence = List.of(
                S00_START, S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT, S03_DOT_AFTER_MAJOR_NUMBER, S05_MINOR_STARTS_WITH_POSITIVE_DIGIT, S06_DOT_AFTER_MINOR_NUMBER, S07_PATCH_STARTS_WITH_POSITIVE_DIGIT, END_OF_SEMVER

        );

        assertThat(classUnderTest.getReachedStates()).containsExactlyElementsOf(expectedStateSequence);
    }


    @Test
    void semanticVersionNumberZeroDotTwoDotThreeWillBeRecognizedCorrectly() throws Throwable {
        classUnderTest.accept('0');
        classUnderTest.accept('.');
        classUnderTest.accept('2');
        classUnderTest.accept('.');
        classUnderTest.accept('3');
        classUnderTest.accept(TERMINAL_SIGNAL);

        List<State> expectedStateSequence = List.of(
                S00_START, S01_MAJOR_STARTS_WITH_ZERO, S03_DOT_AFTER_MAJOR_NUMBER, S05_MINOR_STARTS_WITH_POSITIVE_DIGIT, S06_DOT_AFTER_MINOR_NUMBER, S07_PATCH_STARTS_WITH_POSITIVE_DIGIT, END_OF_SEMVER


        );

        assertThat(classUnderTest.getReachedStates()).containsExactlyElementsOf(expectedStateSequence);
    }


    @CsvFileSource(resources = "/dataset/semantic-versions-valid-only-core.csv", numLinesToSkip = 1)
    @ParameterizedTest
    void validSemanticVersionNumbersWithoutPreReleaseAndBuildAreParsedProperly(String givenSemanticVersion) throws Throwable {

        char[] input = new char[givenSemanticVersion.length() + 1];
        System.arraycopy(givenSemanticVersion.toCharArray(), 0, input, 0, givenSemanticVersion.length());
        input[givenSemanticVersion.length()] = TERMINAL_SIGNAL;

        for (char c : input) {
            classUnderTest.accept(c);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset/semantic-versions-invalid.csv", useHeadersInDisplayName = true)
    void invalidSemanticVersionNumbersAreRecognized(String givenSemanticVersion, SemanticVersionNumberElement expectedErrorLocation) throws Throwable {
        String usedSemanticVersion = givenSemanticVersion == null ? "" : givenSemanticVersion;
        char[] input = new char[usedSemanticVersion.length() + 1];
        System.arraycopy(usedSemanticVersion.toCharArray(), 0, input, 0, usedSemanticVersion.length());
        input[usedSemanticVersion.length()] = TERMINAL_SIGNAL;

        try {
            for (char c : input) {
                classUnderTest.accept(c);
            }
        } catch (TerminalNodeReachedException e) {
            // Ignore
        }

        State finalState = classUnderTest.getReachedStates().getLast();

        assertThat(finalState.isErrorState())
                .as("State %s is not an final error state", finalState)
                .isTrue();

        SemanticVersionNumberElement errorLocation = errorLocationMapping.getLocationByState(finalState);

        assertThat(errorLocation).isEqualTo(expectedErrorLocation);
    }

}
