/**
 * Public API of the parser for semantic versioning.
 *
 * <h2>Usage Examples</h2>
 * <h3>Parsing a given Semantic Version</h3>
 * <pre>{@code
 * SemVerParser parser = SemVer.parser();
 *
 * SemVer semver = parser.parse("1.2.3-SNAPSHOT+23");
 *
 * if (semver.isInvalid()) {
 *     System.out.println("Given semantic version is not valid");
 * } else {
 *     System.out.println(semver.getMajor());
 *     System.out.println(semver.getMinor());
 *     System.out.println(semver.getPatch());
 *
 *     semver.getPreRelease().ifPresent(System.out::println);
 *     semver.getBuild().ifPresent(System.out::println);
 * }
 * }</pre>
 *
 * <h3>Building a new Semantic Version from an existing one</h3>
 * <pre>{@code
 * SemVerParser parser = SemVer.parser();
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
 *
 */
package io.github.freiheitstools.semver.parser.api;
