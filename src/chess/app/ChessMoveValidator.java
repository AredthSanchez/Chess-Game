package chess.app;

import chess.board.ChessBoard;
import chess.enums.ChessColor;
import chess.enums.ChessPieceType;
import chess.enums.LocationX;
import chess.exceptions.InvalidCoordinateException;
import chess.exceptions.InvalidMoveException;
import chess.exceptions.InvalidPieceException;
import chess.figures.Bishop;
import chess.figures.Figure;
import chess.figures.King;
import chess.figures.Knight;
import chess.figures.Pawn;
import chess.figures.Queen;
import chess.figures.Rook;
import java.util.Scanner;



/**
 * ChessMoveValidator.java
 * Controls input, prevents duplicate piece types, creates five Figure objects and a separate Bishop, checks their moves, and displays results.
 * Authors: Aredth Sanchez, Sofia Vargas, Ben Silva
 *
 * @author Aredth Sanchez, Sofia Vargas, Ben Silva
 * @version 1.0
 * @since 2026-10-20
 * @see chess.board.ChessBoard
 */
public class ChessMoveValidator {

    /**
     * Creates the application controller.
     *
     */
    public ChessMoveValidator() {
    }

    /**
     * Chessboard instance used to validate positions and coordinates.
     */
    private static final ChessBoard board = new ChessBoard();
    /**
     * Array holding the five non-bishop chess pieces.
     */
    private static final Figure[] pieceArray = new Figure[5];
    /**
     * Separate bishop selected by the user.
     */
    private static Bishop bishop;
    /**
     * Column of the currently entered starting square.
     */
    private static LocationX startColumn;
    /**
     * Row of the currently entered starting square.
     */
    private static int startRow;
    /**
     * Column of the destination square.
     */
    private static LocationX targetColumn;
    /**
     * Row of the destination square.
     */
    private static int targetRow;

