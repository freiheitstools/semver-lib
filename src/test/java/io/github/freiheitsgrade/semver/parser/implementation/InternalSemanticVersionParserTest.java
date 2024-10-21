package io.github.freiheitsgrade.semver.parser.implementation;

import io.github.freiheitsgrade.semver.parser.api.SemVer;
import io.github.freiheitsgrade.semver.parser.api.SemanticVersionNumberElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.github.freiheitsgrade.semver.parser.implementation.SemVerRegex.SEM_VER_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class InternalSemanticVersionParserTest {
    InternalSemanticVersionParser classUnderTest = new InternalSemanticVersionParser();

    // todo Test if all given valid semvers are valid and result matches regex!

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset/semantic-versions-invalid.csv", useHeadersInDisplayName = true)
    void invalidSemanticVersionNumersAreRecognized(String given, SemanticVersionNumberElement expectedErrorLocation) {
        SemVer result = classUnderTest.parse(given);

        assertSoftly(softly -> {
            softly.assertThat(result.isValid())
                  .describedAs("Semantic version '%s' should be taken as invalid", given)
                  .isFalse();
            softly.assertThat(result.getErrorLocation()).hasValue(expectedErrorLocation);
        });
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/dataset/semantic-versions-valid.csv", useHeadersInDisplayName = true)
    void validSemanticVersioNumbersAreParsedProperly(String givenSemVer) {
        assertThat(givenSemVer)
            .describedAs("Semantic version %s should match regular expression by semver.org", givenSemVer)
            .matches(SEM_VER_PATTERN);

        SemVer result = classUnderTest.parse(givenSemVer);

        assertThat(result.isValid())
            .describedAs("Semantic version %s should be taken as valid", givenSemVer)
            .isTrue();
    }


    @CsvFileSource(resources = "/dataset/semantic-versions-invalid-with-prerelease.csv", useHeadersInDisplayName = true)
    @ParameterizedTest(name = ">{0}<")
    void invalidSemanticVersionNumbersWithPreReleaseAreRecognized(String givenSemVer) {
        assertThat(givenSemVer)
            .describedAs("Semantic version %s should match regular expression by semver.org", givenSemVer)
            .doesNotMatch(SEM_VER_PATTERN);

        SemVer result = classUnderTest.parse(givenSemVer);

        assertSoftly(softly -> {
            softly.assertThat(result.isValid()).isFalse();
            softly.assertThat(result.getErrorLocation()).isPresent()
                  .hasValue(SemanticVersionNumberElement.PRERELEASE_VERSION);
        });
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/dataset/semverspectestdata/valid.csv", useHeadersInDisplayName = true)
    void officialValidExamplesFromSemVerOrgAreRecognizedAsValid(String givenSemVer) {
        assertThat(givenSemVer)
            .describedAs("Semantic version %s should match regular expression by semver.org", givenSemVer)
            .matches(SEM_VER_PATTERN);

        SemVer result = classUnderTest.parse(givenSemVer);

        assertThat(result.isValid())
            .describedAs("Semantic version %s should be taken as valid", givenSemVer)
            .isTrue();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset/semverspectestdata/invalid.csv", useHeadersInDisplayName = true)
    void officialInvalidExamplesFromSemVerOrgAreRecognizedAsInvalid(String givenSemVer) {
        assertThat(givenSemVer)
            .describedAs("Semantic version %s should match regular expression by semver.org", givenSemVer)
            .doesNotMatch(SEM_VER_PATTERN);

        SemVer result = classUnderTest.parse(givenSemVer);

        assertThat(result.isValid())
            .describedAs("Semantic version %s should be taken as invalid", givenSemVer)
            .isFalse();
    }

    @CsvFileSource(resources = "/dataset/semantic-versions-valid-only-core.csv", useHeadersInDisplayName = true)
    @ParameterizedTest
    void validSemanticVersionNumbersWithoutPreReleaseAndBuildAreParsedProperly(String givenSemanticVersion,
                                                                               Integer expectedMajorNumber,
                                                                               Integer expectedMinorNumber,
                                                                               Integer expectedPatchNumber) {
        SemVer result = classUnderTest.parse(givenSemanticVersion);

        assertSoftly(softly -> {
            softly.assertThat(result.isValid()).isTrue();
            softly.assertThat(result.getErrorLocation()).isEmpty();
            softly.assertThat(result.getMajor()).isEqualTo(expectedMajorNumber);
            softly.assertThat(result.getMinor()).isEqualTo(expectedMinorNumber);
            softly.assertThat(result.getPatch()).isEqualTo(expectedPatchNumber);
            softly.assertThat(result.hasBuild()).isFalse();
            softly.assertThat(result.hasPreRelease()).isFalse();
        });
    }

    @CsvFileSource(resources = "/dataset/semantic-versions-valid-with-prerelease.csv", useHeadersInDisplayName = true)
    @ParameterizedTest
    @Timeout(1)
    void validSemanticVersionNumbersWithPreReleaseAreParsedProperly(String givenSemanticVersion,
                                                                    Integer expectedMajorNumber,
                                                                    Integer expectedMinorNumber,
                                                                    Integer expectedPatchNumber,
                                                                    String expectedPreRelease) {
        SemVer result = classUnderTest.parse(givenSemanticVersion);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(result.isValid()).isTrue();
            softly.assertThat(result.getErrorLocation()).isEmpty();
            softly.assertThat(result.getMajor()).isEqualTo(expectedMajorNumber);
            softly.assertThat(result.getMinor()).isEqualTo(expectedMinorNumber);
            softly.assertThat(result.getPatch()).isEqualTo(expectedPatchNumber);
            softly.assertThat(result.getPreRelease()).isPresent().hasValue(expectedPreRelease);
            softly.assertThat(result.hasBuild()).isFalse();
            softly.assertThat(result.hasPreRelease()).isTrue();
        });
    }

    @CsvFileSource(resources = "/dataset/semantic-versions-valid-with-build.csv", useHeadersInDisplayName = true)
    @ParameterizedTest
    void validSemanticVersionNumbersWithBuildAreParsedProperly(String givenSemanticVersion,
                                                               Integer expectedMajorNumber,
                                                               Integer expectedMinorNumber,
                                                               Integer expectedPatchNumber,
                                                               String expectedBuild) {
        SemVer result = classUnderTest.parse(givenSemanticVersion);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(result.isValid()).isTrue();
            softly.assertThat(result.getErrorLocation()).isEmpty();
            softly.assertThat(result.getMajor()).isEqualTo(expectedMajorNumber);
            softly.assertThat(result.getMinor()).isEqualTo(expectedMinorNumber);
            softly.assertThat(result.getPatch()).isEqualTo(expectedPatchNumber);
            softly.assertThat(result.hasPreRelease()).isFalse();
            softly.assertThat(result.getPreRelease()).isEmpty();
            softly.assertThat(result.hasBuild()).isTrue();
            softly.assertThat(result.getBuild()).hasValue(expectedBuild);
        });
    }

    @Test
    void verySimpleSemanticVersionNumberWillBeParsedProperly() {
        String givenSemVer = "1.2.3";

        SemVer result = classUnderTest.parse(givenSemVer);

        assertSoftly(softly -> {
            softly.assertThat(result.isValid()).isTrue();
            softly.assertThat(result.getMajor()).isEqualTo(1);
            softly.assertThat(result.getMinor()).isEqualTo(2);
            softly.assertThat(result.getPatch()).isEqualTo(3);
            softly.assertThat(result.hasBuild()).isFalse();
            softly.assertThat(result.getBuild()).isEmpty();
            softly.assertThat(result.hasPreRelease()).isFalse();
            softly.assertThat(result.getPreRelease()).isEmpty();
            softly.assertThat(result.getErrorLocation()).isEmpty();
        });
    }

}
