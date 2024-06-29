package io.github.freiheitsgrade.semver.parser.implementation;

import io.github.freiheitsgrade.semver.parser.api.SemanticVersionNumberElement;

import java.util.Hashtable;
import java.util.function.BiFunction;

import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_BUILD;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_MAJOR_VERSION;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_MINOR_VERSION;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_PATCH_NUMBER;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_PRERELEASE;

class StateToLocationMapping {
    private static Hashtable<State, SemanticVersionNumberElement> mapping = new Hashtable<>();

    static {
        mapping.put(State.S00_START, SemanticVersionNumberElement.MAJOR_VERSION);
        mapping.put(State.S01_MAJOR_STARTS_WITH_ZERO, SemanticVersionNumberElement.MAJOR_VERSION);
        mapping.put(State.S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT, SemanticVersionNumberElement.MAJOR_VERSION);
        mapping.put(State.S03_DOT_AFTER_MAJOR_NUMBER, SemanticVersionNumberElement.DOT_BETWEEN_MAJOR_VERSION_AND_MINOR_VERSION);
        mapping.put(State.S04_MINOR_STARTS_WITH_ZERO, SemanticVersionNumberElement.MINOR_VERSION);
        mapping.put(State.S05_MINOR_STARTS_WITH_POSITIVE_DIGIT, SemanticVersionNumberElement.MINOR_VERSION);
        mapping.put(State.S06_DOT_AFTER_MINOR_NUMBER, SemanticVersionNumberElement.DOT_BETWEEN_MINOR_VERSION_AND_PATCH_VERSION);
        mapping.put(State.S07_PATCH_STARTS_WITH_POSITIVE_DIGIT, SemanticVersionNumberElement.PATCH_VERSION);
        mapping.put(State.S08_PATCH_STARTS_WITH_ZERO, SemanticVersionNumberElement.PATCH_VERSION);
        mapping.put(State.S09_AFTER_HYPHEN_BEFORE_PRERELEASE, SemanticVersionNumberElement.HYPHEN_BETWEEN_PATCH_VERSION_AND_PRERELEASE_VERSION);
        mapping.put(State.S10_PRERELEASE_AFTER_DOT, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S11_PRERELEASE_AFTER_POSITIVE_DIGIT, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S12_PRERELEASE_AFTER_ALPHA, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S13_PRERELEASE_AFTER_ZERO, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S14_PRERELEASE_AFTER_DOT, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S15_PRERELEASE_DIGIT_LOOP, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(State.S16_AFTER_PLUS_BEFORE_BUILD, SemanticVersionNumberElement.PLUS_BETWEEN_PRERELEASE_VERSION_AND_VERSION_VERSION);
        mapping.put(State.S17_BUILD_AFTER_ALPHANUM, SemanticVersionNumberElement.BUILD_VERSION);
        mapping.put(State.S18_BUILD_DOT_IN_BUILD, SemanticVersionNumberElement.BUILD_VERSION);

        mapping.put(ERROR_MAJOR_VERSION, SemanticVersionNumberElement.MAJOR_VERSION);
        mapping.put(ERROR_MINOR_VERSION, SemanticVersionNumberElement.MINOR_VERSION);
        mapping.put(ERROR_PATCH_NUMBER, SemanticVersionNumberElement.PATCH_VERSION);
        mapping.put(ERROR_PRERELEASE, SemanticVersionNumberElement.PRERELEASE_VERSION);
        mapping.put(ERROR_BUILD, SemanticVersionNumberElement.BUILD_VERSION);
    };

    SemanticVersionNumberElement getLocationByState(State state) {
        return mapping.compute(state, new BiFunction<State, SemanticVersionNumberElement, SemanticVersionNumberElement>() {
            @Override
            public SemanticVersionNumberElement apply(State state, SemanticVersionNumberElement semanticVersionNumberElements) {
                if (semanticVersionNumberElements == null) {
                    throw new IllegalStateException("Missing mapping to semantic version element name for state " + state.name());
                }

                return semanticVersionNumberElements;
            }
        });
    }
}
