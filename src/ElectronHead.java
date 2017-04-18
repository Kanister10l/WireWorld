/**
 * Created on 14.04.2017 at 14:48 for usage in WireWorld.
 */
public class ElectronHead extends WireElement{
    private int posX;
    private int posY;
    private int sizeX = 1;
    private int sizeY = 1;
    private int rotation;

    public ElectronHead(int posX, int posY, int rotation){
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
        return 3;
    }

    @Override
    public int getSizeX(){
        return sizeX;
    }

    @Override
    public int getSizeY(){
        return sizeY;
    }

    @Override
    public String getType(){
        return "ElectronHead";
    }
}
