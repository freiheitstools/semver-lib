package io.github.freiheitsgrade.semver.parser.implementation;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Regular expressions provided by the maintainers of semver.org used to validate or to parse
 * a semantic version.
 */
public class SemVerRegex {
    final static String SEM_VER_REGEX = """
        ^(0|[1-9]\\d*)\\.
        (0|[1-9]\\d*)\\.
        (0|[1-9]\\d*)
        (?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))
        ?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$
        """.lines().collect(Collectors.joining());

    final static Pattern SEM_VER_PATTERN = Pattern.compile(SEM_VER_REGEX);
}
