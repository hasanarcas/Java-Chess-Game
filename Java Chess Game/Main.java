package hasanArcasChess;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ChessBoard Board = new ChessBoard();
        //print the board on the screen
        Scanner readers = new Scanner(System.in);
        //Create a ChessBoard
        System.out.println(Board);

        //If the game is not ended request from the user a movement
        while (!Board.isFinished()) {
            Piece p = null;
            System.out.println("It is " + (Board.isWhiteTurn() ? "White" : "Black") + "'s turn");
            //request coordinates
            //If given coordinates doesn't have the piece of the player selected
            do {
                System.out.print("Enter the location of the piece:");
                String inp = readers.next();
                //get the piece for the given location
                // null means that the Square is not in use
                p = Board.PieceGetter(inp);
                //piece color and current player's color should be consistent
            } while (p == null || p.ColorSelecter() != (Board.isWhiteTurn() ? ChessBoard.WHITE : ChessBoard.BLACK));


            String target = null;
            //If the target coordinate isn't a valid Square to move, ask for new coordinates
            do {
                System.out.print("Enter the new location of the piece:");
                target = readers.next();
            } while (!p.canMove(target));

            //move the piece in use into the target location
            p.move(target);

            //print the Chess board after each move
            System.out.println(Board);
        }
        readers.close();
    }
}