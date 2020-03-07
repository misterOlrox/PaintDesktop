package drawing.app;

import drawing.figure.Figure;
import drawing.figure.Point;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
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
    private JCheckBox moveModeCheckBox;
    private JComboBox figuresComboBox;
    private JPanel drawPanel;

    private Color lineColor = Color.RED;
    private Color fillingColor = Color.CYAN;
    private ButtonGroup buttonGroup;
    private String selectedFigureType;

    private PaintMouseListener paintMouseListener = new PaintMouseListener();
    private MoveMotionAdapter moveMotionAdapter = new MoveMotionAdapter();

    private Point movePoint;
    private Figure movingFigure;

    private Figure.Builder currentBuilder;

    private FigureStorage figureStorage = new FigureStorage();
    private FigureBuilders builders = new FigureBuilders();

    public MainFrame() {
        super("MyPaint");

        setContentPane(rootPanel);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        drawPanel.addMouseListener(paintMouseListener);

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

        for (String figureType : builders.getAvailableTypes()) {
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

        moveModeCheckBox.setSelected(false);
        figuresComboBox.setEnabled(false);

        moveModeCheckBox.addActionListener((l) -> {
            if (moveModeCheckBox.isSelected() && !figureStorage.isEmpty()) {
                figuresComboBox.setEnabled(true);
                figuresComboBox.removeAllItems();
                for (Figure figure : figureStorage.getAll()) {
                    figuresComboBox.addItem(figure);
                }
                drawPanel.removeMouseListener(paintMouseListener);

            } else {
                figuresComboBox.setEnabled(false);
                drawPanel.addMouseListener(paintMouseListener);
                drawPanel.removeMouseMotionListener(moveMotionAdapter);
            }
        });

        figuresComboBox.addItemListener((e) -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        Figure selectedFigure = (Figure) e.getItem();
                        if (selectedFigure == null || selectedFigure == movingFigure) {
                            return;
                        }
                        movingFigure = selectedFigure;

                        movePoint = selectedFigure.getLocation();
                        MainFrame.this.repaint();
                        drawPanel.addMouseMotionListener(moveMotionAdapter);
                    }
                }
        );

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private boolean inProgress = false;

    public void drawPoint(Point point, Color color, boolean fill) {
        Graphics g = drawPanel.getGraphics();
        g.setColor(color);
        if (fill) {
            g.fillOval(point.getX() - 5, point.getY() - 5, 10, 10);
        }

        g.drawOval(point.getX() - 5, point.getY() - 5, 10, 10);
    }

    private class PaintMouseListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            Point currentPoint = new Point(e.getX(), e.getY());
            drawPoint(currentPoint, Color.BLUE, false);

            if (!inProgress) {
                currentBuilder = builders.get(selectedFigureType);

                currentBuilder
                        .setLineColor(lineColorButton.getBackground())
                        .setFillingColor(fillingColorButton.getBackground())
                        .setRefPoint(currentPoint);

                inProgress = true;
            } else {
                if (currentBuilder == null) {
                    inProgress = false;
                    return;
                }
                if (currentBuilder.needsMorePoints()) {
                    currentBuilder.addPoint(currentPoint);
                }
                if (currentBuilder.isReadyForBuild()) {
                    Figure newFigure = currentBuilder.build();
                    figureStorage.add(newFigure);
                    newFigure.draw(drawPanel.getGraphics());
                    inProgress = false;
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

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }

    private class MoveMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);

            if (!moveModeCheckBox.isSelected()) {
                drawPanel.removeMouseMotionListener(this);
                return;
            }
            Point clickPoint = new Point(e.getX(), e.getY());
            movePoint = clickPoint;
            movingFigure.move(movePoint);
            repaint();
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

    private void createUIComponents() {
        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Figure figure : figureStorage.getAll()) {
                    figure.draw(g);
                }
                if (moveModeCheckBox.isSelected()) {
                    drawPoint(movePoint, Color.RED, true);
                }
            }
        };
    }
}
