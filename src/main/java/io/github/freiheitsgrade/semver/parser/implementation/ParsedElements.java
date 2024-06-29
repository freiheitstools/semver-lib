package io.github.freiheitsgrade.semver.parser.implementation;

import io.github.freiheitsgrade.semver.parser.api.SemanticVersionNumberElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class ParsedElements {
    private final Map<SemanticVersionNumberElement, StringBuilder> elements = new HashMap<>();

    public StringBuilder get(SemanticVersionNumberElement name) {
        return elements.computeIfAbsent(name, key -> new StringBuilder());
    }

    public Optional<String> getResult(SemanticVersionNumberElement elementNameMajorNumber) {
        if (elements.containsKey(elementNameMajorNumber)) {
            return Optional.of(elements.get(elementNameMajorNumber).toString());
        }

        return Optional.empty();
    }
}

