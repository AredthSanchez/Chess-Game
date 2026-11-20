package chess.board;
import chess.enums.LocationX;
import chess.exceptions.InvalidCoordinateException;
import chess.interfaces.IntChessBoard;

/**
 * ChessBoard.java
 * Validates that enum-based columns and integer rows identify positions within the board.
 *
 * @author Aredth Sanchez
 */
public class ChessBoard implements IntChessBoard {
    /**
     * Creates a chessboard coordinate validator.
     *
     */
    public ChessBoard() {
    }

    /**
     * Smallest valid chessboard row number.
     */
    public static final int MIN_ROW = 1;
    /**
     * Largest valid chessboard row number.
     */
    public static final int MAX_ROW = 8;

    /**
     * Checks that the column is non-null and the row is within 1 through 8.
     *
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @return true if the coordinates are valid; invalid coordinates throw an exception.
     * @throws chess.exceptions.InvalidCoordinateException supplied coordinate is invalid.
     */
    @Override
    public boolean verifyCoordinate(LocationX column, int row) {
        if (column == null || (row < MIN_ROW || row > MAX_ROW)) {
            throw new InvalidCoordinateException("Invalid coordinates. Piece cannot fit inside ChessBoard.");
        }
        return true;
    }
}
