package chess.interfaces;
import chess.enums.LocationX;

/**
 * IntFigure.java
 * Defines the movement contract used by the Figure hierarchy.
 *
 * @author Aredth Sanchez
 */
public interface IntFigure {
    /**
     * Declares the movement check required of implementing classes.
     *
     * @param x column to validate or use as the destination.
     * @param y row to validate or use as the destination.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    public boolean moveTo(LocationX x, int y);
}
