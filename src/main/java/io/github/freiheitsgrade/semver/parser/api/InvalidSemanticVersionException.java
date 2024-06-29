package io.github.freiheitsgrade.semver.parser.api;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * <p>Exception to indicate that a given semantic version is not valid, so that specific
 * operations on an instance of {@linkplain SemVer} can not called.</p>
 *
 * @see SemVer
 */
public class InvalidSemanticVersionException extends RuntimeException {

    /**
     * <p>Creates a new instance of the exception for a given semantic version.</p>
     *
     * @param semanticVersion the given invalid semantic version
     */
    public InvalidSemanticVersionException(@NonNull String semanticVersion) {
        super(semanticVersion + " is not a valid semantic version");
    }
}
