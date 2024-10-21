package io.github.freiheitstools.semver.parser.api;

/**
 * <p>Enumeration of all elements a semantic version can have.</p>
 */
public enum SemanticVersionNumberElement {
    /**
     * The major version of a semantic version
     */
    MAJOR_VERSION,

    /**
     * The dot (.) used to separate major version and minor version of a semantic version
     */
    DOT_BETWEEN_MAJOR_VERSION_AND_MINOR_VERSION,

    /**
     * The minor version of a semantic version
     */
    MINOR_VERSION,

    /**
     * The dot (.) used to separate minor version and patch version of a semantic version
     */
    DOT_BETWEEN_MINOR_VERSION_AND_PATCH_VERSION,

    /**
     * The prerelease version of a semantic version
     */
    PRERELEASE_VERSION,

    /**
     * The hyphen (-) used to separate patch version and a given prerelease version of
     * a semantic version.
     */
    HYPHEN_BETWEEN_PATCH_VERSION_AND_PRERELEASE_VERSION,

    /**
     * The plus (+) used to separate patch version or pre-release version and  given build metadata of
     * a semantic version.
     */
    PLUS_BETWEEN_PRERELEASE_VERSION_AND_VERSION_VERSION,

    /**
     * The build metadata of a semantic version.
     */
    BUILD_VERSION,

    /**
     * The patch version element of a semantic version.
     */
    PATCH_VERSION
}