    /**
     * Starts the program, collects six distinct pieces, checks their movement, and closes the input reader.
     *
     * @param args command-line arguments; not used.
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int nextFigureIndex = 0;

        for (int i = 0; i < 6; ++i) {

            ChessPieceType pieceType = getPieceType(input);

            while (isDuplicatePiece(pieceArray, bishop, pieceType)) {
                System.out.println("That piece type has already been selected. Try again.");
                pieceType = getPieceType(input);
            }

            ChessColor color = getColor(input);
            getPiecePosition(input, board);

            if (pieceType == ChessPieceType.BISHOP) {
                bishop = new Bishop(pieceType, color, startColumn, startRow);
            } else {
                pieceArray[nextFigureIndex] = createPiece(pieceType, color, startColumn, startRow);
                ++nextFigureIndex;
            }
        }

        getTargetPosition(input, board);

        for (Figure piece : pieceArray) {
            try {
                boolean canMove = piece.moveTo(targetColumn, targetRow);
                printMoveResult(piece.toString(), targetColumn, targetRow, canMove);
            } catch (InvalidMoveException e) {
                printMoveResult(piece.toString(), targetColumn, targetRow, false);
                System.out.println(e.getMessage());
            } catch (InvalidCoordinateException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            boolean canMove = bishop.moveToBishop(targetColumn, targetRow);
            printMoveResult(bishop.toString(), targetColumn, targetRow, canMove);
        } catch (InvalidMoveException e) {
            printMoveResult(bishop.toString(), targetColumn, targetRow, false);
            System.out.println(e.getMessage());
        } catch (InvalidCoordinateException e) {
            System.out.println(e.getMessage());
        }

        input.close();
    }

    /**
     * Prompts until the user enters a supported piece type.
     *
     * @param input Scanner used to read user input.
     * @return A valid ChessPieceType value.
     */
    public static ChessPieceType getPieceType(Scanner input) {
        while (true) {

            System.out.println(
                "Select Piece Type (PAWN, ROOK, BISHOP, KNIGHT, KING, or QUEEN): "
            );

            String pieceType = input.nextLine().trim().toUpperCase();

            try{
                return parsePieceType(pieceType);
            } catch (InvalidPieceException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Converts normalized piece-name text into an enum value.
     *
     * @param text piece-name text that has already been trimmed and converted to uppercase.
     * @return The matching ChessPieceType value.
     * @throws chess.exceptions.InvalidPieceException text does not match a supported piece name.
     * @see chess.enums.ChessPieceType
     */
    public static ChessPieceType parsePieceType(String text) {

        try {
            return ChessPieceType.valueOf(text);
        } catch (IllegalArgumentException e) {
            throw new InvalidPieceException(
                "Invalid piece type. Enter PAWN, ROOK, BISHOP, KNIGHT, KING, or QUEEN."
            );
        }
    }

    /**
     * Checks the Figure array and separate Bishop for a previously selected type.
     *
     * @param pieceArray array of Figure objects already collected; unfilled elements may be null.
     * @param bishop separate Bishop object, or null if it has not been collected.
     * @param piece piece type to check for duplication.
     * @return true if the type has already been selected; false otherwise.
     */
    public static boolean isDuplicatePiece(Figure[] pieceArray, Bishop bishop, ChessPieceType piece) {

        if (piece == ChessPieceType.BISHOP && bishop != null) {
            return true;
        }

        for (Figure chessPiece : pieceArray) {
            if (chessPiece != null && chessPiece.getPieceName() == piece) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prompts until the user enters WHITE or BLACK.
     *
     * @param input Scanner used to read user input.
     * @return A valid ChessColor value.
     */
    public static ChessColor getColor(Scanner input) {

        while (true) {

            System.out.println("Select Piece Color (WHITE or BLACK): ");
            String color = input.nextLine().trim().toUpperCase();

            try {
                return ChessColor.valueOf(color);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Color. Enter WHITE or BLACK. Try again.");
            }
        }
    }

    /**
     * Prompts for valid target coordinates and stores them in targetColumn and targetRow.
     *
     * @param input Scanner used to read user input.
     * @param board ChessBoard used to validate positions.
     */
    public static void getTargetPosition(Scanner input, ChessBoard board) {

        boolean validPosition = false;

        while (!validPosition) {

            System.out.print("Enter target column (a-h): ");
            String columnInput = input.nextLine().trim();

            System.out.print("Enter target row (1-8): ");
            String rowInput = input.nextLine().trim();

            if (columnInput.length() == 1) {

                try {
                    LocationX column = LocationX.valueOf(columnInput.toUpperCase());
                    int row = Integer.parseInt(rowInput);

                    if (board.verifyCoordinate(column, row)) {
                        targetColumn = column;
                        targetRow = row;
                        validPosition = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid row. Enter a whole number from 1 to 8.");
                } catch (InvalidCoordinateException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid column. Enter one letter from A through H.");
                }

            } else {
                System.out.println("Invalid column. Enter one letter from A through H.");
            }
        }
    }

    /**
     * Prompts for valid starting coordinates and stores them in startColumn and startRow.
     *
     * @param input Scanner used to read user input.
     * @param board ChessBoard used to validate positions.
     */
    public static void getPiecePosition(Scanner input, ChessBoard board) {

        boolean validPosition = false;

        while (!validPosition) {

            System.out.print("Enter start column (a-h): ");
            String columnInput = input.nextLine().trim();

            System.out.print("Enter start row (1-8): ");
            String rowInput = input.nextLine().trim();

            if (columnInput.length() == 1) {

                try {
                    LocationX column = LocationX.valueOf(columnInput.toUpperCase());
                    int row = Integer.parseInt(rowInput);

                    if (board.verifyCoordinate(column, row)) {
                        startColumn = column;
                        startRow = row;
                        validPosition = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid row. Enter a whole number from 1 to 8.");
                } catch (InvalidCoordinateException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid column. Enter one letter from A through H.");
                }

            } else {
                System.out.println("Invalid column. Enter one letter from A through H.");
            }
        }
    }

    /**
     * Creates a Figure subclass for the selected type; Bishop is created separately in main.
     *
     * @param pieceType selected chess piece type.
     * @param color piece color as a ChessColor value.
     * @param column column of the position as a LocationX value.
     * @param row row number of the position.
     * @return A Pawn, Rook, Knight, King, or Queen object as a Figure.
     * @throws chess.exceptions.InvalidPieceException type is null, BISHOP, or otherwise unsupported.
     * @throws chess.exceptions.InvalidCoordinateException starting coordinates are invalid.
     * @throws IllegalArgumentException color is null.
     */
    public static Figure createPiece(ChessPieceType pieceType, ChessColor color, LocationX column, int row) {
        
        if (pieceType == null) {
            throw new InvalidPieceException("Piece type cannot be null.");
        }

        switch (pieceType) {
            case PAWN:
                return new Pawn(pieceType, color, column, row);
            
            case ROOK:
                return new Rook(pieceType, color, column, row);
            
            case KNIGHT:
                return new Knight(pieceType, color, column, row);
            
            case KING:
                return new King(pieceType, color, column, row);
            
            case QUEEN:
                return new Queen(pieceType, color, column, row);
            
            default:
                throw new InvalidPieceException(
                    "This piece type cannot be created as a Figure: " + pieceType
                );
        }
    }

    /**
     * Displays the piece description, target position, and movement result.
     *
     * @param description readable description of the piece.
     * @param targetColumn destination column as a LocationX value.
     * @param targetRow destination row number.
     * @param result true for a valid move; false for an invalid move.
     */
    public static void printMoveResult(String description, LocationX targetColumn, int targetRow, boolean result) {

        System.out.println(description + (result ? " can move to target position " : " cannot move to target position ") + targetColumn + targetRow);
    }
}
