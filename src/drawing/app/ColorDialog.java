package drawing.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private ButtonGroup group;
    private JRadioButton redButton;
    private JRadioButton greenButton;
    private JRadioButton blueButton;
    private Color color;

    public ColorDialog(Color color) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        group = new ButtonGroup();
        group.add(redButton);
        group.add(greenButton);
        group.add(blueButton);
        this.color = color;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setSize(new Dimension(250, 250));
        setMinimumSize(new Dimension(250, 250));
        setVisible(true);
    }

    private void onOK() {
        if(redButton.isSelected())
            color = Color.RED;
        if(blueButton.isSelected())
            color = Color.BLUE;
        if(greenButton.isSelected())
            color = Color.GREEN;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Color getColor(){
        return color;
    }
}
