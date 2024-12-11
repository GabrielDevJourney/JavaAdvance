package academy.mindswap.field;

public class Position {

    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result  = false;
        if(obj instanceof Position position){
            result = (getRow() == position.getRow() && getCol() == position.getCol());
        }
	    return result;
    }
}
