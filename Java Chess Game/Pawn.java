package hasanArcasChess;

public class Pawn extends Piece {

    //piece has not been moved yet
    boolean initialLocation = true;

    public Pawn(int color, Square location) {
        super(color, location);
    }



    @Override
    public boolean canMove(String to) {
        boolean isValid = false;
        Square targetLocation = location.BoardSelector().SquareSelector(to);
        //Get the row difference between the current location and target location.
        int rowDistance = targetLocation.getRowDistance(location);
        //if the target coordinate is at the same column

        if (this.location.isAtSameColumn(targetLocation)) {
            //for white check pawn is moving forward at most 2 Squares
            if (color == ChessBoard.WHITE && rowDistance > 0 && rowDistance <= 2) {
                if (rowDistance == 2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.BoardSelector().getSquaresBetween(location, targetLocation);
                        isValid = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    isValid = targetLocation.isEmpty();
                }
                return isValid;
                //for black check pawn is moving forward at most 2 Squares
            } else if (color == ChessBoard.BLACK && rowDistance < 0 && rowDistance >= -2) {
                if (rowDistance == -2) {
                    if (initialLocation) {
                        //pawn is moving twice, check two squares in front are empty
                        Square[] between = location.BoardSelector().getSquaresBetween(location, targetLocation);
                        isValid = targetLocation.isEmpty() && between[0].isEmpty();
                    }
                } else {
                    isValid = targetLocation.isEmpty();
                }
            }
            // if the target column is not at the same column, it should be a neighbour column
        } else if (this.location.isNeighborColumn(targetLocation)) {
            //pawn can only move to forward diagonals ig there is an opponen there(attack)
            if (color == ChessBoard.WHITE && rowDistance == 1) {
                isValid = !targetLocation.isEmpty() && targetLocation.PieceSelecter().ColorSelecter() == ChessBoard.BLACK;
            } else if (color == ChessBoard.BLACK && rowDistance == -1) {
                isValid = !targetLocation.isEmpty() && targetLocation.PieceSelecter().ColorSelecter() == ChessBoard.WHITE;
            }

        }
        return isValid;
    }

    @Override
    public void move(String to) {
        Square targetLocation = location.BoardSelector().SquareSelector(to);
        //promoteToQueen
        if (targetLocation.isAtLastRow(color)) {
            targetLocation.putNewQueen(color);
        } else {
            targetLocation.setPiece(this);
        }
        //clear previous location
        location.clear();
        //update current location
        location = targetLocation;
        location.BoardSelector().nextPlayer();
        //piece has been moved at least once
        initialLocation = false;

    }
    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "P" : "p";
    }
}
