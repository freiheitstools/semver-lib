package io.github.freiheitstools.semver.parser.implementation;

public enum TestOperation {
    NEXT_MAJOR_AND_RESET,
    NEXT_MAJOR,
    NEXT_MINOR_AND_RESET,
    NEXT_MINOR,
    NEXT_PATCH_AND_RESET,
    NEXT_PATCH,
    SET_MAJOR,
    SET_MINOR,
    SET_PATCH,
    DROP_PRERELEASE,
    DROP_BUILD,
    SET_PRERELEASE,
    SET_BUILD
}
