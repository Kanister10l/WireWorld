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
        for (int i = 0; i < 5; i++) {
            matrix.printMatrix();
            matrix.jumpToNexGen();
            System.out.printf("\n");
        }
        matrix.printMatrix();
    }
}
