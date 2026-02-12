package io.github.freiheitstools.semver.parser.api;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * <p>
 * Exception to indicate that a given semantic version is not valid, so that
 * specific operations on an instance of {@linkplain SemVer} can not called.
 * </p>
 *
 * @see SemVer
 */
public class InvalidSemanticVersionException extends RuntimeException {

    public static InvalidSemanticVersionException ofMessage(@NonNull String message) {
        return new InvalidSemanticVersionException(message);
    }

    public static InvalidSemanticVersionException ofSemanticVersion(@NonNull String semanticVersion) {
        String message = "%s is not a valid semantic version".formatted(semanticVersion);
        return new InvalidSemanticVersionException(message);
    }

    /**
     * <p>
     * Creates a new instance of the exception for a given semantic version.
     * </p>
     *
     * @param semanticVersion
     *            the given invalid semantic version
     */
    private InvalidSemanticVersionException(@NonNull String message) {
        super(message);
    }
}
