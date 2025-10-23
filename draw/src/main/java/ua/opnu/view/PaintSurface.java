package ua.opnu.view;

import ua.opnu.model.DrawShape;
import ua.opnu.model.Ellipse; 
import ua.opnu.model.Rectangle; 
import ua.opnu.model.RoundedRectangle; 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class PaintSurface extends JComponent {
    
    private final List<DrawShape> shapes = new ArrayList<>();
    private int shapeType;
    private Point startDrag;
    private Point endDrag;

    private final List<Color> colors = Arrays.asList
            (Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK);

    public PaintSurface() {
        shapeType = DrawShape.SHAPE_RECTANGLE;
        super.setPreferredSize(new Dimension(400, 400));

        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;

                DrawShape newShape = DrawShape.newInstance(shapeType); 
                
                if (newShape != null) {
                    newShape.setStartPoint(startDrag);
                    newShape.setEndPoint(endDrag); 
                    shapes.add(newShape); 
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (!shapes.isEmpty()) {
                    DrawShape lastShape = shapes.get(shapes.size() - 1);
                    lastShape.setEndPoint(new Point(e.getX(), e.getY()));
                }
                startDrag = null;
                endDrag = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());

                if (!shapes.isEmpty()) {
                    DrawShape lastShape = shapes.get(shapes.size() - 1);
                    lastShape.setEndPoint(endDrag);
                }
                repaint();
            }
        });
    }

    public void setShapeType(int type) {
        this.shapeType = type;
    }

    public void clearShapes() {
        this.shapes.clear();
        repaint();
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackgroundGrid(g2);
        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        shapes.forEach(s -> {
            g2.setPaint(Color.BLACK);
            g2.draw(s.getShape());
            g2.setPaint(colors.get(shapes.indexOf(s) % 6));
            g2.fill(s.getShape());
        });
    }

    private void paintBackgroundGrid(Graphics2D g2) {
        g2.setPaint(Color.LIGHT_GRAY);

        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }
    }
}
