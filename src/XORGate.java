/**
 * Created on 14.04.2017 at 15:18 for usage in WireWorld.
 */
public class XORGate extends WireElement {
    private int posX;
    private int posY;
    private int sizeX = 5;
    private int sizeY = 7;
    private int rotation;
    private byte[][] grid;

    public XORGate(int posX, int posY, int rotation){
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;

        grid = new byte[5][7];
        grid[0][0] = 1;
        grid[0][1] = 0;
        grid[0][2] = 1;
        grid[0][3] = 1;
        grid[0][4] = 1;
        grid[0][5] = 0;
        grid[0][6] = 1;

        grid[1][0] = 0;
        grid[1][1] = 1;
        grid[1][2] = 1;
        grid[1][3] = 0;
        grid[1][4] = 1;
        grid[1][5] = 1;
        grid[1][6] = 0;

        grid[2][0] = 0;
        grid[2][1] = 0;
        grid[2][2] = 1;
        grid[2][3] = 0;
        grid[2][4] = 1;
        grid[2][5] = 0;
        grid[2][6] = 0;

        grid[3][0] = 0;
        grid[3][1] = 0;
        grid[3][2] = 1;
        grid[3][3] = 1;
        grid[3][4] = 1;
        grid[3][5] = 0;
        grid[3][6] = 0;

        grid[4][0] = 0;
        grid[4][1] = 0;
        grid[4][2] = 0;
        grid[4][3] = 1;
        grid[4][4] = 0;
        grid[4][5] = 0;
        grid[4][6] = 0;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getRotation(){
        return rotation;
    }

    @Override
    public byte getGridPoint(int posX, int posY){
        if (rotation == 90)
            return grid[posY][posX];
        else if (rotation == 180)
            return grid[sizeX - posX - 1][sizeY - posY - 1];
        else if (rotation == 270)
            return grid[sizeX - posY - 1][sizeY - posX - 1];
        else
            return grid[posX][posY];
    }

    @Override
    public int getSizeX(){
        if (rotation == 0 || rotation == 180)
            return sizeX;
        else
            return sizeY;
    }

    @Override
    public int getSizeY(){
        if (rotation == 0 || rotation == 180)
            return sizeY;
        else
            return sizeX;
    }
}
