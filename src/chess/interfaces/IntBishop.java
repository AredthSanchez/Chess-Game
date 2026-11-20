package chess.interfaces;
import chess.enums.LocationX;

/**
 * IntBishop.java
 * Defines a diagonal movement contract independently of IntFigure.
 *
 * @author Aredth Sanchez
 */
public interface IntBishop {
    /**
     * Declares the movement check required of implementing classes.
     *
     * @param x column to validate or use as the destination.
     * @param y row to validate or use as the destination.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    public boolean moveToBishop(LocationX x, int y);
}
