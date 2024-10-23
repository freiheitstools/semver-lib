package io.github.freiheitstools.semver.parser.implementation;

import io.github.freiheitstools.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitstools.semver.parser.api.SemVer;
import io.github.freiheitstools.semver.parser.api.SemVerBuilder;

public class SemVerBuilderImpl extends SemVerBuilder {
    private int major;
    private int minor;
    private int patch;
    private String build;
    private String prerelease;

    @Override
    public SemVerBuilder startFrom(SemVer currentSemVer) {
        this.major = currentSemVer.getMajor();
        this.minor = currentSemVer.getMinor();
        this.patch = currentSemVer.getPatch();
        currentSemVer.getBuild().ifPresent(build -> this.build = build);
        currentSemVer.getPreRelease().ifPresent(preRelease -> this.prerelease = preRelease);

        return this;
    }

    @Override
    public SemVerBuilder startFrom(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder setMajor(int majorVersion) {
        this.major = majorVersion;

        validateCurrentSemanticVersion();


        return this;
    }

    @Override
    public SemVerBuilder setMinor(int majorVersion) {
        this.minor = majorVersion;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder setPatch(int patchVersion) {
        this.patch = patchVersion;

        validateCurrentSemanticVersion();


        return this;
    }

    @Override
    public SemVerBuilder setPrerelease(String prereleaseIdentifier) {
        this.prerelease = prereleaseIdentifier;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder setBuild(String buildIdentifier) {
        this.build = buildIdentifier;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder nextMajorAndResetSubsequentElements() {
        this.major = this.major + 1;
        this.minor = 0;
        this.patch = 0;
        this.build = null;
        this.prerelease = null;

        validateCurrentSemanticVersion();


        return this;
    }

    @Override
    public SemVerBuilder nextMinorAndResetSubsequentElements() {
        this.minor = minor + 1;
        this.patch = 0;
        this.build = null;
        this.prerelease = null;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder nextPatchAndResetSubsequentElements() {
        this.patch = patch + 1;
        this.build = null;
        this.prerelease = null;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder removePrerelease() {
        this.prerelease = null;

        validateCurrentSemanticVersion();

        return this;
    }

    @Override
    public SemVerBuilder removeBuild() {
        this.build = null;

        validateCurrentSemanticVersion();

        return this;
    }

    private void validateCurrentSemanticVersion() {
        StringBuilder builder = new StringBuilder();
        builder.append(major).append(".").append(minor).append(".").append(patch);

        if (prerelease != null) {
            builder.append("-").append(prerelease);
        }

        if (build != null) {
            builder.append("+").append(build);
        }

        String stringRepresentation = builder.toString();
        SemVer result = new InternalSemanticVersionParser().parse(stringRepresentation);

        if (result.isInvalid()) {
            String message = """
                %s would not represent a valid semantic version, as the %s part is not valid
                """.formatted(stringRepresentation, result.getErrorLocation().orElseThrow(IllegalStateException::new));

            throw new InvalidSemanticVersionException(message);
        }

    }

    @Override
    public SemVerBuilder nextMinor() {
        this.minor = minor + 1;
        this.patch = 0;
        return this;
    }

    @Override
    public SemVerBuilder nextPatch() {
        this.patch = patch + 1;
        return this;
    }

    @Override
    public SemVerBuilder nextMajor() {
        this.major = major + 1;
        this.minor = 0;
        this.patch = 0;
        return this;
    }

    @Override
    public SemVer build() {
        SemVerImpl result = new SemVerImpl();

        result.setMajorVersion(String.valueOf(major));
        result.setMinorVersion(String.valueOf(minor));
        result.setPatchVersion(String.valueOf(patch));
        result.setBuild(build);
        result.setPreReleaseIdentifier(prerelease);

        return result;
    }
}
