package io.github.freiheitsgrade.semver.parser.implementation;

import java.util.List;

import static io.github.freiheitsgrade.semver.parser.implementation.State.*;
import static io.github.freiheitsgrade.semver.parser.implementation.State.END_OF_SEMVER;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_BUILD;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_PATCH_NUMBER;
import static io.github.freiheitsgrade.semver.parser.implementation.State.ERROR_PRERELEASE;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S08_PATCH_STARTS_WITH_ZERO;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S09_AFTER_HYPHEN_BEFORE_PRERELEASE;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S11_PRERELEASE_AFTER_POSITIVE_DIGIT;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S12_PRERELEASE_AFTER_ALPHA;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S13_PRERELEASE_AFTER_ZERO;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S14_PRERELEASE_AFTER_DOT;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S15_PRERELEASE_DIGIT_LOOP;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S06_DOT_AFTER_MINOR_NUMBER;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S07_PATCH_STARTS_WITH_POSITIVE_DIGIT;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S16_AFTER_PLUS_BEFORE_BUILD;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S17_BUILD_AFTER_ALPHANUM;
import static io.github.freiheitsgrade.semver.parser.implementation.State.S18_BUILD_DOT_IN_BUILD;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.ALPHA;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.ALPHANUM;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.DIGIT;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.DOT;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.END_OF_INPUT;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.HYPHEN;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.PLUS;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.POSITIVE_DIGIT;
import static io.github.freiheitsgrade.semver.parser.implementation.TransitionCharSets.ZERO_DIGIT;

class SemVerFiniteStateTransitionTable {
    FiniteStateTransitionTable transitionTable = new FiniteStateTransitionTable();

    SemVerFiniteStateTransitionTable() {
        initFiniteStateTransitionTable();
    }

