/**
 * @author: Lam Nguyen
 * a JUnit to test the chess game
 */

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Project3Tester {

    // shortcut for calling the north direction of player side
    private ChessGame.Side north = ChessGame.Side.SOUTH;

    // shortcut for calling the south direction of player side
    private ChessGame.Side south = ChessGame.Side.NORTH;

    // shortcut for calling the east direction of player side
    private ChessGame.Side east = ChessGame.Side.EAST;

    // shortcut for calling the west direction of player side
    private ChessGame.Side west = ChessGame.Side.WEST;

    // initialize the complete chess board south->north
    public ChessBoard init() {
        // initialize european chess rule
        EuropeanChess europeanChess = new EuropeanChess(south);
        // initialize european chess display
        EuropeanChessDisplay europeanChessDisplay = new EuropeanChessDisplay();
        // initialize chess board
        ChessBoard chessBoard = new ChessBoard(8, 8, europeanChessDisplay, europeanChess);

        // add PawnPiece
        for (int i = 0; i < 8; ++i) {
            chessBoard.addPiece(new PawnPiece(north, chessBoard), 1, i);
            chessBoard.addPiece(new PawnPiece(south, chessBoard), 6, i);
        }
        // add KingPiece
        chessBoard.addPiece(new KingPiece(north, chessBoard), 0, 4);
        chessBoard.addPiece(new KingPiece(south, chessBoard), 7, 4);

        // add QueenPiece
        chessBoard.addPiece(new QueenPiece(north, chessBoard), 0, 3);
        chessBoard.addPiece(new QueenPiece(south, chessBoard), 7, 3);

        // add BishopPiece
        chessBoard.addPiece(new BishopPiece(north, chessBoard), 0, 2);
        chessBoard.addPiece(new BishopPiece(north, chessBoard), 0, 5);
        chessBoard.addPiece(new BishopPiece(south, chessBoard), 7, 2);
        chessBoard.addPiece(new BishopPiece(south, chessBoard), 7, 5);

        // add KnightPiece
        chessBoard.addPiece(new KnightPiece(north, chessBoard), 0, 1);
        chessBoard.addPiece(new KnightPiece(north, chessBoard), 0, 6);
        chessBoard.addPiece(new KnightPiece(south, chessBoard), 7, 1);
        chessBoard.addPiece(new KnightPiece(south, chessBoard), 7, 6);

        // add RookPiece
        chessBoard.addPiece(new RookPiece(north, chessBoard), 0, 0);
        chessBoard.addPiece(new RookPiece(north, chessBoard), 0, 7);
        chessBoard.addPiece(new RookPiece(south, chessBoard), 7, 0);
        chessBoard.addPiece(new RookPiece(south, chessBoard), 7, 7);

        return chessBoard;
    }

    // overloading method init by adding firstPlayer
    public ChessBoard init(ChessGame.Side firstPlayer) {
        // initialize european chess rule
        EuropeanChess europeanChess = new EuropeanChess(firstPlayer);
        // initialize european chess display
        EuropeanChessDisplay europeanChessDisplay = new EuropeanChessDisplay();
        // initialize chess board
        ChessBoard chessBoard = new ChessBoard(8, 8, europeanChessDisplay, europeanChess);

        return chessBoard;
    }

    /** TEST SIDE */

    // compare the side of all the pawns on the table to the correct side
    @Test
    public void testPawnSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // compare the side of the pawn on the table to the correct side
        for (int i = 0; i < 8; ++i) {
            assertEquals(north, chessBoard.getPiece(1, i).getSide());
            assertEquals(south, chessBoard.getPiece(6, i).getSide());
        }
    }

    // compare the side of all the rooks on the table to the correct side
    @Test
    public void testRookSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(north, chessBoard.getPiece(0, 0).getSide());
        assertEquals(north, chessBoard.getPiece(0, 7).getSide());
        assertEquals(south, chessBoard.getPiece(7, 0).getSide());
        assertEquals(south, chessBoard.getPiece(7, 7).getSide());
    }

    // compare the side of all the knights on the table to the correct side
    @Test
    public void testKnightSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(north, chessBoard.getPiece(0, 1).getSide());
        assertEquals(north, chessBoard.getPiece(0, 6).getSide());
        assertEquals(south, chessBoard.getPiece(7, 1).getSide());
        assertEquals(south, chessBoard.getPiece(7, 6).getSide());
    }

    // compare the side of all the bishops on the table to the correct side
    @Test
    public void testBishopSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(north, chessBoard.getPiece(0, 2).getSide());
        assertEquals(north, chessBoard.getPiece(0, 5).getSide());
        assertEquals(south, chessBoard.getPiece(7, 2).getSide());
        assertEquals(south, chessBoard.getPiece(7, 5).getSide());
    }

    // compare the side of all the queens on the table to the correct side
    @Test
    public void testQueenSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(north, chessBoard.getPiece(0, 3).getSide());
        assertEquals(south, chessBoard.getPiece(7, 3).getSide());
    }

    // compare the side of all the kings on the table to the correct side
    @Test
    public void testKingSide() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(north, chessBoard.getPiece(0, 4).getSide());
        assertEquals(south, chessBoard.getPiece(7, 4).getSide());
    }

    /** TEST LABEL */

    // compare the label of all the pawns on the table to the correct label
    @Test
    public void testPawnLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // compare the label of the pawn on the table to the correct label
        for (int i = 0; i < 8; ++i) {
            assertEquals("P", chessBoard.getPiece(1, i).getLabel());
            assertEquals("P", chessBoard.getPiece(6, i).getLabel());
        }
    }

    // compare the label of all the rooks on the table to the correct label
    @Test
    public void testRookLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals("R", chessBoard.getPiece(0, 0).getLabel());
        assertEquals("R", chessBoard.getPiece(0, 7).getLabel());
        assertEquals("R", chessBoard.getPiece(7, 0).getLabel());
        assertEquals("R", chessBoard.getPiece(7, 7).getLabel());
    }

    // compare the label of all the knights on the table to the correct label
    @Test
    public void testKnightLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals("N", chessBoard.getPiece(0, 1).getLabel());
        assertEquals("N", chessBoard.getPiece(0, 6).getLabel());
        assertEquals("N", chessBoard.getPiece(7, 1).getLabel());
        assertEquals("N", chessBoard.getPiece(7, 6).getLabel());
    }

    // compare the label of all the bishops on the table to the correct label
    @Test
    public void testBishopLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals("B", chessBoard.getPiece(0, 2).getLabel());
        assertEquals("B", chessBoard.getPiece(0, 5).getLabel());
        assertEquals("B", chessBoard.getPiece(7, 2).getLabel());
        assertEquals("B", chessBoard.getPiece(7, 5).getLabel());
    }

    // compare the label of all the queens on the table to the correct label
    @Test
    public void testQueenLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals("Q", chessBoard.getPiece(0, 3).getLabel());
        assertEquals("Q", chessBoard.getPiece(7, 3).getLabel());
    }

    // compare the label of all the kings on the table to the correct label
    @Test
    public void testKingLabel() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals("K", chessBoard.getPiece(0, 4).getLabel());
        assertEquals("K", chessBoard.getPiece(7, 4).getLabel());
    }

    /** TEST ICON */

    // compare the icons of all the pieces on the table to the correct icons
    @Test
    public void testNullIcon() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // compare the icon of the piece on the table to the correct icon
        for (int i = 0; i < 8; ++i) {
            assertEquals(null, chessBoard.getPiece(0, i).getIcon());
            assertEquals(null, chessBoard.getPiece(1, i).getIcon());
            assertEquals(null, chessBoard.getPiece(6, i).getIcon());
            assertEquals(null, chessBoard.getPiece(7, i).getIcon());
        }
    }

    /** TEST POSITION */

    // compare the positions of all the pawns on the table to the correct positions
    @Test
    public void testPawnPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // compare the position of the pawn on the table to the correct position
        for (int i = 0; i < 8; ++i) {
            assertEquals(1, chessBoard.getPiece(1, i).getRow());
            assertEquals(i, chessBoard.getPiece(1, i).getColumn());

            assertEquals(6, chessBoard.getPiece(6, i).getRow());
            assertEquals(i, chessBoard.getPiece(6, i).getColumn());
        }
    }

    // compare the positions of all the rooks on the table to the correct positions
    @Test
    public void testRookPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(0, chessBoard.getPiece(0, 0).getRow());
        assertEquals(0, chessBoard.getPiece(0, 0).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 7).getRow());
        assertEquals(7, chessBoard.getPiece(0, 7).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 0).getRow());
        assertEquals(0, chessBoard.getPiece(7, 0).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 7).getRow());
        assertEquals(7, chessBoard.getPiece(7, 7).getColumn());
    }

    // compare the positions of all the knights on the table to the correct positions
    @Test
    public void testKnightPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(0, chessBoard.getPiece(0, 1).getRow());
        assertEquals(1, chessBoard.getPiece(0, 1).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 6).getRow());
        assertEquals(6, chessBoard.getPiece(0, 6).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 1).getRow());
        assertEquals(1, chessBoard.getPiece(7, 1).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 6).getRow());
        assertEquals(6, chessBoard.getPiece(7, 6).getColumn());
    }

    // compare the positions of all the bishops on the table to the correct positions
    @Test
    public void testBishopPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(0, chessBoard.getPiece(0, 2).getRow());
        assertEquals(2, chessBoard.getPiece(0, 2).getColumn());

        assertEquals(0, chessBoard.getPiece(0, 5).getRow());
        assertEquals(5, chessBoard.getPiece(0, 5).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 2).getRow());
        assertEquals(2, chessBoard.getPiece(7, 2).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 5).getRow());
        assertEquals(5, chessBoard.getPiece(7, 5).getColumn());
    }

    // compare the positions of all the queens on the table to the correct positions
    @Test
    public void testQueenPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(0, chessBoard.getPiece(0, 3).getRow());
        assertEquals(3, chessBoard.getPiece(0, 3).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 3).getRow());
        assertEquals(3, chessBoard.getPiece(7, 3).getColumn());
    }

    // compare the positions of all the kings on the table to the correct positions
    @Test
    public void testKingPosition() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        assertEquals(0, chessBoard.getPiece(0, 4).getRow());
        assertEquals(4, chessBoard.getPiece(0, 4).getColumn());

        assertEquals(7, chessBoard.getPiece(7, 4).getRow());
        assertEquals(4, chessBoard.getPiece(7, 4).getColumn());
    }

    /** TEST CONSTRUCTOR */
    @Test
    public void testChessPieceConstructor() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // test pawn constructor
        PawnPiece pawn = new PawnPiece(north, chessBoard);
        assertEquals(north, pawn.getSide());
        assertEquals("P", pawn.getLabel());

        // test rook constructor
        RookPiece rook = new RookPiece(south, chessBoard);
        assertEquals(south, rook.getSide());
        assertEquals("R", rook.getLabel());

        // test king constructor
        KingPiece king = new KingPiece(east, chessBoard);
        assertEquals(east, king.getSide());
        assertEquals("K", king.getLabel());

        // test queen constructor
        QueenPiece queen = new QueenPiece(west, chessBoard);
        assertEquals(west, queen.getSide());
        assertEquals("Q", queen.getLabel());

        // test knight constructor
        KnightPiece knight = new KnightPiece(north, chessBoard);
        assertEquals(north, knight.getSide());
        assertEquals("N", knight.getLabel());

        // test bishop constructor
        BishopPiece bishop = new BishopPiece(south, chessBoard);
        assertEquals(south, bishop.getSide());
        assertEquals("B", bishop.getLabel());
    }

    /** TEST BOARD */
    @Test
    public void testBoard() {
        // initialize the complete chess board
        ChessBoard chessBoard = init();

        // test north board
        PawnPiece northBoard = new PawnPiece(north, chessBoard);
        assertEquals(north, northBoard.getSide());

        // test south board
        PawnPiece southBoard = new PawnPiece(south, chessBoard);
        assertEquals(south, southBoard.getSide());

        // test east board
        PawnPiece eastBoard = new PawnPiece(east, chessBoard);
        assertEquals(east, eastBoard.getSide());

        // test west board
        PawnPiece westBoard = new PawnPiece(west, chessBoard);
        assertEquals(west, westBoard.getSide());
    }

    /** TEST FIRST PLAYER */
    @Test
    public void testFirstPlayer() {
        // test north being the first player
        ChessBoard northBoard = init(north);
        assertEquals(north, northBoard.getGameRules().getFirstPlayer());

        // test south being the first player
        ChessBoard southBoard = init(south);
        assertEquals(south, southBoard.getGameRules().getFirstPlayer());

        // test east being the first player
        ChessBoard eastBoard = init(east);
        assertEquals(east, eastBoard.getGameRules().getFirstPlayer());

        // test west being the first player
        ChessBoard westBoard = init(west);
        assertEquals(west, westBoard.getGameRules().getFirstPlayer());
    }

}