package hasanArcasChess;

public class Knight extends Piece
{
    public Knight(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean isValid = false;
        Square targetLocation = location.BoardSelector().SquareSelector(to);
        //Get the row difference between the current location and target location.
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        if((Math.abs(colDistance) == 1 && Math.abs(rowDistance) ==2)
           || (Math.abs(colDistance) == 2 && Math.abs(rowDistance) ==1)){
            if(targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color ){
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
