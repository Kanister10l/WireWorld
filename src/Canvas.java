import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Canvas extends JPanel{
    private Dimension area;
    private Matrix matrix;
    private Color colors[];
    private int blockSize = 15;
    public Canvas() {
        area = new Dimension(0,0);
        colors = new Color[4];
        colors[0] = new Color(0, 0, 0);
        colors[1] = new Color(125, 125, 0);
        colors[2] = new Color(125, 125, 125);
        colors[3] = new Color(125, 0, 0);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        int sizeX = matrix.getSizeX();
        int sizeY = matrix.getSizeY();

        //draw grid
        g2d.setPaint(new Color(64, 64, 64));
        for(int i=0;i<sizeX+1;i++){
            g2d.drawLine((blockSize+1)*i, 0, (blockSize+1)*i, (blockSize+1)*(sizeY));
        }
        for(int i=0;i<sizeY+1;i++){
            g2d.drawLine(0, (blockSize+1)*i, (blockSize+1)*(sizeX), (blockSize+1)*i);
        }
        byte[][] matrixToDraw = matrix.getMatrix();
        for(int i=0;i<sizeX;i++){
            for(int j=0;j<sizeY;j++){
                g2d.setPaint(colors[matrixToDraw[i][j]]);
                g2d.fillRect(i*(blockSize+1)+1, j*(blockSize+1)+1, blockSize, blockSize);
            }
        }

    }
    public void setArea(int width,int height){
        area.width = width;
        area.height = height;
        setPreferredSize(area);
        revalidate();
    }
    public void loadMatix(File cfgFile){
        FileParser parser = new FileParser(cfgFile.getAbsolutePath());
        WireElement[] elementList = new WireElement[0];
        try {
            elementList = parser.getElements(10);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        matrix = new Matrix(elementList);
        matrix.fillMatrix();
        System.out.println(matrix.getSizeY());
        setArea((matrix.getSizeX())*(blockSize+1),(matrix.getSizeY())*(blockSize+1));

    }
    public void nextGen(){
        matrix.jumpToNexGen();
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        if(this.isMatrixLoaded()){
            doDrawing(g);
        }else{
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Serif", Font.PLAIN, 20));
            g2d.drawString("Proszę o wybranie pliku do wczytania.", 40, 50);
        }
    }
    public boolean isMatrixLoaded(){
        return (matrix != null);
    }
}