package io.github.freiheitstools.semver.parser.implementation;

import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.BUILD_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.DOT_BETWEEN_MAJOR_VERSION_AND_MINOR_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.DOT_BETWEEN_MINOR_VERSION_AND_PATCH_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.HYPHEN_BETWEEN_PATCH_VERSION_AND_PRERELEASE_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.MAJOR_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.MINOR_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.PATCH_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.PLUS_BETWEEN_PRERELEASE_VERSION_AND_VERSION_VERSION;
import static io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement.PRERELEASE_VERSION;
import static io.github.freiheitstools.semver.parser.implementation.State.ERROR_BUILD;
import static io.github.freiheitstools.semver.parser.implementation.State.ERROR_MAJOR_VERSION;
import static io.github.freiheitstools.semver.parser.implementation.State.ERROR_MINOR_VERSION;
import static io.github.freiheitstools.semver.parser.implementation.State.ERROR_PATCH_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.State.ERROR_PRERELEASE;
import static io.github.freiheitstools.semver.parser.implementation.State.S00_START;
import static io.github.freiheitstools.semver.parser.implementation.State.S01_MAJOR_STARTS_WITH_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S03_DOT_AFTER_MAJOR_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.State.S04_MINOR_STARTS_WITH_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S05_MINOR_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S06_DOT_AFTER_MINOR_NUMBER;
import static io.github.freiheitstools.semver.parser.implementation.State.S07_PATCH_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S08_PATCH_STARTS_WITH_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S09_AFTER_HYPHEN_BEFORE_PRERELEASE;
import static io.github.freiheitstools.semver.parser.implementation.State.S10_PRERELEASE_AFTER_DOT;
import static io.github.freiheitstools.semver.parser.implementation.State.S11_PRERELEASE_AFTER_POSITIVE_DIGIT;
import static io.github.freiheitstools.semver.parser.implementation.State.S12_PRERELEASE_AFTER_ALPHA;
import static io.github.freiheitstools.semver.parser.implementation.State.S13_PRERELEASE_AFTER_ZERO;
import static io.github.freiheitstools.semver.parser.implementation.State.S14_PRERELEASE_AFTER_DOT;
import static io.github.freiheitstools.semver.parser.implementation.State.S15_PRERELEASE_DIGIT_LOOP;
import static io.github.freiheitstools.semver.parser.implementation.State.S16_AFTER_PLUS_BEFORE_BUILD;
import static io.github.freiheitstools.semver.parser.implementation.State.S17_BUILD_AFTER_ALPHANUM;
import static io.github.freiheitstools.semver.parser.implementation.State.S18_BUILD_DOT_IN_BUILD;

import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import java.util.Hashtable;
import java.util.function.BiFunction;

/**
 * Maintains the mapping between states and semantic version number elements.
 */
class StateToLocationMapping {
    private static Hashtable<State, SemanticVersionNumberElement> mapping = new Hashtable<>();

    static {
        mapping.put(S00_START, MAJOR_VERSION);
        mapping.put(S01_MAJOR_STARTS_WITH_ZERO, MAJOR_VERSION);
        mapping.put(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT, MAJOR_VERSION);
        mapping.put(S03_DOT_AFTER_MAJOR_NUMBER, DOT_BETWEEN_MAJOR_VERSION_AND_MINOR_VERSION);
        mapping.put(S04_MINOR_STARTS_WITH_ZERO, MINOR_VERSION);
        mapping.put(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT, MINOR_VERSION);
        mapping.put(S06_DOT_AFTER_MINOR_NUMBER, DOT_BETWEEN_MINOR_VERSION_AND_PATCH_VERSION);
        mapping.put(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT, PATCH_VERSION);
        mapping.put(S08_PATCH_STARTS_WITH_ZERO, PATCH_VERSION);
        mapping.put(S09_AFTER_HYPHEN_BEFORE_PRERELEASE, HYPHEN_BETWEEN_PATCH_VERSION_AND_PRERELEASE_VERSION);
        mapping.put(S10_PRERELEASE_AFTER_DOT, PRERELEASE_VERSION);
        mapping.put(S11_PRERELEASE_AFTER_POSITIVE_DIGIT, PRERELEASE_VERSION);
        mapping.put(S12_PRERELEASE_AFTER_ALPHA, PRERELEASE_VERSION);
        mapping.put(S13_PRERELEASE_AFTER_ZERO, PRERELEASE_VERSION);
        mapping.put(S14_PRERELEASE_AFTER_DOT, PRERELEASE_VERSION);
        mapping.put(S15_PRERELEASE_DIGIT_LOOP, PRERELEASE_VERSION);
        mapping.put(S16_AFTER_PLUS_BEFORE_BUILD, PLUS_BETWEEN_PRERELEASE_VERSION_AND_VERSION_VERSION);
        mapping.put(S17_BUILD_AFTER_ALPHANUM, BUILD_VERSION);
        mapping.put(S18_BUILD_DOT_IN_BUILD, BUILD_VERSION);

        mapping.put(ERROR_MAJOR_VERSION, MAJOR_VERSION);
        mapping.put(ERROR_MINOR_VERSION, MINOR_VERSION);
        mapping.put(ERROR_PATCH_NUMBER, PATCH_VERSION);
        mapping.put(ERROR_PRERELEASE, PRERELEASE_VERSION);
        mapping.put(ERROR_BUILD, BUILD_VERSION);
    }
    ;

    SemanticVersionNumberElement getLocationByState(State state) {
        return mapping.compute(
                state, new BiFunction<State, SemanticVersionNumberElement, SemanticVersionNumberElement>() {
                    @Override
                    public SemanticVersionNumberElement apply(
                            State state, SemanticVersionNumberElement semanticVersionNumberElements) {
                        if (semanticVersionNumberElements == null) {
                            throw new IllegalStateException(
                                    "Missing mapping to semantic version element name for state " + state.name());
                        }

                        return semanticVersionNumberElements;
                    }
                });
    }
}
