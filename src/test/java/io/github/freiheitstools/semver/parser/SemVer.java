package io.github.freiheitstools.semver.parser;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

public interface SemVer {

    /**
     * <p>
     *     Returns the major version of the semantic version in case of a
     *     valid semantic version.
     * </p>
     *
     * @throws InvalidSemanticVersionException
     *         in case of an erroneous semantic version
     *
     * @return the major version, never {@code null}
     */
    @NonNull
    String getMajor();

    /**
     * <p>
     *     Returns the minor version of the semantic version in case of a
     *     valid semantic version.
     * </p>
     *
     * @throws InvalidSemanticVersionException
     *         in case of an erroneous semantic version
     *
     * @return the minor version, never {@code null}
     */
    @NonNull
    String getMinor();

    /**
     * <p>
     *     Returns the patch version of the semantic version in case of a
     *     valid semantic version.
     * </p>
     *
     * @throws InvalidSemanticVersionException
     *         in case of an erroneous semantic version
     *
     * @return the patch “version, never {@code null}
     */
    @NonNull
    String getPatch();

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
     * <p>
     *      Checks if the instance represents a valid semantic version or not.
     * </p>
     *
     *
     * @return {@link true} if the instance represents a valid semantic version, otherwise
     *         {@link false}.
     */
    default boolean isValid() {
        return getErrorLocation().isEmpty();
    }

    /**
     * <p>
     *      Returns the location of where in case of an erroneous semantic version.
     * </p>
     *
     * @see #isValid()
     *
     * @return The location of the erroneous part of the sematic version in case of an invalid
     *         semantic version, otherwise an empty {@linkplain Optional}.
     */
    Optional<ErrorLocation> getErrorLocation();
}
