/**
 * Created on 11.04.2017 at 21:47 for usage in WireWorld.
 */
public abstract class WireElement {

    public abstract int getPosX();

    public abstract int getPosY();

    public abstract int getRotation();

    public abstract byte getGridPoint(int posX, int posY);

    public abstract int getSizeX();

    public abstract int getSizeY();
}
