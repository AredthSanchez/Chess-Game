package chess.figures;

import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidMoveException;

/**
 * Rook.java
 * Represents a rook that moves horizontally or vertically to a different square.
 *
 * @author Aredth Sanchez
 */
public class Rook extends Figure {

    /**
     * Creates a Rook object using the supplied piece type, color, column, and row.
     *
     * @param pieceName chess piece type stored in the object.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidPieceException piece type is null.
     * @throws IllegalArgumentException color is null.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     */
    public Rook(ChessPieceType pieceName, ChessColor color, LocationX column, int row) {
        super(pieceName, color, column, row);
    }

    /**
     * Checks a horizontal or vertical move to a different square.
     *
     * @param targetColumn destination column as a LocationX value.
     * @param targetRow destination row number.
     * @return true for a valid move; invalid moves throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException target column is null or target row is outside 1-8.
     * @throws chess.exceptions.InvalidMoveException target does not follow the permitted movement rule.
     */
    @Override
    public boolean moveTo(LocationX targetColumn, int targetRow) throws InvalidMoveException {
        board.verifyCoordinate(targetColumn, targetRow);

        if (targetColumn == getColumn() && targetRow == getRow()) {
            throw new InvalidMoveException("The target must differ from the starting position.");
        }

        if (targetColumn == getColumn() || targetRow == getRow()) {
            return true;
        }

        throw new InvalidMoveException("Rook must move horizontally or vertically.");
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
