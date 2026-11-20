package chess.interfaces;
import chess.enums.LocationX;

/**
 * IntChessBoard.java
 * Defines the contract for validating chessboard coordinates.
 *
 * @author Aredth Sanchez
 */
public interface IntChessBoard {
    /**
     * Checks that the column is non-null and the row is within 1 through 8.
     *
     * @param x column to validate or use as the destination.
     * @param y row to validate or use as the destination.
     * @return true if the coordinates are valid; invalid coordinates throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException supplied coordinate is invalid.
     */
    public boolean verifyCoordinate(LocationX x, int y);
}
