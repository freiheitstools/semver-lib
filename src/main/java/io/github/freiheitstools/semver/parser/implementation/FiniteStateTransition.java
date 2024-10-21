package io.github.freiheitstools.semver.parser.implementation;

class FiniteStateTransition {

    private final State startState;
    private final TransitionCharSet transitionCharSet;
    private final State targetState;

    public FiniteStateTransition(State startState, TransitionCharSet transitionCharSet, State targetState) {
        this.startState = startState;
        this.transitionCharSet = transitionCharSet;
        this.targetState = targetState;
    }

    public State getStartState() {
        return startState;
    }

    public boolean accepts(char input) {
        return transitionCharSet.accepts(input);
    }

    public State getTargetState() {
        return targetState;
    }
}
