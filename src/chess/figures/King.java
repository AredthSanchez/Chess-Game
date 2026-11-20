package chess.figures;

import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidMoveException;

/**
 * King.java
 * Extends Queen and restricts straight and diagonal movement to one square.
 *
 * @author Aredth Sanchez
 */
public class King extends Queen {

    /**
     * Creates a King object using the supplied piece type, color, column, and row.
     *
     * @param pieceName chess piece type stored in the object.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidPieceException piece type is null.
     * @throws IllegalArgumentException color is null.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     */
    public King(ChessPieceType pieceName, ChessColor color, LocationX column, int row) {
        super(pieceName, color, column, row);
    }

    /**
     * Limits movement to one square before reusing Queen movement.
     *
     * @param targetColumn destination column as a LocationX value.
     * @param targetRow destination row number.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    @Override
    public boolean moveTo(LocationX targetColumn, int targetRow) {
        board.verifyCoordinate(targetColumn, targetRow);

        int columnDifference = Math.abs(targetColumn.ordinal() - getColumn().ordinal());
        int rowDifference = Math.abs(targetRow - getRow());

        if ((columnDifference == 0 && rowDifference == 0) || columnDifference > 1 || rowDifference > 1) {
            throw new InvalidMoveException("King must move exactly one square.");
        }

        return super.moveTo(targetColumn, targetRow);
    }

    /**
     * Checks a one-square diagonal move without changing the stored position.
     *
     * @param targetColumn destination column as a LocationX value.
     * @param targetRow destination row number.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    @Override
    public boolean moveToBishop(LocationX targetColumn, int targetRow) {
        board.verifyCoordinate(targetColumn, targetRow);

        int columnDifference = Math.abs(targetColumn.ordinal() - getColumn().ordinal());
        int rowDifference = Math.abs(targetRow - getRow());

        if (columnDifference == 1 && rowDifference == 1) {
            return super.moveToBishop(targetColumn, targetRow);
        }

        throw new InvalidMoveException("King diagonal movement must be exactly one square.");
    }

    /**
     * Builds a readable description of the piece type, color, column, and row.
     *
     * @return A String describing this piece.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}