package chess.exceptions;

/**
 * InvalidMoveException.java
 * Represents a movement pattern that is invalid for the selected piece.
 *
 * @author Aredth Sanchez
 */
public class InvalidMoveException extends RuntimeException {
    /**
     * Creates this exception with the supplied error message.
     *
     * @param message description of the error passed to RuntimeException.
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}
