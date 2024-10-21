package io.github.freiheitstools.semver.parser.api;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

/**
 * <p>Representation of a semantic version</p>
 *
 * @see <a href="https://semver.org">Semantic version specification</a>
 */
public interface SemVer {

    /**
     * <p>Returns the major version of the semantic version in case of a
     * valid semantic version.</p>
     *
     * @return the major version, never {@code null}
     * @throws InvalidSemanticVersionException in case of an erroneous semantic version
     */
    Integer getMajor();

    /**
     * <p>
     * Returns the minor version of the semantic version in case of a
     * valid semantic version.
     * </p>
     *
     * @return the minor version, never {@code null}
     * @throws InvalidSemanticVersionException in case of an erroneous semantic version
     */
    Integer getMinor();

    /**
     * <p>
     * Returns the patch version of the semantic version in case of a
     * valid semantic version.
     * </p>
     *
     * @return the patch “version, never {@code null}
     * @throws InvalidSemanticVersionException in case of an erroneous semantic version
     */
    Integer getPatch();

    /**
     * Returns the given semantic version as it is, independent if it is valid
     * or erroneous.
     *
     * @return the
     */
    @NonNull
    String getSemanticVersion();

    /**
     * <p>
     *     Returns the pre-release identifier of the semantic version number if present.
     * </p>
     *
     * @see #hasPreRelease()
     *
     * @return The pre-release identifier
     */
    @NonNull
    Optional<String> getPreRelease();

    /**
     * <p>
     *     Returns the build identifier of the semantic version number if present.
     * </p>
     *
     * @see #hasBuild()
     *
     * @return The build identifier
     */
    @NonNull
    Optional<String> getBuild();

    /**
     * <p>
     *      Checks if the semantic version number has a pre-release part or not.
     * </p>
     *
     * @see #getPreRelease()
     *
     * @return {@code true} if the semantic version number has a pre-release part,
     *         otherwise {@code false}.
     */
    default boolean hasPreRelease() {
        return getPreRelease().isPresent();
    }

    /**
     * <p>
     *      Checks if the semantic version number has build part or not.
     * </p>
     *
     * @see #getBuild()
     *
     * @return {@code true} if the semantic version number has a build part,
     *         otherwise {@code false}.
     */
    default boolean hasBuild() {
        return getBuild().isPresent();
    }

    /**
     * <p>Checks if the instance represents a valid semantic version or not.</p>
     *
     * @return {@code true} if the instance represents a valid semantic version, otherwise
     *         {@code false}.
     */
    default boolean isValid() {
        return getErrorLocation().isEmpty();
    }

    /**
     * <p>Checks if the instance represents an invalid semantic version or not </p>
     *
     * @return {@code true} if the instance represents an invalid semantic version, otherwise
     *         {@code false}.
     */
    default boolean isInvalid() {
        return !isValid();
    }

    /**
     * <p>Returns the location of where in case of an erroneous semantic version.</p>
     *
     * @see #isValid()
     *
     * @return The location of the erroneous part of the semamtic version in case of an invalid
     *         semantic version, otherwise an empty {@linkplain Optional}.
     */
    @NonNull
    Optional<SemanticVersionNumberElement> getErrorLocation();
}
