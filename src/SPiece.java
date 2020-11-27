/**
 * @author: Lam Nguyen
 * a class representing all pieces that have straight movements
 */

import javax.swing.*;

public abstract class SPiece extends ChessPiece {

    // SPiece constructor that takes in label, side, and board associated with the piece
    public SPiece(String label, ChessGame.Side side, ChessBoard board) {
        super(label, side, board);
    }

    // check whether the king is moved properly
    private boolean legalKing(ChessPiece piece, int toRow, int toColumn) {
        if (piece.getLabel() == "K") {
            // check if the movement of the king exceeds 1 step
            if (Math.max(toRow, getRow()) > Math.min(toRow, getRow()) + 1 || Math.max(toColumn, getColumn()) > Math.min(toColumn, getColumn()) + 1) {
                if (legalCastle(toColumn)) {
                    // castle to the left
                    if (getColumn() > toColumn) {
                        // remove the king from the board
                        ChessPiece king = getChessBoard().removePiece(getRow(), getColumn());

                        getChessBoard().addPiece(king, toRow, toColumn);

                        // remove the rook at column 0 from the board
                        ChessPiece rook = getChessBoard().removePiece(getRow(), 0);

                        getChessBoard().addPiece(rook, getRow(), toColumn + 1);
                    } else {
                        // remove the king from the board
                        ChessPiece king = getChessBoard().removePiece(getRow(), getColumn());

                        getChessBoard().addPiece(king, toRow, toColumn);

                        // remove the rook at column 7 from the board
                        ChessPiece rook = getChessBoard().removePiece(getRow(), 7);

                        getChessBoard().addPiece(rook, getRow(), toColumn - 1);
                    }
                    return true;
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    // check whether the pawn is moved properly
    private boolean legalPawn(ChessPiece piece, int toRow, int toColumn) {
        if (piece.getLabel() == "P") {
            // check whether the movement of the pawn exceeds one step
            if (Math.max(toRow, getRow()) > Math.min(toRow, getRow()) + 1) {
                if (!piece.getPawnFirstMove()) {
                    return false;
                } else {
                    piece.setPawnFirstMove(false);
                    return Math.max(toRow, getRow()) == Math.min(toRow, getRow()) + 2;
                }
            } else {
                // check if the pawn movement is a capture move or not
                if ((piece.getSide() == ChessGame.Side.SOUTH && getRow() > toRow)
                        || (piece.getSide() == ChessGame.Side.NORTH && getRow() < toRow)) {
                    if (toColumn == getColumn() - 1 || toColumn == getColumn() + 1) {
                        return getChessBoard().hasPiece(toRow, toColumn)
                                && getChessBoard().getPiece(toRow, toColumn).getSide() != this.getSide();
                    }
                } else {
                    return false;
                }
                return true;
            }
        } else {
            return true;
        }
    }

    // upgrade the pawn when it has reach the other side of the board
    private void upgradePawn(int toRow, int toColumn) {
        // take the input of the user to update the pawn
        String s = JOptionPane.showInputDialog("Available Options: N B Q R", "input");
        // if there is nothing in the input, automatically set label a value of ' '
        char label = s != null ? s.charAt(0) : ' ';

        switch (label) {
            case 'N' -> {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new KnightPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
            case 'B' -> {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new BishopPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
            case 'Q' -> {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new QueenPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
            case 'R' -> {
                getChessBoard().removePiece(getRow(), getColumn());
                getChessBoard().addPiece(new RookPiece(getSide(), getChessBoard()), toRow, toColumn);
            }
            default -> JOptionPane.showMessageDialog(null, "INVALID PAWN UPDATE");
        }
    }

    // check whether the castle move is made properly
    private boolean legalCastle(int toColumn) {
        if (getKingFirstMove()) {
            if (getRow() == 0 || getRow() == 7) {
                boolean left = getColumn() - 2 == toColumn && getChessBoard().getPiece(getRow(), 0).getRookFirstMove();
                boolean right = getColumn() + 2 == toColumn && getChessBoard().getPiece(getRow(), 7).getRookFirstMove();
                return left || right;
            }
        }
        return false;
    }

    // check whether the piece has made a legal move
    public boolean isLegalMove(int toRow, int toColumn) {
        if (getRow() == toRow && getColumn() == toColumn) {
            return false;
        }

        // check whether to upgrade pawn or not
        if ((getChessBoard().getPiece(getRow(), getColumn()).getSide() == ChessGame.Side.SOUTH
                && toRow == 0 && getRow() == 1)
                || (getChessBoard().getPiece(getRow(), getColumn()).getSide() == ChessGame.Side.NORTH
                && toRow == 7 && getRow() == 6)) {
            if (toColumn == getColumn() - 1 || toColumn == getColumn() + 1 || toColumn == getColumn()) {
                upgradePawn(toRow, toColumn);
            }
        }

        // check whether the destination has a piece of a same side or not
        if (getChessBoard().hasPiece(toRow, toColumn)) {
            if (getChessBoard().getPiece(toRow, toColumn).getSide() == this.getSide()) {
                return false;
            }
        }

        return legalPawn(this, toRow, toColumn) && legalKing(this, toRow, toColumn)
                && (legalDiagonalPath(toRow, toColumn)
                || legalHorizontalPath(toRow, toColumn)
                || legalVerticalPath(toRow, toColumn));
    }

    // deal with actions after the move is done
    public void moveDone() {}
}