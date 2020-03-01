package com.app;

import com.figure.Figure;
import com.figure.FigureFactory;
import com.figure.Point;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Paint extends JFrame {

    private JPanel rootPanel;
    private JMenuBar menuBar;
    private Color color;
    private int prevX, prevY;
    private ArrayList<ColorLine> image;

    FigureFactory factory;

    public Paint() {
        super("MyPaint");

        FigureFactory figureFactory = new FigureFactory(getGraphics());

        setContentPane(rootPanel);
        setVisible(true);
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        color = Color.RED;
        menuBar = new JMenuBar();
        image = new ArrayList<>();

        JMenu fileMenu = new JMenu("Файл");

        JMenuItem openItem = new JMenuItem("Открыть");
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Сохранить");
        fileMenu.add(saveItem);

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
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                openImage();
            }
        });
        saveItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveImage();
            }
        });

        setJMenuBar(menuBar);
        pack();

        addMouseListener(new PaintMouseAdapter());
        addMouseMotionListener(new PaintMouseMotionAdapter());

    }

    private class PaintMouseAdapter extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            setPreviousCoordinates(e.getX(), e.getY());
        }
    }

    public void setPreviousCoordinates(int x, int y){
        prevX = x;
        prevY = y;
    }

    private class PaintMouseMotionAdapter extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e){
            Graphics g = getGraphics();
            g.setColor(color);
            g.drawLine(prevX, prevY, e.getX(),e.getY());
            image.add(new ColorLine(prevX, prevY, e.getX(),e.getY(), color));
            setPreviousCoordinates(e.getX(),e.getY());

            FigureFactory figureFactory = new FigureFactory(getGraphics());
            Figure circle = figureFactory.createCircle(new Point(100, 200), 20);
            circle.draw();

        }
    }

    public void paint(Graphics g){
        super.paint(g);
        for(ColorLine cl : image){
            g.setColor(cl.getColor());
            g.drawLine(cl.getX1(),cl.getY1(),cl.getX2(),cl.getY2());
        }
    }

    public void saveImage(){
        JFileChooser saveDialog = new JFileChooser(".");
        saveDialog.setDialogTitle("Сохранение файла");
        saveDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = saveDialog.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION ) {
            try {
                Connector writer = new Connector(saveDialog.getSelectedFile());
                writer.write(image);
            }
            catch(Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Ошибка при сохранении",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null,
                    "Файл " + saveDialog.getSelectedFile() + " сохранен");
        }
    }

    public void openImage(){
        JFileChooser openDialog = new JFileChooser(".");
        openDialog.setDialogTitle("Открытие файла");
        openDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = openDialog.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION ) {
            try {
                Connector con = new Connector(openDialog.getSelectedFile());
                image = con.read();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ошибка при открытии",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
        paint(getGraphics());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
}
