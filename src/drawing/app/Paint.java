package drawing.app;

import drawing.figure.Figure;
import drawing.figure.Point;
import drawing.figure.Segment;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Paint extends JFrame {

    private JPanel rootPanel;
    private JMenuBar menuBar;
    private Color color;
    private int prevX, prevY;
    private FigureStorage figureStorage;

    public Paint() {
        super("MyPaint");

        setContentPane(rootPanel);
        setVisible(true);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        color = Color.RED;
        menuBar = new JMenuBar();
        figureStorage = new FigureStorage();

        JMenu fileMenu = new JMenu("Файл");

        menuBar.add(fileMenu);

        JMenu colorMenu = new JMenu("Цвет");
        menuBar.add(colorMenu);
        colorMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ColorDialog colorDialog = new ColorDialog(color);
                color = colorDialog.getColor();
            }
        });

        setJMenuBar(menuBar);
        pack();

        addMouseListener(new PaintMouseAdapter());
        addMouseMotionListener(new PaintMouseMotionAdapter());

    }

    private class PaintMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            setPreviousCoordinates(e.getX(), e.getY());
        }
    }

    public void setPreviousCoordinates(int x, int y) {
        prevX = x;
        prevY = y;
    }

    private class PaintMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(color);
            g.drawLine(prevX, prevY, e.getX(), e.getY());

            Figure segment = new Segment(g, color, new Point(e.getX(), e.getY()), new Point(e.getX()+5, e.getY() + 5));

            figureStorage.add(segment);
            segment.draw();

            setPreviousCoordinates(e.getX(), e.getY());

        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure figure : figureStorage.getAll()) {
            figure.draw();
        }
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
