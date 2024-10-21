package io.github.freiheitstools.semver.parser.api;

import io.github.freiheitstools.semver.parser.implementation.InternalSemanticVersionParser;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * <p>A parser for parsing a given semantic version, returning a typesafe representation of type
 * {@linkplain SemVer}, which allows to easily check if the given string represents a valid
 * semantic version or not and to access each element of a semantic version.</p>
 *
 * <p>The method {@linkplain  #parse(String)} takes a given semantic version presented as string
 * and returns a {@linkplain SemVer} instance, representing the given semantic version.
 * The method {@linkplain SemVer#isValid()}) allows you to check if the semantic version valid or
 * not. In case of an invalid semantic version, the method {@linkplain SemVer#getErrorLocation()}
 * returns the erroneous element of the semantic version.</p>
 *
 * @see <a href="https://semver.org">Semantic Version Specification</a>
 */
public class SemVerParser {
    /**
     * Creates a new instance of the parser
     */
    public SemVerParser() {
    }

    /**
     * Parses a given string as semantic version number and returns a {@linkplain SemVer} instance,
     * representing the given semantic version.
     *
     * @param semanticVersion the given semantic version.
     * @return {@linkplain SemVer} instance representing the given semantic version, never {@code null}
     * @throws IllegalArgumentException if the given semantic version is longer then 2.048 characters
     */
    public @NonNull SemVer parse(String semanticVersion)
        throws IllegalArgumentException {
        InternalSemanticVersionParser internalParser = new InternalSemanticVersionParser();

        return internalParser.parse(semanticVersion);
    }
}
