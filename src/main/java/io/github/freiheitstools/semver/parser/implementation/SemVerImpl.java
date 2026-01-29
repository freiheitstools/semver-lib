package io.github.freiheitstools.semver.parser.implementation;

import io.github.freiheitstools.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitstools.semver.parser.api.SemVer;
import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.NonNull;

class SemVerImpl implements SemVer {
    private String build;
    private SemanticVersionNumberElement errorLocation = null;
    private String majorVersion;
    private String minorVersion;
    private String patchVersion;
    private String preReleaseIdentifier;
    private String semanticVersion;

    @NonNull @Override
    public Optional<String> getBuild() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Optional.ofNullable(build);
    }

    @Override
    public @NonNull Optional<SemanticVersionNumberElement> getErrorLocation() {
        return Optional.ofNullable(errorLocation);
    }

    @Override
    public Integer getMajor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(majorVersion);
    }

    @Override
    public Integer getMinor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(minorVersion);
    }

    @Override
    public Integer getPatch() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(patchVersion);
    }

    @NonNull @Override
    public Optional<String> getPreRelease() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Optional.ofNullable(preReleaseIdentifier);
    }

    @NonNull @Override
    public String getSemanticVersion() {
        return semanticVersion;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    // todo rm public
    public void setErrorLocation(SemanticVersionNumberElement location) {
        this.errorLocation = location;
    }

    /* todo rm public */ public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setPreReleaseIdentifier(String preReleaseIdentifier) {
        this.preReleaseIdentifier = preReleaseIdentifier;
    }

    public void setSemanticVersion(String semanticVersion) {
        this.semanticVersion = semanticVersion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getMajor()).append(".");
        sb.append(getMinor()).append(".");
        sb.append(getPatch());
        getPreRelease().ifPresent(identifier -> sb.append("-").append(identifier));
        getBuild().ifPresent(identifier -> sb.append("+").append(identifier));

        return sb.toString();
    }

    void setMinorVersion(String minorVersion) {
        this.minorVersion = minorVersion;
    }

    void setPatchVersion(@NonNull String patchVersion) {
        this.patchVersion = patchVersion;
    }
}
