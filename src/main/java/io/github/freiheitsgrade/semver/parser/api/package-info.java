/**
 * Public API of the parser for semantic versioning.
 *
 * <h2>Usage Example</h2>
 * <h3>Parsing a given Semantic Version</h3>
 * <pre>{@code
 * SemVerParser parser = new SemVerParser();
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
 */
package io.github.freiheitsgrade.semver.parser.api;
