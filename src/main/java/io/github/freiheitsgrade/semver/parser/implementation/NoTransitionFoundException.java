package io.github.freiheitsgrade.semver.parser.implementation;

class NoTransitionFoundException extends RuntimeException {
    private State state;

    public NoTransitionFoundException(State currentState, char input) {
        super(String.format("No transition found for current state '%s' and input '%s'", currentState, input));
        this.state = currentState;
    }

    public State getState() {
        return state;
    }
}
