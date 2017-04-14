/**
 * Created on 14.04.2017 at 17:31 for usage in WireWorld.
 */
public class Wire extends WireElement {
    private int posX;
    private int posY;
    private int sizeX = 1;
    private int sizeY = 1;
    private int rotation;

    public Wire(int posX, int posY, int rotation){
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
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
        return 1;
    }

    @Override
    public int getSizeX(){
        return sizeX;
    }

    @Override
    public int getSizeY(){
        return sizeY;
    }
}
