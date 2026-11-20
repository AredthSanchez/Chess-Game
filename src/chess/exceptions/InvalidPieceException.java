package chess.exceptions;

/**
 * InvalidPieceException.java
 * Represents an invalid piece name or unsupported piece type.
 *
 * @author Aredth Sanchez
 */
public class InvalidPieceException extends RuntimeException {
    /**
     * Creates this exception with the supplied error message.
     *
     * @param message description of the error passed to RuntimeException.
     */
    public InvalidPieceException(String message) {
        super(message);
    }  
}