    @SuppressWarnings("DuplicatedCode")
    void initFiniteStateTransitionTable() {
        /*
         * AVOID linebreaks in the statements used to configure a transition. The Python script used to
         * extract the rules can not handle linebreaks in the statements.
         * 2024-10-20 // Oliver B. Fischer
         */
        /*
         *
         */
        transitionTable.startFrom(S00_START);

        /*
         *
         */
        transitionTable.from(S00_START).to(S01_MAJOR_STARTS_WITH_ZERO).when(ZERO_DIGIT);
        transitionTable.from(S00_START).to(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT).when(POSITIVE_DIGIT);
        transitionTable.from(S00_START).to(ERROR_MAJOR_VERSION).when(END_OF_INPUT);
        transitionTable.from(S00_START).to(ERROR_MAJOR_VERSION).when(TransitionCharSet.ofNegationFor(DIGIT));

        /*
         *
         */
        transitionTable.from(S01_MAJOR_STARTS_WITH_ZERO).to(S03_DOT_AFTER_MAJOR_NUMBER).when(DOT);
        transitionTable.from(S01_MAJOR_STARTS_WITH_ZERO).to(ERROR_MAJOR_VERSION).when(TransitionCharSet.ofNegationFor(DOT));

        /*
         *
         */
        transitionTable.from(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT).to(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT).when(DIGIT);
        transitionTable.from(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT).to(S03_DOT_AFTER_MAJOR_NUMBER).when(DOT);
        transitionTable.from(S02_MAJOR_STARTS_WITH_POSITIVE_DIGIT).to(ERROR_MINOR_VERSION).when(TransitionCharSet.ofNegationFor(DOT, DIGIT));

        /*
         * S3: All transitions starting from the dot between major and minor number
         */
        transitionTable.from(S03_DOT_AFTER_MAJOR_NUMBER).to(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT).when(POSITIVE_DIGIT);
        transitionTable.from(S03_DOT_AFTER_MAJOR_NUMBER).to(S04_MINOR_STARTS_WITH_ZERO).when(ZERO_DIGIT);
        transitionTable.from(S03_DOT_AFTER_MAJOR_NUMBER).to(ERROR_MINOR_VERSION).when(TransitionCharSet.ofNegationFor(DIGIT));

        /*
         *
         */
        transitionTable.from(S04_MINOR_STARTS_WITH_ZERO).to(S06_DOT_AFTER_MINOR_NUMBER).when(DOT);
        transitionTable.from(S04_MINOR_STARTS_WITH_ZERO).to(ERROR_MINOR_VERSION).when(TransitionCharSet.ofNegationFor(DOT));
        transitionTable.from(S04_MINOR_STARTS_WITH_ZERO).to(ERROR_MINOR_VERSION).when(TransitionCharSet.ofNegationFor(DIGIT));

        /*
         * All transitions from S5: The minor number starts with a positive digit
         */
        transitionTable.from(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT).to(S06_DOT_AFTER_MINOR_NUMBER).when(DOT);
        transitionTable.from(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT).to(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT).when(DIGIT);
        transitionTable.from(S05_MINOR_STARTS_WITH_POSITIVE_DIGIT).to(ERROR_MINOR_VERSION).when(TransitionCharSet.ofNegationFor(DOT, DIGIT));

        /*
         *
         */
        transitionTable.from(S06_DOT_AFTER_MINOR_NUMBER).to(S08_PATCH_STARTS_WITH_ZERO).when(ZERO_DIGIT);
        transitionTable.from(S06_DOT_AFTER_MINOR_NUMBER).to(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).when(POSITIVE_DIGIT);
        transitionTable.from(S06_DOT_AFTER_MINOR_NUMBER).to(ERROR_PATCH_NUMBER).when(END_OF_INPUT);
        transitionTable.from(S06_DOT_AFTER_MINOR_NUMBER).to(ERROR_PATCH_NUMBER).when(TransitionCharSet.ofNegationFor(DIGIT));

        /*
         * All transitions from S7: The patch number starts with 1..9
         */
        transitionTable.from(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).to(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).when(DIGIT);
        transitionTable.from(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).to(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).when(HYPHEN);
        transitionTable.from(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).to(S16_AFTER_PLUS_BEFORE_BUILD).when(PLUS);
        transitionTable.from(S07_PATCH_STARTS_WITH_POSITIVE_DIGIT).to(ERROR_PATCH_NUMBER).when(TransitionCharSet.ofNegationFor(PLUS, DIGIT, HYPHEN));

        /*
         *
         */
        transitionTable.from(S08_PATCH_STARTS_WITH_ZERO).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S08_PATCH_STARTS_WITH_ZERO).to(ERROR_PATCH_NUMBER).when(DIGIT);
        transitionTable.from(S08_PATCH_STARTS_WITH_ZERO).to(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).when(HYPHEN);
        transitionTable.from(S08_PATCH_STARTS_WITH_ZERO).to(S16_AFTER_PLUS_BEFORE_BUILD).when(PLUS);

        /*
         *
         */
        transitionTable.from(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).to(S12_PRERELEASE_AFTER_ALPHA).when(ALPHA);
        transitionTable.from(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).to(S13_PRERELEASE_AFTER_ZERO).when(ZERO_DIGIT);
        transitionTable.from(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).to(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).when(POSITIVE_DIGIT);
        transitionTable.from(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).to(ERROR_PRERELEASE).when(END_OF_INPUT);
        transitionTable.from(S09_AFTER_HYPHEN_BEFORE_PRERELEASE).to(ERROR_PRERELEASE).when(TransitionCharSet.ofNegationFor(ALPHA, ZERO_DIGIT, POSITIVE_DIGIT));

        /*
         *
         */
        transitionTable.from(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).to(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).when(ALPHANUM);
        transitionTable.from(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).to(S14_PRERELEASE_AFTER_DOT).when(DOT);
        transitionTable.from(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).to(S16_AFTER_PLUS_BEFORE_BUILD).when(PLUS);

