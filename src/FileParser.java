import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 12.04.2017 for usage in WireWorld.
 */
public class FileParser {
    private String fileName;
    private WireElement[] elementList;
    private int n = 0;
    private ElementFactory factory = new ElementFactory();

    public FileParser(String fileName){
        this.fileName = fileName;
    }

    public WireElement[] getElements(int expectedN) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        Scanner scan = null;
        elementList = new WireElement[expectedN];
        try {
            scan = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (scan.hasNext()){
                String line = scan.nextLine();
                String[] params = line.split("[:]");
                n++;
                if (n > expectedN)
                    this.doubleSize();
                elementList[n-1] = factory.newElement(params[0], Integer.parseInt(params[1]),
                                                        Integer.parseInt(params[2]), Integer.parseInt(params[3]));
            }
        }
        finally {
            if (scan != null)
                scan.close();
        }
        return elementList;
    }

    private void doubleSize(){
        elementList = Arrays.copyOf(elementList, elementList.length * 2);
    }
}
