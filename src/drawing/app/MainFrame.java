package drawing.app;

import drawing.figure.Figure;
import drawing.figure.Point;
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
    private Color fillingColor = Color.CYAN;
    private FigureStorage figureStorage = new FigureStorage();
    private ButtonGroup buttonGroup;
    private String selectedFigureType;

    private Figure.Builder currentBuilder;

    private FigureBuilders builders = new FigureBuilders();

    public MainFrame() {
        super("MyPaint");

        setContentPane(rootPanel);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new DefaultMouseListener());
        addMouseMotionListener(new DefaultMouseMotionAdapter());

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

        for(String figureType : builders.getAvailableTypes()) {
            JRadioButton button = new JRadioButton(figureType);
            buttonGroup.add(button);
            figureButtons.add(button);
            button.addActionListener(
                    x -> {
                        selectedFigureType = figureType;
                        inProgress = false;
                    }
            );
            button.setSelected(true);
            selectedFigureType = figureType;
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private class DefaultMouseMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
//            super.mouseMoved(e);
//            userChoice.setGraphics(getGraphics());
//            userChoice.setLastPoint(new Point(e.getX(), e.getY()));
//
//            if (figureInProcess != null) {
//                figureInProcess.preUpdate(userChoice.getLastPoint());
//                repaint();
//            }
        }
    }

    private boolean inProgress = false;
    private Point lastPoint;

    public void drawPoint(Point point) {
        getGraphics().drawOval(
                point.getX() - 5,
                point.getY() - 5,
                10,
                10
                );
    }

    private class DefaultMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point currentPoint = new Point(e.getX(), e.getY());
            drawPoint(currentPoint);

            if (!inProgress) {
                currentBuilder = builders.get(selectedFigureType);
                currentBuilder.setGraphics(getGraphics());
                currentBuilder.setLineColor(lineColorButton.getBackground());
                currentBuilder.setFillingColor(fillingColorButton.getBackground());
                currentBuilder.setRefPoint(currentPoint);
                lastPoint = currentPoint;
                inProgress = true;
            } else {
                if (currentBuilder.needsMorePoints()) {
                    currentBuilder.addPoint(currentPoint);
                    if (currentBuilder.isReadyForBuild()) {
                        Figure newFigure = currentBuilder.build();
                        figureStorage.add(newFigure);
                        newFigure.draw();
                        inProgress = false;
                        lastPoint = currentPoint;
                        return;
                    }
//                    if (currentBuilder.isReadyForBuild() && currentBuilder.needsMorePoints()) {
//                        if (isPointsEqual(currentPoint)) {
//                        Figure newFigure = currentBuilder.build();
//                        figureStorage.add(newFigure);
//                        newFigure.draw();
//                        inProgress = false;
//                        lastPoint = currentPoint;
//                        return;
//                        } else {
//                            currentBuilder.addPoint(currentPoint);
//                            lastPoint = currentPoint;
//                        }
//                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

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
