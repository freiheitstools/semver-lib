package io.github.freiheitstools.semver.parser.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TerminalNodeReachedExceptionTest {

    @Test
    void exceptionReturnsAHelpfulMessage() {
        // -- Given
        TerminalNodeReachedException classUnderTest = new TerminalNodeReachedException(State.S17_BUILD_AFTER_ALPHANUM);

        // -- Then
        assertThat(classUnderTest).hasMessage("Current state 'S17_BUILD_AFTER_ALPHANUM' is terminal state'");
    }

    @Test
    void exceptionReturnsTheGivenState() {
        // -- Given
        TerminalNodeReachedException classUnderTest = new TerminalNodeReachedException(State.S17_BUILD_AFTER_ALPHANUM);

        // -- Then
        assertThat(classUnderTest.getState()).isEqualTo(State.S17_BUILD_AFTER_ALPHANUM);
    }
}
