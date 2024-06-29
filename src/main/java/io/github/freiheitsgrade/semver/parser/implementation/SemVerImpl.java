package io.github.freiheitsgrade.semver.parser.implementation;

import io.github.freiheitsgrade.semver.parser.api.InvalidSemanticVersionException;
import io.github.freiheitsgrade.semver.parser.api.SemVer;
import io.github.freiheitsgrade.semver.parser.api.SemanticVersionNumberElement;
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

    @NonNull
    @Override
    public String getMajor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return majorVersion;
    }

    /* todo rm public */ public
    void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }


    @NonNull
    @Override
    public  String getMinor() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return minorVersion;
    }


    void setMinorVersion(String minorVersion) {
        this.minorVersion = minorVersion;
    }

    @NonNull
    @Override
    public String getPatch() {
        if (isInvalid()) {
            throw new InvalidSemanticVersionException(getSemanticVersion());
        }

        return patchVersion;
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
