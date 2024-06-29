package io.github.freiheitstools.semver.parser.internal;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import static io.github.freiheitstools.semver.parser.internal.SemVerLexer.HYPHEN;
import static io.github.freiheitstools.semver.parser.internal.SemVerLexer.DOT;
import static io.github.freiheitstools.semver.parser.internal.SemVerLexer.VERSION_NUMBER_FRAGMENT;
import static org.assertj.core.api.Assertions.assertThat;

class SemVerLexerTest {

    static TokenProxy T_DOT = new TokenProxy("DOT", DOT);
    static TokenProxy T_VERSION_NUMBER_FRAGMENT = new TokenProxy("VERSION_NUMBER_FRAGMENT", VERSION_NUMBER_FRAGMENT);
    static TokenProxy T_HYPHEN = new TokenProxy("HYPHEN", VERSION_NUMBER_FRAGMENT);

    static Map<Integer, TokenProxy> TOKEN_PROXY_LOOKUP_TABLE = Map.of(DOT, T_DOT,
            VERSION_NUMBER_FRAGMENT, T_VERSION_NUMBER_FRAGMENT,
            HYPHEN, T_HYPHEN);


    @ArgumentsSource(SemanticVersionArgumentsProvider.class)
    @ParameterizedTest
    void bulletsformyvalentine(String givenSemanticVersion, List<TokenProxy> expectedTokens) {
        CodePointCharStream charStream = CharStreams.fromString(givenSemanticVersion);
        SemVerLexer lexer = new SemVerLexer(charStream);

        List<? extends Token> resultingTokens = lexer.getAllTokens();

        List<TokenProxy> proxiedTokens = proxyTokens(resultingTokens);

        assertThat(proxiedTokens).hasSameElementsAs(expectedTokens)
                .isEqualTo(expectedTokens);
    }

    private List<TokenProxy> proxyTokens(List<? extends Token> givenTokens) {
        return givenTokens.stream()
                .map((Function<Token, TokenProxy>) token -> TOKEN_PROXY_LOOKUP_TABLE.get(token.getType()))
                .toList();
    }

    static class SemanticVersionArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantA(),
                    argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantB(),
                    argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantC(),



                    argumentsForSemanticVersionConsistingOfTheCoreElementsWithFollowingHyphen()


                    // todo Formatierung
            );
        }

        Arguments argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantA() {
            return Arguments.of("1.1.1",
                    List.of(T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT));
        }

        Arguments argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantB() {
            return Arguments.of("1.1.1",
                    List.of(T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT));
        }

        Arguments argumentsForSemanticVersionConsistingOnlyOfTheCoreElementsVariantC() {
            return Arguments.of("1.9.1",
                    List.of(T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT));
        }

        Arguments argumentsForSemanticVersionConsistingOfTheCoreElementsWithFollowingHyphen() {
            return Arguments.of("1.1.1-",
                    List.of(T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT,
                            T_DOT,
                            T_VERSION_NUMBER_FRAGMENT,
                            T_HYPHEN));
        }


        // todo Fehlerhafte zu testen
        // 0.0.0
        // 0.1.1
        // a.1.1
    }

    private static class TokenProxy {
        private String displayName;
        private int token;

        public TokenProxy(String displayName, int token) {
            this.displayName = displayName;
            this.token = token;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            TokenProxy that = (TokenProxy) other;
            return token == that.token;
        }

        @Override
        public int hashCode() {
            return Objects.hash(displayName, token);
        }

        @Override
        public String toString() {
            return String.format("%s(%d)", displayName, token);
        }
    }
}