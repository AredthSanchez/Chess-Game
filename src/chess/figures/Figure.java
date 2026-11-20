package chess.figures;
import chess.board.ChessBoard;
import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidCoordinateException;
import chess.exceptions.InvalidPieceException;
import chess.interfaces.IntFigure;

/**
 * Figure.java
 * Abstract superclass that stores shared piece attributes and implements IntFigure without providing movement behavior.
 *
 * @author Aredth Sanchez
 */
public abstract class Figure implements IntFigure {
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
    protected ChessBoard board = new ChessBoard();

    /**
     * Creates a Figure object using the supplied piece type, color, column, and row.
     *
     * @param pieceName chess piece type stored in the object.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @throws chess.exceptions.InvalidPieceException piece type is null.
     * @throws IllegalArgumentException color is null.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     */
    public Figure(ChessPieceType pieceName, ChessColor color, LocationX column, int row) {
        if (pieceName == null) {
            throw new InvalidPieceException("Invalid Piece.");
        }

        if (color == null || (color != ChessColor.BLACK) && color != ChessColor.WHITE) {
            throw new IllegalArgumentException();
        }

        board.verifyCoordinate(column, row);
        this.pieceName = pieceName;
        this.color = color;
        setColumn(column);
        setRow(row);

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
     * Returns the stored piece type.
     *
     * @return The ChessPieceType value for this piece.
     */
    public ChessPieceType getPieceName() {
        return this.pieceName;
    }

    
    /**
     * Returns the stored piece color.
     *
     * @return The ChessColor value for this piece.
     */
    public ChessColor getColor() {
        return this.color;
    }

    
    /**
     * Returns the current column.
     *
     * @return The current LocationX value.
     */
    public LocationX getColumn() {
        return this.column;
    }

    
    /**
     * Returns the current row.
     *
     * @return The current row number.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Builds a readable description of the piece type, color, column, and row.
     *
     * @return A String describing this piece.
     */
    @Override
    public String toString(){
        return "Piece Type: " + this.pieceName + ", Piece Color: " + this.color + ", Piece Column: " + this.column + ", Piece Row: " + this.row;
    }
}
