package io.github.freiheitstools.semver.parser.implementation;

import io.github.freiheitstools.semver.parser.api.SemVer;
import io.github.freiheitstools.semver.parser.api.SemVerParser;
import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;

import static io.github.freiheitstools.semver.parser.implementation.CharacterSets.TERMINAL_SIGNAL;

/** todo Klasse umbenennen ohne 2  */
public class InternalSemanticVersionParser implements SemVerParser {
    public static final int MAX_LENGTH = 2_048;

    public @NonNull SemVer parse(String semanticVersion) throws IllegalArgumentException {
        if (StringUtils.length(semanticVersion) > MAX_LENGTH) {
            String message = """
                The given semantic version exceeds the maximum allowed length of %d characters
                """.formatted(MAX_LENGTH);
            throw new IllegalArgumentException(message);
        }

        String nullSafeSemanticVersion = semanticVersion == null ? "" : semanticVersion;
        char[] input = new char[nullSafeSemanticVersion.length() + 1];
        System.arraycopy(nullSafeSemanticVersion.toCharArray(), 0, input, 0, nullSafeSemanticVersion.length());
        input[nullSafeSemanticVersion.length()] = TERMINAL_SIGNAL;

        ParsedElements parsedElements = new ParsedElements();
        SemVerFiniteStateTransitionTable transitionTable = new SemVerFiniteStateTransitionTable();
        StateToLocationMapping errorLocationMapping = new StateToLocationMapping();
        SemVerImpl semVer = new SemVerImpl();

        try {
            for (char c : input) {
                State newState = transitionTable.accept(c);

                if (newState != State.END_OF_SEMVER) {
                    SemanticVersionNumberElement location = errorLocationMapping.getLocationByState(newState);
                    parsedElements.get(location).append(c);
                }
            }

        } catch (NoTransitionFoundException noTransitionFoundException) {
            String message = String.format("Failed to parse semantic version '%s'. This might be a bug in the library ", semanticVersion);
            throw new IllegalStateException(message, noTransitionFoundException);
        } catch (TerminalNodeReachedException e) {
            // ignore
        }

        State finalState = transitionTable.getReachedStates().get(transitionTable.getReachedStates().size() - 1);

        if (finalState.isErrorState()) {
            SemanticVersionNumberElement errorLocation = errorLocationMapping.getLocationByState(finalState);
            semVer.setErrorLocation(errorLocation);
        }

        parsedElements.getResult(SemanticVersionNumberElement.MAJOR_VERSION).ifPresent(semVer::setMajorVersion);
        parsedElements.getResult(SemanticVersionNumberElement.MINOR_VERSION).ifPresent(semVer::setMinorVersion);
        parsedElements.getResult(SemanticVersionNumberElement.PATCH_VERSION).ifPresent(semVer::setPatchVersion);
        parsedElements.getResult(SemanticVersionNumberElement.PRERELEASE_VERSION).ifPresent(semVer::setPreReleaseIdentifier);
        parsedElements.getResult(SemanticVersionNumberElement.BUILD_VERSION).ifPresent(semVer::setBuild);
        return semVer;
    }
}
