package io.github.freiheitstools.semver.parser.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.freiheitstools.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitstools.semver.parser.api.SemVer;
import io.github.freiheitstools.semver.parser.api.SemVerBuilder;
import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class SemVerBuilderImplTest {
    InternalSemanticVersionParser parser = new InternalSemanticVersionParser();

    @CsvFileSource(resources = "/dataset/builder/start-with-semver-and-manipulate.csv", useHeadersInDisplayName = true)
    @ParameterizedTest
    void builderCanManipulateGivenSemanticVersionProperly(
            String givenStartingSemVer, TestOperation operation, String value, String expectedResult) {
        SemVer given = parser.parse(givenStartingSemVer);
        SemVerBuilder classUnderTest = new SemVerBuilderImpl().startFrom(given);

        SemVer result =
                switch (operation) {
                    case NEXT_MAJOR_AND_RESET ->
                        classUnderTest.nextMajorAndResetSubsequentElements().build();
                    case NEXT_MAJOR -> classUnderTest.nextMajor().build();
                    case NEXT_MINOR_AND_RESET ->
                        classUnderTest.nextMinorAndResetSubsequentElements().build();
                    case NEXT_MINOR -> classUnderTest.nextMinor().build();
                    case NEXT_PATCH_AND_RESET ->
                        classUnderTest.nextPatchAndResetSubsequentElements().build();
                    case NEXT_PATCH -> classUnderTest.nextPatch().build();
                    case SET_MAJOR -> {
                        int major = Integer.parseInt(value);
                        yield classUnderTest.setMajor(major).build();
                    }
                    case SET_MINOR -> {
                        int minor = Integer.parseInt(value);
                        yield classUnderTest.setMinor(minor).build();
                    }
                    case SET_PATCH -> {
                        int patch = Integer.parseInt(value);
                        yield classUnderTest.setPatch(patch).build();
                    }
                    case DROP_PRERELEASE -> classUnderTest.removePrerelease().build();
                    case DROP_BUILD -> classUnderTest.removeBuild().build();
                    case SET_PRERELEASE -> classUnderTest.setPrerelease(value).build();
                    case SET_BUILD -> classUnderTest.setBuild(value).build();
                };

        assertThat(result).hasToString(expectedResult);
    }

    @CsvFileSource(
            resources = "/dataset/builder/start-with-invalid-values-for-core-elements.csv",
            useHeadersInDisplayName = true)
    @ParameterizedTest
    void builderRefusesInvalidSemanticVersionForGivenValuesForCoreElements(
            int givenMajorVersion, int givenMinorVersion, int givenPatchVersion) {
        SemVerBuilder classUnderTest = new SemVerBuilderImpl();
        assertThatThrownBy(() -> classUnderTest.startFrom(givenMajorVersion, givenMinorVersion, givenPatchVersion))
                .isInstanceOf(InvalidSemanticVersionException.class)
                .hasNoCause();
    }

    @CsvFileSource(
            resources = "/dataset/builder/start-with-values-for-core-elements.csv",
            useHeadersInDisplayName = true)
    @ParameterizedTest
    void builderCanCreateAValidSemanticVersionFromGivenValuesForCoreElements(
            int givenMajorVersion, int givenMinorVersion, int givenPatchVersion, String expectedResult) {
        SemVer result = new SemVerBuilderImpl()
                .startFrom(givenMajorVersion, givenMinorVersion, givenPatchVersion)
                .build();

        assertThat(result).hasToString(expectedResult);
    }

    @Test
    void builderWillFailIfBuildIdentifierIsInvalid() {
        // -- Given
        SemVer givenStartingSemVer = parser.parse("1.2.3");
        SemVerBuilder classUnderTest = new SemVerBuilderImpl().startFrom(givenStartingSemVer);

        // -- Then
        Assertions.assertThatThrownBy(() -> {
                    classUnderTest.setBuild("?");
                })
                .isInstanceOf(InvalidSemanticVersionException.class)
                .hasMessage("""
				1.2.3+? would not represent a valid semantic version, \
				as the BUILD_VERSION part is not valid\
				""");
    }

    @Test
    void builderWillFailIfPrereleaseIdentifierIsInvalid() {
        // -- Given
        SemVer givenStartingSemVer = parser.parse("1.2.3");
        SemVerBuilder classUnderTest = new SemVerBuilderImpl().startFrom(givenStartingSemVer);

        // -- Then
        Assertions.assertThatThrownBy(() -> {
                    classUnderTest.setPrerelease("?");
                })
                .isInstanceOf(InvalidSemanticVersionException.class)
                .hasMessage("""
				1.2.3-? would not represent a valid semantic version, \
				as the PRERELEASE_VERSION part is not valid\
				""");
    }

    @ParameterizedTest
    @CsvSource("""
			PATCH_VERSION
			MINOR_VERSION
			MAJOR_VERSION
			""")
    void builderRefusesInvalidSemanticVersionWithNegativeValueForCoreNumbers(SemanticVersionNumberElement element) {
        // -- Given
        SemVer givenStartingSemVer = parser.parse("1.2.3");
        SemVerBuilder classUnderTest = new SemVerBuilderImpl().startFrom(givenStartingSemVer);

        // -- Then
        assertThatThrownBy(() -> {
                    switch (element) {
                        case PATCH_VERSION -> classUnderTest.setPatch(-1);
                        case MINOR_VERSION -> classUnderTest.setMinor(-1);
                        case MAJOR_VERSION -> classUnderTest.setMajor(-1);
                    }
                })
                .isInstanceOf(InvalidSemanticVersionException.class)
                .hasNoCause();
    }
}
