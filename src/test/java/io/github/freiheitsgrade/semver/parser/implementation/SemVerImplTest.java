package io.github.freiheitsgrade.semver.parser.implementation;

import io.github.freiheitsgrade.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitsgrade.semver.parser.api.SemanticVersionNumberElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SemVerImplTest {
    SemVerImpl classUnderTest = new SemVerImpl();

    @Test
    void inCaseOfInvalidSemanticVersionEachGetterThrowsAnException() {
        classUnderTest.setSemanticVersion("x.1.1");
        classUnderTest.setErrorLocation(SemanticVersionNumberElement.MAJOR_VERSION);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> classUnderTest.getMajor())
                  .isExactlyInstanceOf(InvalidSemanticVersionException.class);

            softly.assertThatThrownBy(() -> classUnderTest.getMinor())
                  .isExactlyInstanceOf(InvalidSemanticVersionException.class);

            softly.assertThatThrownBy(() -> classUnderTest.getPatch())
                      .isExactlyInstanceOf(InvalidSemanticVersionException.class);

            softly.assertThatThrownBy(() -> classUnderTest.getPreRelease())
                  .isExactlyInstanceOf(InvalidSemanticVersionException.class);

            softly.assertThatThrownBy(() -> classUnderTest.getBuild())
                  .isExactlyInstanceOf(InvalidSemanticVersionException.class);
        });
    }

    @Test
    void setErrorLocationIsReturnedByGetter() {
        classUnderTest.setErrorLocation(SemanticVersionNumberElement.MAJOR_VERSION);

        assertThat(classUnderTest.getErrorLocation()).hasValue(SemanticVersionNumberElement.MAJOR_VERSION);
    }
}
