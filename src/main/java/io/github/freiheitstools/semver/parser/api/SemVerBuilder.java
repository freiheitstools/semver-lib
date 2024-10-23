package io.github.freiheitstools.semver.parser.api;

/**
 * A builder to build a Semantic Version either based on an existing one
 * or from scratch.
 *
 * <h2>Usage Examples</h2>
 * <h3>Building a new Semantic Version from an existing one</h3>
 * <pre>{@code
 * SemVerParser parser = new SemVerParser();
 * SemVer semVer = parser.parse("1.2.3-SNAPSHOT");
 *
 * SemVer nextSemVer = SemVer.builder().startFrom(semVer)
 *                           .removePrerelease().build();
 * }</pre>
 *
 * <h3>Building a new Semantic Version from scratch</h3>
 * <pre>{@code
 * SemVer nextSemVer = SemVer.builder().startFrom(1, 2, 0)
 *                           .setPrerelease("SNAPSHOT")
 *                           .build();
 * }</pre>
 */
public abstract class SemVerBuilder {
    /**
     * Creates a new instance.
     */
    protected SemVerBuilder() {
    }

    /**
     * Starts the build with a given valid semantic version as basis the semantic version to build.
     *
     * @param currentSemVer semantic version to start from
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the given semantic version is not valid
     */
    public abstract SemVerBuilder startFrom(SemVer currentSemVer);

    /**
     * Starts the build with a given major version, minor version, and patch version as basis
     * the semantic version to build.
     *
     * @param major the major version to start with
     * @param minor the minor version to start with
     * @param patch the patch version to start with
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder startFrom(int major, int minor, int patch);

    /**
     * Sets the major version to the given value.
     *
     * @param majorVersion the new major version to be set
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder setMajor(int majorVersion);

    /**
     * Sets the minor version to the given value.
     *
     * @param minorVersion the new minor version to be set
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder setMinor(int minorVersion);

    /**
     * Sets the patch version to the given value.
     *
     * @param patchVersion the new patch version to be set
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder setPatch(int patchVersion);

    /**
     * Sets the pre-release version to the given value.
     *
     * @param prereleaseVersion the new pre-release version to be set
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder setPrerelease(String prereleaseVersion);


    /**
     * Sets the build metadata to the given value.
     *
     * @param buildIdentifier the new build metadata to be set
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder setBuild(String buildIdentifier);

    /**
     * Increases the major version by one and sets minor version and patch version to 0, pre-release
     * version and build metadata are removed.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextMajorAndResetSubsequentElements();

    /**
     * Increases the major version by one and sets minor version and patch version to 0, pre-release
     * version and build metadata will be left as they are.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextMajor();

    /**
     * Increases the minor version by one, keeps the current major version, and sets
     * patch version to 0, pre-release version and build metadata are removed.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextMinorAndResetSubsequentElements();

    /**
     * Increases the minor version by one and sets the patch version to 0, pre-release
     * version and build metadata will be left as they are.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextMinor();

    /**
     * Increases the patch version by one, keeps the current major version and minor version, and
     * pre-release version and build metadata are removed.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextPatchAndResetSubsequentElements();

    /**
     * Increases the patch version by one, pre-release version and build metadata will be left as they are.
     *
     * @return the current instance of the builder
     * @throws InvalidSemanticVersionException in case the resulting semantic version would not be valid
     */
    public abstract SemVerBuilder nextPatch();

    /**
     * Removes the currently set pre-release version and leaves all other versions and the
     * build metadata untouched.
     *
     * @return the current instance of the builder
     */
    public abstract SemVerBuilder removePrerelease();

    /**
     * Removes the currently set build metadata and leaves all other versions and the
     * pre-release version untouched.
     *
     * @return the current instance of the builder
     */
    public abstract SemVerBuilder removeBuild();

    /**
     * Returns a new {@link SemVer} instance with the configured values.
     *
     * @return a new {@link SemVer} instance
     */
    public abstract SemVer build();
}
