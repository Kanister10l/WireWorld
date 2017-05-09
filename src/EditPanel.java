import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jamsl on 2017-05-05.
 */
public class EditPanel extends JPanel implements ActionListener {
    private JButton backToDefaultButton;
    private JButton newCanvasButton;
    private JButton elementButtons[];
    private int elementCount = 10;
    private Gui app;
    private JButton buttonSelected;

    public EditPanel(Gui app) {
        this.app = app;
        this.setLayout(null);
        newCanvasButton = new JButton("Nowy obwód");
        newCanvasButton.setBounds(10, 10, 120, 30);
        newCanvasButton.addActionListener(this);
        this.add(newCanvasButton);
        backToDefaultButton = new JButton("Powrot do menu");
        backToDefaultButton.setBounds(10, 50, 120, 30);
        backToDefaultButton.addActionListener(this);
        this.add(backToDefaultButton);


        elementButtons = new JButton[elementCount];
        for (int i = 0; i < elementCount; i++) {
            elementButtons[i] = new JButton();
            elementButtons[i].setIcon(new ImageIcon("images/" + i + ".png"));
            elementButtons[i].setSelectedIcon(new ImageIcon("images/s" + i + ".png"));
            elementButtons[i].setBorder(BorderFactory.createEmptyBorder());
            elementButtons[i].setContentAreaFilled(false);
            elementButtons[i].setFocusable(false);
            elementButtons[i].addActionListener(this);
        }
        elementButtons[0].setName("ElectronHead");
        elementButtons[1].setName("ElectronTail");
        elementButtons[2].setName("FlipFlop");
        elementButtons[3].setName("NANDGate1");
        elementButtons[4].setName("NANDGate2");
        elementButtons[5].setName("NANDORGate");
        elementButtons[6].setName("NANDORGateMirrored");
        elementButtons[7].setName("ORGate");
        elementButtons[8].setName("Wire");
        elementButtons[9].setName("XORGate");

        elementButtons[0].setBounds(10, 80, 22, 22);
        elementButtons[1].setBounds(85, 80, 22, 22);
        elementButtons[2].setBounds(10, 110, 57, 78);
        elementButtons[3].setBounds(70, 110, 57, 64);
        elementButtons[4].setBounds(10, 200, 57, 64);
        elementButtons[5].setBounds(70, 200, 57, 64);
        elementButtons[6].setBounds(10, 280, 57, 64);
        elementButtons[7].setBounds(70, 280, 43, 50);
        elementButtons[8].setBounds(10, 360, 22, 22);
        elementButtons[9].setBounds(70, 340, 50, 64);
        for (int i = 0; i < elementCount; i++) {
            this.add(elementButtons[i]);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == newCanvasButton) {
            app.newCanvasAction();
        } else if (source == backToDefaultButton) {
            app.backToDefaultAction();
        } else if (source instanceof JButton) {
            Selectable selectable = Selectable.getInstance();
            JButton newButtonSelected = (JButton) source;

            if (buttonSelected != null) {
                buttonSelected.setSelected(false);
            }
            if (buttonSelected == newButtonSelected) {
                    buttonSelected = null;
                    selectable.deselect();
            }else{
                    buttonSelected = newButtonSelected;
                    buttonSelected.setSelected(true);
                    selectable.select(buttonSelected.getName());
            }

        }
    }
}
