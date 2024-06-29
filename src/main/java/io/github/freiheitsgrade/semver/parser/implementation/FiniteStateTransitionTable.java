package io.github.freiheitsgrade.semver.parser.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class FiniteStateTransitionTable {
    private State currentState;
    private Collection<FiniteStateTransition> transitions = new ArrayList<>();
    private List<State> reachedStates = new ArrayList<>(6);

    ToStateStep from(State startState) {
        return new ToStateStep(startState);
    }

    void startFrom(State startState) {
        if (this.currentState != null) {
            throw new IllegalStateException("Start state has already been set");
        }

        setNewState('s', startState);
    }

    State getCurrentState() {
        return currentState;
    }

    void accept(char currentChar) throws NoTransitionFoundException {
        if (getCurrentState().isErrorState() || getCurrentState().isFinalState()) {
            throw new TerminalNodeReachedException(getCurrentState());
        }

        Collection<FiniteStateTransition> transitions = findAllTransitionsForCurrentState();

        FiniteStateTransition transition = transitions
                .stream()
                .filter(finiteStateTransition -> finiteStateTransition.accepts(currentChar))
                .findFirst()
                .orElseThrow(() -> new NoTransitionFoundException(getCurrentState(), currentChar));

        setNewState(currentChar, transition.getTargetState());
    }

    private void setNewState(char input, State newState) {
        // used only for debugging [System.out.println("'" + input + "' : " +getCurrentState() + " -> " + newState);
        this.currentState = newState;
        reachedStates.add(newState);
    }

    List<State> getReachedStates() {
        return Collections.unmodifiableList(reachedStates);
    }

    private Collection<FiniteStateTransition> findAllTransitionsForCurrentState() {
        return transitions
                .stream()
                .filter(finiteStateTransition -> finiteStateTransition.getStartState() == getCurrentState())
                .toList();
    }

    void addTransition(FiniteStateTransition transition) {
        this.transitions.add(transition);
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
        private final State targetState;
        private final State startState;

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
