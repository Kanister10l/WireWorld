import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static java.awt.GridBagConstraints.PAGE_START;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by jamsl on 2017-04-11.
 */
public class Gui extends JFrame {
    private JButton startSimButton;
    private JButton loadFileButton;
    private JButton saveFileButton;
    private JButton exitButton;
    private JPanel menuPanel;
    private Canvas canvas;
    private Boolean isSimRunning=false;
    private Simulation sim;

    public Gui(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initUI();

        loadFileButton.addActionListener((ActionEvent event) -> {
            final JFileChooser fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new FileChooseFilter());
            int returnVal = fc.showOpenDialog(Gui.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                canvas.loadMatix(file);
                canvas.repaint();
            }
        });
        saveFileButton.addActionListener((ActionEvent event) -> {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(Gui.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                System.out.println("Saving to : " + file.getAbsolutePath() + "." );
            }
        });
        startSimButton.addActionListener((ActionEvent event) -> {
            if(canvas.isMatrixLoaded()) {

                if (!isSimRunning) {
                    startSimButton.setText("Zatrzymaj");
                    sim = new Simulation(canvas);
                    sim.start();
                    isSimRunning = true;
                } else {
                    sim.stop();
                    isSimRunning = false;
                    startSimButton.setText("Start symulacji");
                }
            }else{
                showMessageDialog(null   , "Najpierw załaduj plik z układem!","Błąd",ERROR_MESSAGE);
            }
        });
        exitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        //drag and drop
        new  FileDrop( canvas, files -> {
            File toLoad = files[0];
            canvas.loadMatix(toLoad);
            canvas.repaint();
        });
    }
    private void initUI(){
        setLayout(new BorderLayout());
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(140, -1));

        startSimButton = new JButton("Start symulacji");
        startSimButton.setBounds(10,10,120,30);
        menuPanel.add(startSimButton);

        loadFileButton = new JButton("Wczytaj plik");
        loadFileButton.setBounds(10,50,120,30);
        menuPanel.add(loadFileButton);

        saveFileButton = new JButton("Zapisz do pliku");
        saveFileButton.setBounds(10,90,120,30);
        menuPanel.add(saveFileButton);

        exitButton = new JButton("Wyjście");
        exitButton.setBounds(10,130,120,30);
        menuPanel.add(exitButton);

        add(menuPanel,BorderLayout.WEST);

        canvas = new Canvas();
        JScrollPane scroller = new JScrollPane(canvas);
        scroller.getViewport().addChangeListener(new ListenScrolled(canvas));
        scroller.setPreferredSize(new Dimension(200,200));

        add(scroller,BorderLayout.CENTER);

        pack();
        setTitle("Wireworld");
        setSize(600, 450);
        ImageIcon webIcon = new ImageIcon("icon.png");
        setIconImage(webIcon.getImage());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
