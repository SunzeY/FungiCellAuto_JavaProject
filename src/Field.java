import java.util.ArrayList;

class Field {
    private final Cell[][] labField;
    private final double[][] fieldBase;
    public static final double BaseValue = 200.0;
    private static final Location[] adjacent = {
            new Location(-1,-1),new Location(-1,0),new Location(-1,1),
            new Location(0,-1),new Location(0,0),new Location(0,1),
            new Location(1,-1),new Location(1,0),new Location(1,1),
    };
    private final int Height;
    private final int Width;

    public Field(int Width, int Height) {
        this.Height = Height;
        this.Width = Width;
        this.labField = new Cell[Width][Height];
        this.fieldBase = new double[Width][Height];
        for (int i = 0; i<Width; i++) {
            for (int j = 0; j<Height; j++) {
                fieldBase[i][j] = BaseValue;
            }
        }
    }

    public Cell get(int row, int col) {
        return labField[row][col];
    }

    public int getHeight() {
        return this.Height;
    }

    public int getWidth() {
        return this.Width;
    }

    public void place(int row, int col, Cell obj) {
        labField[row][col] = obj;
    }

    public void clear(){
        for(int i=0;i<Height;i++){
            for(int j=0;j<Width;j++){
                labField[i][j] = null;
                fieldBase[i][j] = BaseValue;
            }
        }
    }

    public Location[] getFreeNeighbour(int row,int col){
        ArrayList<Location> list = new ArrayList<>();
        for(Location loc : adjacent){
            int r = row + loc.getRow();
            int c = col+loc.getCol();
            if(r>-1 && r<Height && c>-1 && c<Width && labField[r][c] == null){
                list.add(new Location(r,c));
            }
        }
        return list.toArray(new Location[0]);
    }
    public void placeRandomAdj(int row, int col, Cell cell){
        boolean ret = false;
        Location[] freeAdj = getFreeNeighbour(row,col);
        if(freeAdj.length > 0){
            int idx = (int)(Math.random()*freeAdj.length);
            labField[freeAdj[idx].getRow()][freeAdj[idx].getCol()] = cell;
            ret = true;
        }
    }
    public void eat(int row, int col) {
        fieldBase[row][col] -= labField[row][col].eat();
        if (fieldBase[row][col] <= 0) {
            labField[row][col] = null;
            fieldBase[row][col] = 0;
        }
    }

    public double getRemain(int row, int col) {
        return this.fieldBase[row][col];
    }

}
