package io.github.freiheitstools.semver.parser.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CharCharTupleTest {
    @Test
    void predecessorAndSuccessorAreSetAndReturnedProperly() {
        CharCharTuple classUnderTest = CharCharTuple.newTuple('a', 'b');

        assertThat(classUnderTest.getPredecessor()).isEqualTo('a');
        assertThat(classUnderTest.getSuccessor()).isEqualTo('b');
    }
}
