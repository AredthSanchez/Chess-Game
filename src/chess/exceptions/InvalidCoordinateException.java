package chess.exceptions;

/**
 * InvalidCoordinateException.java
 * Represents a null column or row outside the board boundaries.
 *
 * @author Aredth Sanchez
 */
public class InvalidCoordinateException extends RuntimeException {
    /**
     * Creates this exception with the supplied error message.
     *
     * @param message description of the error passed to RuntimeException.
     */
    public InvalidCoordinateException(String message) {
        super(message);
    }
}
