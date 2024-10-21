package io.github.freiheitstools.semver.parser.implementation;

enum State {
    S00_START,

    /**
     * The state that follows {@linkplain #S00_START} when zero is received
     */
    S01_MAJOR_STARTS_WITH_ZERO,

    /**
     * The state that follows {@linkplain #S00_START} or {@linkplain #S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT}
     * when a positive digit is received
     */
    S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT,

    /**
     * The state that follows {@linkplain #S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT} or {@linkplain #S01_MAJOR_STARTS_WITH_ZERO}
     * then a dot is received
     */
    S03_DOT_AFTER_MAJOR_NUMBER,

    S04_MINOR_STARTS_WITH_ZERO,

    S05_MINOR_STARTS_WITH_POSITIVE_DIGIT,

    S06_DOT_AFTER_MINOR_NUMBER,

    S07_PATCH_STARTS_WITH_POSITIVE_DIGIT,

    S08_PATCH_STARTS_WITH_ZERO,

    S09_AFTER_HYPHEN_BEFORE_PRERELEASE,

    S10_PRERELEASE_AFTER_DOT,

    S11_PRERELEASE_AFTER_POSITIVE_DIGIT,

    S12_PRERELEASE_AFTER_ALPHA,

    S13_PRERELEASE_AFTER_ZERO(),

    S14_PRERELEASE_AFTER_DOT(),

    S15_PRERELEASE_DIGIT_LOOP,

    S16_AFTER_PLUS_BEFORE_BUILD(),

    S17_BUILD_AFTER_ALPHANUM(),

    S18_BUILD_DOT_IN_BUILD,

    END_OF_SEMVER,

    ERROR_MAJOR_VERSION(true),

    ERROR_MINOR_VERSION(true),

    ERROR_PATCH_NUMBER(true),

    ERROR_PRERELEASE(true),

    ERROR_BUILD(true);

    private boolean isErrorState = false;

    State(boolean isErrorState) {
        this.isErrorState = isErrorState;
    }

    State() {
    }

    public boolean isErrorState() {
        return isErrorState;
    }

    public boolean isFinalState() {
        return this == END_OF_SEMVER;
    }
}
