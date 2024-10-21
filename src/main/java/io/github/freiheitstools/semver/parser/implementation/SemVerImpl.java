package io.github.freiheitstools.semver.parser.implementation;

import io.github.freiheitstools.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitstools.semver.parser.api.SemVer;
import io.github.freiheitstools.semver.parser.api.SemanticVersionNumberElement;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

class SemVerImpl implements SemVer {
    private String majorVersion;
    private String minorVersion;
    private String patchVersion;
    private String semanticVersion;
    private String preReleaseIdentifier;
    private SemanticVersionNumberElement errorLocation = null;
    private String build;

    @Override
    public Integer getMajor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(majorVersion);
    }

    /* todo rm public */ public
    void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }


    @Override
    public Integer getMinor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(minorVersion);
    }


    void setMinorVersion(String minorVersion) {
        this.minorVersion = minorVersion;
    }

    @Override
    public Integer getPatch() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Integer.valueOf(patchVersion);
    }

    void setPatchVersion(@NonNull String patchVersion) {
        this.patchVersion = patchVersion;
    }

    @NonNull
    @Override
    public String getSemanticVersion() {
        return semanticVersion;
    }

    public void setSemanticVersion(String semanticVersion) {
        this.semanticVersion = semanticVersion;
    }

    @NonNull
    @Override
    public Optional<String> getPreRelease() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Optional.ofNullable(preReleaseIdentifier);
    }

    @NonNull
    @Override
    public Optional<String> getBuild() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return Optional.ofNullable(build);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getMajor()).append(".");
        sb.append(getMinor()).append(".");
        sb.append(getPatch());

        return sb.toString();
    }

    public void setPreReleaseIdentifier(String preReleaseIdentifier) {
        this.preReleaseIdentifier = preReleaseIdentifier;
    }

    @Override
    public @NonNull Optional<SemanticVersionNumberElement> getErrorLocation() {
        return Optional.ofNullable(errorLocation);
    }

    // todo rm public
    public void setErrorLocation(SemanticVersionNumberElement location) {
        this.errorLocation = location;
    }

    public void setBuild(String build) {
        this.build = build;
    }
}
