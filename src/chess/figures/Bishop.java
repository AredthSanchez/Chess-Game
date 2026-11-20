package chess.figures;

import chess.board.ChessBoard;
import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidCoordinateException;
import chess.exceptions.InvalidMoveException;
import chess.exceptions.InvalidPieceException;
import chess.interfaces.IntBishop;

/**
 * Bishop.java
 * Implements diagonal movement independently of Figure and is checked separately from the Figure array.
 *
 * @author Aredth Sanchez
 */
public class Bishop implements IntBishop {

    /**
     * Type of this chess piece.
     */
    private ChessPieceType pieceName;
    /**
     * Color assigned to this chess piece.
     */
    private ChessColor color;
    /**
     * Current chessboard column of this piece.
     */
    private LocationX column;
    /**
     * Current chessboard row of this piece.
     */
    private int row;

    /**
     * Chessboard instance used to validate positions and coordinates.
     */
    private final ChessBoard board = new ChessBoard();

    /**
     * Creates a Bishop object using the supplied piece type, color, column, and row.
     *
     * @param pieceName chess piece type stored in the object.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidPieceException piece type is null.
     * @throws IllegalArgumentException color is null.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     */
    public Bishop(ChessPieceType pieceName, ChessColor color, LocationX column, int row) {
        if (pieceName != ChessPieceType.BISHOP) {
            throw new InvalidPieceException("Bishop requires the BISHOP piece type.");
        }

        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null.");
        }

        board.verifyCoordinate(column, row);

        this.pieceName = pieceName;
        this.color = color;
        this.column = column;
        this.row = row;
    }

    /**
     * Returns the stored piece type.
     *
     * @return The ChessPieceType value for this piece.
     */
    public ChessPieceType getPieceName() {
        return pieceName;
    }

    /**
     * Returns the stored piece color.
     *
     * @return The ChessColor value for this piece.
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * Returns the current column.
     *
     * @return The current LocationX value.
     */
    public LocationX getColumn() {
        return column;
    }

    /**
     * Returns the current row.
     *
     * @return The current row number.
     */
    public int getRow() {
        return row;
    }

    /**
     * Validates and updates the stored column.
     *
     * @param column column of the position as a LocationX value.
     * @throws chess.exceptions.InvalidCoordinateException supplied coordinate is invalid.
     */
    public void setColumn(LocationX column) {
        if (column == null) {
            throw new InvalidCoordinateException("Column cannot be null.");
        }

        this.column = column;
    }

    /**
     * Validates and updates the stored row.
     *
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidCoordinateException supplied coordinate is invalid.
     */
    public void setRow(int row) {
        if (row < ChessBoard.MIN_ROW || row > ChessBoard.MAX_ROW) {
            throw new InvalidCoordinateException("Row must be 1-8.");
        }

        this.row = row;
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

        throw new InvalidMoveException("Bishop must move diagonally to a different square.");
    }

    /**
     * Builds a readable description of the piece type, color, column, and row.
     *
     * @return A String describing this piece.
     */
    @Override
    public String toString() {
        return "Piece Type: " + pieceName + ", Piece Color: " + color + ", Piece Column: " + column + ", Piece Row: " + row;
    }
}