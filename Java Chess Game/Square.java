package hasanArcasChess;


public class Square {
    private Piece piece;
    private ChessBoard board;

    private int column;
    private int row;

    public Square(int row, int column, ChessBoard board) {
        this.column = column;
        this.row = row;
        this.board = board;
    }

    public ChessBoard BoardSelector(){
        return this.board;
    }

    public int getRowDistance(Square location){
        return this.row - location.row;
    }
    public int getColDistance(Square location) {
        return this.column - location.column;
    }
    public Piece PieceSelecter(){
        return piece;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public boolean isAtLastRow(int color){
        if(this.piece != null){
            if (color == ChessBoard.WHITE && this.piece.location.row == 7){
                return true;
            }
            if (color == ChessBoard.BLACK && this.piece.location.row == 0){
                return true;
            }
        }
        return false;
    }
    public boolean isEmpty(){
        return piece == null ? true : false;
    }
    public boolean isAtSameColumn(Square square){
        return (square.getColumn() == this.getColumn());
    }
    public boolean isAtSameRow(Square square) {
        return  square.getRow() == this.getRow();
    }
    public boolean isDiagonal(Square square){
        return Math.abs(this.getRowDistance(square)) == Math.abs(this.getColDistance(square));
    }
    public boolean isNeighborColumn(Square square) {
        return (square.getColumn() == this.getColumn() +1 ) || (square.getColumn() == this.getColumn() - 1 );
    }

    public void putNewQueen(int color)
    {
        this.piece = new Queen(color, this);
    }
    public void clear(){
        this.piece = null;
    }

    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
}
