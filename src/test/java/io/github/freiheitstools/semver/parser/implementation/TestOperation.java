package io.github.freiheitstools.semver.parser.implementation;

public enum TestOperation {
    DROP_BUILD,
    DROP_PRERELEASE,
    NEXT_MAJOR,
    NEXT_MAJOR_AND_RESET,
    NEXT_MINOR,
    NEXT_MINOR_AND_RESET,
    NEXT_PATCH,
    NEXT_PATCH_AND_RESET,
    SET_BUILD,
    SET_MAJOR,
    SET_MINOR,
    SET_PATCH,
    SET_PRERELEASE
}
