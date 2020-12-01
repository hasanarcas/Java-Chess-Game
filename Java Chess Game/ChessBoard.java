package hasanArcasChess;

public class ChessBoard{
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    private final String columns = "abcdefgh";
    private final String rows = "12345678";

    Square[][] board;

    boolean whitePlaying = true;

    public ChessBoard() {
        this.board = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i, j, this);
            }
        }

        this.initialize();
    }

    public void initialize() {

        //WHITE PIECES
        for (int i = 0; i < 8; i++)
            board[1][i].setPiece(new Pawn(ChessBoard.WHITE, board[1][i]));

        board[0][0].setPiece(new Rook(ChessBoard.WHITE, board[0][0]));
        board[0][1].setPiece(new Knight(ChessBoard.WHITE, board[0][1]));
        board[0][2].setPiece(new Bishop(ChessBoard.WHITE, board[0][2]));
        board[0][3].setPiece(new Queen(ChessBoard.WHITE, board[0][3]));
        board[0][4].setPiece(new King(ChessBoard.WHITE, board[0][4]));
        board[0][5].setPiece(new Bishop(ChessBoard.WHITE, board[0][5]));
        board[0][6].setPiece(new Knight(ChessBoard.WHITE, board[0][6]));
        board[0][7].setPiece(new Rook(ChessBoard.WHITE, board[0][7]));


        //BLACK PIECES
        for (int i = 0; i < 8; i++)
            board[6][i].setPiece(new Pawn(ChessBoard.BLACK, board[6][i]));

        board[7][0].setPiece(new Rook(ChessBoard.BLACK, board[7][0]));
        board[7][1].setPiece(new Knight(ChessBoard.BLACK, board[7][1]));
        board[7][2].setPiece(new Bishop(ChessBoard.BLACK, board[7][2]));
        board[7][3].setPiece(new Queen(ChessBoard.BLACK, board[7][3]));
        board[7][4].setPiece(new King(ChessBoard.BLACK, board[7][4]));
        board[7][5].setPiece(new Bishop(ChessBoard.BLACK, board[7][5]));
        board[7][6].setPiece(new Knight(ChessBoard.BLACK, board[7][6]));
        board[7][7].setPiece(new Rook(ChessBoard.BLACK, board[7][7]));
    }

    public boolean isWhiteTurn() {
        return whitePlaying;
    }

    public boolean isFinished() {
        int white = 0;
        int black = 0;
        for (int i = 0 ; i<8;i++){
            for(int j = 0; j < 8 ; j++){
                if (!board[i][j].isEmpty() && board[i][j].PieceSelecter().color == ChessBoard.WHITE)
                    white++;
                if (!board[i][j].isEmpty() && board[i][j].PieceSelecter().color == ChessBoard.BLACK)
                    black++;
            }
        }
        return white == 0 || black == 0;
    }

    public Piece PieceGetter(String from) {
        int col = this.getColumns(from);
        int row = this.getRows(from);
        return this.board[row][col].PieceSelecter();
    }

    public Square SquareSelector(String to) {
        int col = this.getColumns(to);
        int row = this.getRows(to);
        return this.board[row][col];
    }

    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        if (location.isAtSameRow(targetLocation)) {
            int lastColumn = Math.max(location.getColumn(), targetLocation.getColumn());
            int firstColumn = Math.min(location.getColumn(), targetLocation.getColumn());
            int columnCount = lastColumn - firstColumn - 1;
            if ( columnCount > 0) {
                Square[] squares = new Square[columnCount];
                for (int j = 0; j < columnCount; ++j) {
                    squares[j] = board[location.getRow()][firstColumn+j+1];
                }

                return squares;
            }
        }
        if (location.isAtSameColumn(targetLocation)) {
            int lastRow = Math.max(location.getRow(), targetLocation.getRow());
            int firstRow = Math.min(location.getRow(), targetLocation.getRow());
            int rowCount = lastRow - firstRow - 1;
            if ( rowCount > 0) {
                Square[] squares = new Square[rowCount];
                for (int i = 0; i < rowCount; ++i) {
                    squares[i] = board[firstRow+i+1][location.getColumn()];
                }

                return squares;
            }
        }
        if (location.isDiagonal(targetLocation)){
            int lastRow = Math.max(location.getRow(), targetLocation.getRow());
            int firstRow = Math.min(location.getRow(), targetLocation.getRow());
            int firstColumn = Math.min(location.getColumn(), targetLocation.getColumn());

            int cellCount = lastRow - firstRow - 1;
            if ( cellCount > 0) {
                Square[] squares = new Square[cellCount];
                for (int i = 0; i < cellCount; ++i) {
                    squares[i] = board[firstRow+i+1][firstColumn+i+1];
                }
                return squares;
            }
        }
        return  null;
    }

    private int getColumns(String s) {
        return this.columns.indexOf(s.charAt(0));
    }

    public void nextPlayer() {
        this.whitePlaying = !this.whitePlaying;
    }


    private int getRows(String s) {
        return this.rows.indexOf(s.charAt(1));
    }

    @Override
    public String toString() {
        String[] rowPresentations = new String[8];
        for (int i = 0; i < 8; i++) {
            String[] listColumnValues = new String[8];
            for (int j = 0; j < 8; j++) {
                listColumnValues[j] = (board[i][j].isEmpty() ? " " : board[i][j].PieceSelecter().toString());
            }
            String rowRepresentation = String.format("%s | %s | %s", i + 1, String.join(" | ", listColumnValues), i + 1);
            rowPresentations[7 - i] = rowRepresentation;
        }

        String representation = "\n    A   B   C   D   E   F   G   H    \n" + "------------------------------------\n";
        representation += String.join("\n------------------------------------\n", rowPresentations);
        representation += "\n------------------------------------\n" + "    A   B   C   D   E   F   G   H    \n";
        return representation;
    }
}
