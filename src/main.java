import java.io.IOException;

/**
 * Created on 11.04.2017 at 21:47 for usage in WireWorld.
 */
public class main {
    public static void main(String[] args) throws IOException {
        FileParser parser = new FileParser("config.cfg");
        WireElement[] elementList = parser.getElements(10);
        Matrix matrix = new Matrix(elementList);
        matrix.fillMatrix();
        byte[][] mat = matrix.getMatrix();
        matrix.printMatrix();
    }
}