        /*
         *
         */
        transitionTable.from(S12_PRERELEASE_AFTER_ALPHA).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S12_PRERELEASE_AFTER_ALPHA).to(S12_PRERELEASE_AFTER_ALPHA).when(ALPHANUM);
        transitionTable.from(S12_PRERELEASE_AFTER_ALPHA).to(S14_PRERELEASE_AFTER_DOT).when(DOT);
        transitionTable.from(S12_PRERELEASE_AFTER_ALPHA).to(ERROR_PRERELEASE).when(TransitionCharSet.ofNegationFor(ALPHA, DOT, PLUS));
        transitionTable.from(S12_PRERELEASE_AFTER_ALPHA).to(S16_AFTER_PLUS_BEFORE_BUILD).when(PLUS);

        /*
         *
         */
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(S14_PRERELEASE_AFTER_DOT).when(DOT);
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(S15_PRERELEASE_DIGIT_LOOP).when(DIGIT);
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(S12_PRERELEASE_AFTER_ALPHA).when(ALPHA);
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(S16_AFTER_PLUS_BEFORE_BUILD).when(PLUS);
        transitionTable.from(S13_PRERELEASE_AFTER_ZERO).to(ERROR_PRERELEASE).when(TransitionCharSet.ofNegationFor(DIGIT, ALPHA, HYPHEN, DOT, PLUS));

        /*
         *
         */
        transitionTable.from(S14_PRERELEASE_AFTER_DOT).to(S11_PRERELEASE_AFTER_POSITIVE_DIGIT).when(POSITIVE_DIGIT);
        transitionTable.from(S14_PRERELEASE_AFTER_DOT).to(S13_PRERELEASE_AFTER_ZERO).when(ZERO_DIGIT);
        transitionTable.from(S14_PRERELEASE_AFTER_DOT).to(S12_PRERELEASE_AFTER_ALPHA).when(ALPHA);
        transitionTable.from(S14_PRERELEASE_AFTER_DOT).to(ERROR_PRERELEASE).when(END_OF_INPUT);
        transitionTable.from(S14_PRERELEASE_AFTER_DOT).to(ERROR_PRERELEASE).when(DOT);

        /*
         *
         */
        transitionTable.from(S15_PRERELEASE_DIGIT_LOOP).to(S15_PRERELEASE_DIGIT_LOOP).when(ZERO_DIGIT);
        transitionTable.from(S15_PRERELEASE_DIGIT_LOOP).to(S12_PRERELEASE_AFTER_ALPHA).when(ALPHA);
        transitionTable.from(S15_PRERELEASE_DIGIT_LOOP).to(ERROR_PRERELEASE).when(END_OF_INPUT);
        transitionTable.from(S15_PRERELEASE_DIGIT_LOOP).to(ERROR_PRERELEASE).when(TransitionCharSet.ofNegationFor(ZERO_DIGIT, ALPHA, END_OF_INPUT));

        /*
         *
         */
        transitionTable.from(S16_AFTER_PLUS_BEFORE_BUILD).to(ERROR_BUILD).when(END_OF_INPUT);
        transitionTable.from(S16_AFTER_PLUS_BEFORE_BUILD).to(S17_BUILD_AFTER_ALPHANUM).when(ALPHANUM);
        transitionTable.from(S16_AFTER_PLUS_BEFORE_BUILD).to(ERROR_BUILD).when(TransitionCharSet.ofNegationFor(ALPHANUM));

        /*
         *
         */
        transitionTable.from(S17_BUILD_AFTER_ALPHANUM).to(END_OF_SEMVER).when(END_OF_INPUT);
        transitionTable.from(S17_BUILD_AFTER_ALPHANUM).to(S17_BUILD_AFTER_ALPHANUM).when(ALPHANUM);
        transitionTable.from(S17_BUILD_AFTER_ALPHANUM).to(S18_BUILD_DOT_IN_BUILD).when(DOT);
        transitionTable.from(S17_BUILD_AFTER_ALPHANUM).to(ERROR_BUILD).when(TransitionCharSet.ofNegationFor(ALPHANUM, DOT));

        /*
         *
         */
        transitionTable.from(S18_BUILD_DOT_IN_BUILD).to(S17_BUILD_AFTER_ALPHANUM).when(ALPHANUM);
        transitionTable.from(S18_BUILD_DOT_IN_BUILD).to(ERROR_BUILD).when(END_OF_INPUT);
        transitionTable.from(S18_BUILD_DOT_IN_BUILD).to(ERROR_BUILD).when(DOT);
    }

    State accept(char currentChar) throws NoTransitionFoundException {
        transitionTable.accept(currentChar);
        return getCurrentState();
    }

    State getCurrentState() {
        return transitionTable.getCurrentState();
    }

    List<State> getReachedStates() {
        return transitionTable.getReachedStates();
    }
}
