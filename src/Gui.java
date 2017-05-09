import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by jamsl on 2017-04-11.
 */
public class Gui extends JFrame {
    private JButton startSimButton;
    private JButton loadFileButton;
    private JButton saveFileButton;
    private JButton changeModeButton;
    private JButton exitButton;

    private JPanel menuPanel;
    private JPanel editPanel;
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
                FileSave save = new FileSave(file);
                try {
                    save.saveFile(canvas.getMatrix().getElementList(), canvas.getMatrix());
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
        changeModeButton.addActionListener((ActionEvent event) -> {
            if(isSimRunning){
                sim.stop();
                isSimRunning = false;
                startSimButton.setText("Start symulacji");
            }
            canvas.changeEditMode();
            remove(menuPanel);
            add(editPanel);
            revalidate();
            repaint();
        });
        exitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        //drag and drop
        new FileDrop( canvas, files -> {
            File toLoad = files[0];
            canvas.loadMatix(toLoad);
            canvas.repaint();
        });

    }
    //edit mode listeners
    public void backToDefaultAction(){
        canvas.changeEditMode();
        remove(editPanel);
        add(menuPanel);
        revalidate();
        repaint();
    }
    public void newCanvasAction(){
        canvas.clearMatrix();
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

        changeModeButton = new JButton("Włącz edytor");
        changeModeButton.setBounds(10,130,120,30);
        menuPanel.add(changeModeButton);

        exitButton = new JButton("Wyjście");
        exitButton.setBounds(10,170,120,30);
        menuPanel.add(exitButton);

        //panel for editing
        editPanel = new EditPanel(this);


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
