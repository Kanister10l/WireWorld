/**
 * Created on 12.04.2017 at 21:47 for usage in WireWorld.
 */
public class Matrix {
    private byte[][] matrix;
    private int lastX = 0;
    private int lastY = 0;
    private WireElement[] elementList;
    
    public Matrix(WireElement[] elementList){
        this.elementList = elementList;
    }

    public void fillMatrix(){
        this.getSize();
        matrix = new byte[lastX + 2][lastY + 2];
        for (WireElement element: elementList) {
            if (element != null){
                for (int i = 0; i < element.getSizeX(); i++) {
                    for (int o = 0; o < element.getSizeY(); o++) {
                        if (matrix[i + element.getPosX() + 1][o + element.getPosY() + 1] < element.getGridPoint(i, o))
                            matrix[i + element.getPosX() + 1][o + element.getPosY() + 1] = element.getGridPoint(i, o);
                    }
                }
            }
        }
    }

    private void getSize() {
        for (WireElement element: elementList) {
            if (element != null) {
                if (element.getPosX() + element.getSizeX() > lastX)
                    lastX = element.getPosX() + element.getSizeX();
                if (element.getPosY() + element.getSizeY() > lastY)
                    lastY = element.getPosY() + element.getSizeY();
            }
        }
    }

    public byte[][] getMatrix(){
        return matrix;
    }

    private int findNeighbour(int posX, int posY){
        int n=0;
        if (matrix[posX - 1][posY - 1] == 3)
            n++;
        if (matrix[posX - 1][posY] == 3)
            n++;
        if (matrix[posX - 1][posY + 1] == 3)
            n++;
        if (matrix[posX][posY - 1] == 3)
            n++;
        if (matrix[posX][posY + 1] == 3)
            n++;
        if (matrix[posX + 1][posY - 1] == 3)
            n++;
        if (matrix[posX + 1][posY] == 3)
            n++;
        if (matrix[posX + 1][posY + 1] == 3)
            n++;
        return n;
    }

    public byte[][] jumpToNexGen(){
        byte[][] temp = new byte[lastX + 2][lastY + 2];
        for (int i = 1; i < lastX + 1; i++){
            for (int j = 1; j < lastY + 1; j++) {
                if (matrix[i][j] == 1) {
                    if (this.findNeighbour(i, j) == 1 || this.findNeighbour(i, j) == 2)
                        temp[i][j] = 3;
                    else
                        temp[i][j] = 1;
                } else if (matrix[i][j] == 2)
                    temp[i][j] = 1;
                else if (matrix[i][j] == 3)
                    temp[i][j] = 2;
                else if (matrix[i][j] == 0)
                    temp[i][j] = 0;
                else
                    temp[i][j] = 1;
            }
        }
        matrix = temp;
        return matrix;
    }

    public void printMatrix(){
        for (int i = 1; i < lastY + 1; i++) {
            for (int j = 1; j < lastX + 1; j++) {
                System.out.printf("%d",matrix[j][i]);
            }
            System.out.printf("\n");
        }
    }

    public int getSizeX(){
        return lastX + 2;
    }

    public int getSizeY(){
        return lastY + 2;
    }

    private void clearMatrix(){
        for (int i = 0; i < lastX + 2; i++) {
            for (int j = 0; j < lastY + 2; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void refreshMatrix(WireElement[] newElementList){
        elementList = newElementList;
        this.clearMatrix();
        this.fillMatrix();
    }
}