package io.github.freiheitstools.semver.parser.implementation;

class TerminalNodeReachedException extends RuntimeException {

    private final State state;

    public TerminalNodeReachedException(State currentState) {
        super(String.format("Current state '%s' is terminal state'", currentState));
        this.state = currentState;
    }

    public State getState() {
        return state;
    }
}
