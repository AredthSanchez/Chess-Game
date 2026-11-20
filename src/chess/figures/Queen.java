package chess.figures;

import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidMoveException;
import chess.interfaces.IntBishop;

/**
 * Queen.java
 * Extends Rook and implements IntBishop to combine straight and diagonal movement.
 *
 * @author Aredth Sanchez
 */
public class Queen extends Rook implements IntBishop {

    /**
     * Creates a Queen object using the supplied piece type, color, column, and row.
     *
     * @param pieceName chess piece type stored in the object.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidPieceException piece type is null.
     * @throws IllegalArgumentException color is null.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     */
    public Queen(ChessPieceType pieceName, ChessColor color, LocationX column, int row) {
        super(pieceName, color, column, row);
    }

    /**
     * Checks a diagonal move without changing the stored position.
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

        if (columnDifference == rowDifference && columnDifference > 0) {
            return true;
        }

        throw new InvalidMoveException("Target must be on a diagonal and differ from the start.");
    }

    /**
     * Tries Rook movement first, then diagonal movement after an InvalidMoveException.
     *
     * @param targetColumn destination column as a LocationX value.
     * @param targetRow destination row number.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    @Override
    public boolean moveTo(LocationX targetColumn, int targetRow) {
        try {
            return super.moveTo(targetColumn, targetRow);
        } catch (InvalidMoveException e) {
            return moveToBishop(targetColumn, targetRow);
        }
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