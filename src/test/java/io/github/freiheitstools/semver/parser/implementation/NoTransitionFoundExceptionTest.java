package io.github.freiheitstools.semver.parser.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NoTransitionFoundExceptionTest {

    @Test
    void exceptionReportsAHelpfullErrorMessage() {
        // -- Given
        var classUnderTest = new NoTransitionFoundException(State.S00_START, '0');

        // -- Then
        assertThat(classUnderTest).hasMessage("No transition found for current state 'S00_START' and input '0'");
    }

    @Test
    void exceptionReturnsTheGivenState() {
        // -- Given
        var classUnderTest = new NoTransitionFoundException(State.S00_START, '0');

        // -- Then
        assertThat(classUnderTest.getState()).isEqualTo(State.S00_START);
    }
}
