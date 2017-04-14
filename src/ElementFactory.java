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
        else if (type.equals("ElectronHead"))
            element = new ElectronHead(posX, posY, rotation);
        else if (type.equals("ElectronTail"))
            element = new ElectronTail(posX, posY, rotation);
        else if (type.equals("ORGate"))
            element = new ORGate(posX, posY, rotation);
        else if (type.equals("XORGate"))
            element = new XORGate(posX, posY, rotation);
        else if (type.equals("NANDGate1"))
            element = new NANDGate1(posX, posY, rotation);
        else if (type.equals("NANDGate2"))
            element = new NANDGate2(posX, posY, rotation);
        else if (type.equals("NANDORGate"))
            element = new NANDORGate(posX, posY, rotation);
        else if (type.equals("NANDORGateMirrored"))
            element = new NANDORGateMirrored(posX, posY, rotation);
        else if (type.equals("FlipFlop"))
            element = new FlipFlop(posX, posY, rotation);
        else
            element = null;

        return element;
    }
}
