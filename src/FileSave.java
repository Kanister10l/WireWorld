import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created on 16.04.2017 at 20:26 for usage in WireWorld.
 */
public class FileSave {
    private String fileName;

    public FileSave(String fileName){
        this.fileName = fileName;
    }

    public void saveFile(WireElement[] elementList, Matrix matrix) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        int k = 0;

        for (WireElement element: elementList) {
            StringBuilder cfg = new StringBuilder();
            if (element != null) {
                if (!element.getType().equals("ElectronHead") || !element.getType().equals("ElectronTail")) {
                    if (k != 0)
                        writer.newLine();
                    k++;
                    cfg.append(element.getType());
                    cfg.append(":");
                    cfg.append(element.getPosX());
                    cfg.append(":");
                    cfg.append(element.getPosY());
                    cfg.append(":");
                    cfg.append(element.getRotation());
                    writer.write(cfg.toString());
                }
            }
        }

        byte [][] grid = matrix.getMatrix();
        int x = matrix.getSizeX();
        int y = matrix.getSizeY();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                StringBuilder cfg = new StringBuilder();
                if (grid[i][j] == 2){
                    cfg.append("ElectronTail");
                    cfg.append(":");
                    cfg.append(i-1);
                    cfg.append(":");
                    cfg.append(j-1);
                    cfg.append(":");
                    cfg.append(0);
                    writer.write(cfg.toString());
                }
                else if (grid[i][j] == 3){
                    cfg.append("ElectronHead");
                    cfg.append(":");
                    cfg.append(i-1);
                    cfg.append(":");
                    cfg.append(j-1);
                    cfg.append(":");
                    cfg.append(0);
                    writer.write(cfg.toString());
                }
            }
        }
        writer.close();
    }
}
