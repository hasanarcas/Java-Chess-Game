package hasanArcasChess;

public class King extends Piece{
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean isValid = false;
        Square targetLocation = location.BoardSelector().SquareSelector(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        if (this.location.isAtSameColumn(targetLocation)){
            if (color == ChessBoard.WHITE && rowDistance == 1){
                isValid = targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color;
            }
            if (color == ChessBoard.BLACK && rowDistance == -1){
                isValid = targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color;
            }
        }

        if (this.location.isAtSameRow(targetLocation)){
            if (colDistance == 1 ||colDistance == -1) {
                isValid = targetLocation.isEmpty() || targetLocation.PieceSelecter().ColorSelecter() != this.color;
            }
        }

        if (this.location.isDiagonal(targetLocation)){
            if ((Math.abs(targetLocation.getRow() - location.getRow()) == 1) && (Math.abs(targetLocation.getColumn() - location.getColumn()) == 1)){
                isValid = (targetLocation.isEmpty() || (targetLocation.PieceSelecter().ColorSelecter() != this.color));
            }

        }


        return isValid;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
