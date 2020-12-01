package hasanArcasChess;

public class Bishop extends Piece {
    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        boolean isValid = false;
        Square target = location.BoardSelector().SquareSelector(to);

        if (!location.isDiagonal(target)) {
            isValid = false;
        } else {
            Square[] btwn = location.BoardSelector().getSquaresBetween(location, target);
            boolean totalEmpty = true;
            if (btwn != null) {
                for (Square square : btwn) {
                    if (!square.isEmpty()) {
                        totalEmpty = false;
                    }
                }
            }
            isValid = (target.isEmpty() || target.PieceSelecter().ColorSelecter() != this.color) && totalEmpty;
        }

        return isValid;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}
