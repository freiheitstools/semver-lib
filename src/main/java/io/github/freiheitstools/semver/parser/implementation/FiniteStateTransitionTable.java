package io.github.freiheitstools.semver.parser.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class FiniteStateTransitionTable {
    private State currentState;
    private List<State> reachedStates = new ArrayList<>(6);
    private Collection<FiniteStateTransition> transitions = new ArrayList<>();

    void accept(char currentChar) throws NoTransitionFoundException {
        if (getCurrentState().isErrorState() || getCurrentState().isFinalState()) {
            throw new TerminalNodeReachedException(getCurrentState());
        }

        Collection<FiniteStateTransition> transitions = findAllTransitionsForCurrentState();

        FiniteStateTransition transition = transitions.stream()
                .filter(finiteStateTransition -> finiteStateTransition.accepts(currentChar))
                .findFirst()
                .orElseThrow(() -> new NoTransitionFoundException(getCurrentState(), currentChar));

        setNewState(currentChar, transition.getTargetState());
    }

    void addTransition(FiniteStateTransition transition) {
        this.transitions.add(transition);
    }

    ToStateStep from(State startState) {
        return new ToStateStep(startState);
    }

    State getCurrentState() {
        return currentState;
    }

    List<State> getReachedStates() {
        return Collections.unmodifiableList(reachedStates);
    }

    void startFrom(State startState) {
        if (this.currentState != null) {
            throw new IllegalStateException("Start state has already been set");
        }

        setNewState('s', startState);
    }

    private Collection<FiniteStateTransition> findAllTransitionsForCurrentState() {
        return transitions.stream()
                .filter(finiteStateTransition -> finiteStateTransition.getStartState() == getCurrentState())
                .toList();
    }

    private void setNewState(char input, State newState) {
        // used only for debugging [System.out.println("'" + input + "' : "
        // +getCurrentState() + " -> " + newState);
        this.currentState = newState;
        reachedStates.add(newState);
    }

    class ToStateStep {
        private final State startState;

        public ToStateStep(State startState) {
            this.startState = startState;
        }

        WhenStep to(State targetState) {
            return new WhenStep(startState, targetState);
        }
    }

    class WhenStep {
        private final State startState;
        private final State targetState;

        WhenStep(State startState, State targetState) {
            this.startState = startState;
            this.targetState = targetState;
        }

        FiniteStateTransitionTable when(TransitionCharSet transitionCharSet) {
            FiniteStateTransition transition = new FiniteStateTransition(startState, transitionCharSet, targetState);
            FiniteStateTransitionTable.this.addTransition(transition);
            return FiniteStateTransitionTable.this;
        }
    }
}
