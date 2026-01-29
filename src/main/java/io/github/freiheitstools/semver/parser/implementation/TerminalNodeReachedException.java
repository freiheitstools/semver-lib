package io.github.freiheitstools.semver.parser.implementation;

/**
 * Exception thrown when a terminal state is reached during state machine
 * processing while input is still available.
 */
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
