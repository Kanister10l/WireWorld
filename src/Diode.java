/**
 * Created on 11.04.2017 at 21:46 for usage in WireWorld.
 */
public class Diode extends WireElement {
    private int posX;
    private int posY;
    private int sizeX = 4;
    private int sizeY = 3;
    private int rotation;
    private byte[][] grid;

    public Diode(int posX, int posY, int rotation){
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;

        grid = new byte[4][3];
        grid[0][0] = 0;
        grid[0][1] = 1;
        grid[0][2] = 0;
        grid[1][0] = 1;
        grid[1][1] = 0;
        grid[1][2] = 1;
        grid[2][0] = 1;
        grid[2][1] = 1;
        grid[2][2] = 1;
        grid[3][0] = 0;
        grid[3][1] = 1;
        grid[3][2] = 0;
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

    @Override
    public String getType(){
        return "Diode";
    }
}
