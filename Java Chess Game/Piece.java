package hasanArcasChess;

public abstract class Piece {

    int color;
    Square location;

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public int ColorSelecter(){ return this.color;
    }

    public abstract boolean canMove(String to);

    public void move(String to)
    {
        Square target = location.BoardSelector().SquareSelector(to);
        //clear previous location
        location.clear();
        //update current location
        target.setPiece(this);
        location = target;
        location.BoardSelector().nextPlayer();
    }
}
