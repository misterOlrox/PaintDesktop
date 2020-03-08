package drawing.lib.dialog;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SymmetricPolygonDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinner;
    private Integer sideNumber;

    public SymmetricPolygonDialog() {
        setTitle("Symmetric Polygon Dialog");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        spinner.addChangeListener((l) -> {
            int value = -1;
            try {
                value = Integer.parseInt(spinner.getValue().toString());
            } catch (NumberFormatException e) {
                spinner.setValue(3);
                sideNumber = 3;
            }
            if (value >= 3) {
                spinner.setValue(value);
                sideNumber = value;
            } else {
                spinner.setValue(3);
                sideNumber = 3;
            }
        });

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

        setMinimumSize(new Dimension(400, 200));
        setResizable(false);
        setLocation(getBounds().width/2, getBounds().height/2);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        sideNumber = null;

        dispose();
    }

    public Integer getSidesNumber() {
        return sideNumber;
    }
}
