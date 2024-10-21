package io.github.freiheitstools.semver.parser.implementation;

class CharCharTuple {
    private final char predecessor;
    private final char successor;

    CharCharTuple(char predecessor, char successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    char getPredecessor() {
        return predecessor;
    }

    char getSuccessor() {
        return successor;
    }

    static CharCharTuple newTuple(char predecessor, char successor) {
        return new CharCharTuple(predecessor, successor);
    }
}
