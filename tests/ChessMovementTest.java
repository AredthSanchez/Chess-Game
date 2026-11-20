import chess.app.ChessMoveValidator;
import chess.board.ChessBoard;
import chess.enums.*;
import chess.figures.*;
import chess.exceptions.*;

public class ChessMovementTest {
    private static void check(boolean value) { if (!value) throw new AssertionError("Movement check failed"); }
    private static void rejects(Class<? extends Throwable> type, Runnable action) {
        try { action.run(); } catch (Throwable error) { if (type.isInstance(error)) return; throw error; }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
    public static void main(String[] args) {
        Pawn pawn = new Pawn(ChessPieceType.PAWN, ChessColor.WHITE, LocationX.D, 4);
        Rook rook = new Rook(ChessPieceType.ROOK, ChessColor.WHITE, LocationX.D, 4);
        Bishop bishop = new Bishop(ChessPieceType.BISHOP, ChessColor.BLACK, LocationX.D, 4);
        Knight knight = new Knight(ChessPieceType.KNIGHT, ChessColor.WHITE, LocationX.D, 4);
        Queen queen = new Queen(ChessPieceType.QUEEN, ChessColor.BLACK, LocationX.D, 4);
        King king = new King(ChessPieceType.KING, ChessColor.WHITE, LocationX.D, 4);
        check(pawn.moveTo(LocationX.D, 5));
        check(new Pawn(ChessPieceType.PAWN, ChessColor.BLACK, LocationX.D, 4).moveTo(LocationX.D, 3));
        check(rook.moveTo(LocationX.D, 8)); check(bishop.moveToBishop(LocationX.H, 8));
        check(knight.moveTo(LocationX.F, 5)); check(queen.moveTo(LocationX.A, 1));
        check(queen.moveTo(LocationX.A, 4)); check(king.moveTo(LocationX.E, 5));
        rejects(InvalidMoveException.class, () -> pawn.moveTo(LocationX.D, 3));
        rejects(InvalidMoveException.class, () -> rook.moveTo(LocationX.E, 5));
        rejects(InvalidMoveException.class, () -> king.moveTo(LocationX.F, 6));
        rejects(InvalidCoordinateException.class, () -> new ChessBoard().verifyCoordinate(LocationX.A, 9));
        check(ChessMoveValidator.isDuplicatePiece(new Figure[]{rook}, bishop, ChessPieceType.ROOK));
        check(ChessMoveValidator.isDuplicatePiece(new Figure[]{rook}, bishop, ChessPieceType.BISHOP));
        check(!ChessMoveValidator.isDuplicatePiece(new Figure[]{rook}, bishop, ChessPieceType.PAWN));
        System.out.println("Chess movement smoke checks passed.");
    }
}
