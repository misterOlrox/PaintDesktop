package drawing.app;

import drawing.figure.Figure;
import drawing.figure.Polygon;
import drawing.figure.Segment;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private JPanel rootPanel;
    private JButton lineColorButton;
    private JButton fillingColorButton;
    private JPanel figureButtons;
    private Color lineColor = Color.RED;
    private Color fillingColor = Color.WHITE;
    private FigureStorage figureStorage = new FigureStorage();
    private ButtonGroup buttonGroup;
    private UserChoice userChoice = new UserChoiceImpl();
    private String selectedFigureType;
    private MouseListener defaultMouseListener = new DefaultMouseAdapter();

    private FigureFactory figureFactory = new FigureFactory(
            new Segment.FactoryMethod(),
            new Polygon.FactoryMethod()
    );

    public MainFrame() {
        super("MyPaint");

        setContentPane(rootPanel);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(defaultMouseListener);
        addMouseMotionListener(new PaintMouseMotionAdapter());

        lineColorButton.setBackground(lineColor);
        lineColorButton.addActionListener(e -> {
            lineColor = JColorChooser.showDialog(this, "", Color.RED);
            lineColorButton.setBackground(lineColor);
        });

        fillingColorButton.setBackground(fillingColor);
        fillingColorButton.addActionListener(e -> {
            fillingColor = JColorChooser.showDialog(this, "", Color.RED);
            fillingColorButton.setBackground(fillingColor);
        });

        buttonGroup = new ButtonGroup();

        for(String figureType : figureFactory.getAvailableTypes()) {
            JRadioButton button = new JRadioButton(figureType);
            buttonGroup.add(button);
            figureButtons.add(button);
            button.addActionListener(x -> selectedFigureType = figureType);
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private class DefaultMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(lineColor);

            Figure newFigure = figureFactory.create(selectedFigureType, userChoice);

            //Figure segment = new Segment(g, lineColor, new Point(e.getX(), e.getY()), new Point(e.getX()+5, e.getY() + 5));

            figureStorage.add(newFigure);
            newFigure.draw();
        }
    }

    private class PaintMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
//            Graphics g = getGraphics();
//            g.setColor(lineColor);
//
//            Figure newFigure = figureFactory.create(selectedFigureType, userChoice);
//
//            //Figure segment = new Segment(g, lineColor, new Point(e.getX(), e.getY()), new Point(e.getX()+5, e.getY() + 5));
//
//            figureStorage.add(newFigure);
//            newFigure.draw();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure figure : figureStorage.getAll()) {
            figure.draw();
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
