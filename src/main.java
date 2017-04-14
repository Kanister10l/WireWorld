import com.sun.prism.shader.FillEllipse_Color_AlphaTest_Loader;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created on 11.04.2017 at 21:47 for usage in WireWorld.
 */
public class main {
    public static void main(String[] args) throws IOException {
        int m = 30;
        FileParser parser = new FileParser("config.cfg");
        WireElement[] elementList = parser.getElements(10);
        Matrix matrix = new Matrix(elementList);
        matrix.fillMatrix();
        BufferedImage img = new BufferedImage(matrix.getSizeX()*m, matrix.getSizeY()*m, BufferedImage.TYPE_INT_RGB);
        byte[][] field = matrix.getMatrix();
        ImageOutputStream output = new FileImageOutputStream(new File("img.gif"));
        GifSequenceWriter writer = new GifSequenceWriter(output, img.getType(), 100, true);
        for (int y = 0; y < 100; y++) {
            for (int i = 0; i < matrix.getSizeX(); i++) {
                for (int j = 0; j < matrix.getSizeY(); j++) {
                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m; l++) {
                            img.setRGB(i * m + k, j * m + l, field[i][j] * 555555);
                        }
                    }
                }
            }
            writer.writeToSequence(img);
            field = matrix.jumpToNexGen();
        }
        writer.close();
        output.close();
        /*for (int i = 0; i < 5; i++) {
            matrix.printMatrix();
            matrix.jumpToNexGen();
            System.out.printf("\n");
        }
        matrix.printMatrix();*/
    }
}
