package io.github.freiheitstools.semver.parser.implementation;

import static io.github.freiheitstools.semver.parser.implementation.State.S00_START;
import static io.github.freiheitstools.semver.parser.implementation.State.S01_MAJOR_STARTS_WITH_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S03_DOT_AFTER_MAJOR_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.TransitionCharSets.DOT;
import static io.github.freiheitstools.semver.parser.implementation.TransitionCharSets.POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.TransitionCharSets.ZERO_DIGIT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FiniteStateTransitionTableTest {
    FiniteStateTransitionTable classUnderTest = new FiniteStateTransitionTable()
            .from(S00_START)
            .to(State.S01_MAJOR_STARTS_WITH_ZERO)
            .when(ZERO_DIGIT)
            .from(S01_MAJOR_STARTS_WITH_ZERO)
            .to(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT)
            .when(POSITIVE_DIGIT)
            .from(S01_MAJOR_STARTS_WITH_ZERO)
            .to(S03_DOT_AFTER_MAJOR_NUMBER)
            .when(DOT);

    @Test
    void existingTransitionIsUsed() throws Throwable {
        classUnderTest.startFrom(S00_START);

        assertThat(classUnderTest.getCurrentState()).isSameAs(S00_START);

        classUnderTest.accept('0');

        assertThat(classUnderTest.getCurrentState()).isSameAs(S01_MAJOR_STARTS_WITH_ZERO);
    }
}
