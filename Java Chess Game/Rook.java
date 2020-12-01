package hasanArcasChess;

public class Rook extends Piece {

    public Rook(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean isValid = false;
        Square targetLocation = location.BoardSelector().SquareSelector(to);

        if (this.location.isAtSameColumn(targetLocation)){

            Square[] between = location.BoardSelector().getSquaresBetween(location,targetLocation);
            boolean totalEmpty = true;
            if (between != null){
                for (Square sq : between){
                    if (!sq.isEmpty()){
                        totalEmpty = false;
                    }
                }
            }
            isValid = (targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color) && totalEmpty;

        }
        if (this.location.isAtSameRow(targetLocation)){
            Square[] between = location.BoardSelector().getSquaresBetween(location,targetLocation);
            boolean totalEmpty = true;
            if (between != null){
                for (Square sq : between){
                    if (!sq.isEmpty()){
                        totalEmpty = false;
                    }
                }
            }
            isValid = (targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color) && totalEmpty;
        }

        return isValid;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}
