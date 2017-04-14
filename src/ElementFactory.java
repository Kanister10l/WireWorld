/**
 * Created on 11.04.2017 at 21:47 for usage in WireWorld.
 */
public class ElementFactory {
    private WireElement element;

    public ElementFactory(){

    }

    public WireElement newElement(String type, int posX, int posY, int rotation){
        if (type.equals("Diode"))
            element = new Diode(posX, posY, rotation);
        else
            element = null;

        return element;
    }
}
