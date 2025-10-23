package ua.opnu.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends DrawShape {
    
    public static final int SHAPE_ELLIPSE = 2; // Нова константа
    
    public Ellipse() {
        super();
    }

    public Ellipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public Shape getShape(Point start, Point end) {
      
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        
        return new Ellipse2D.Double(x, y, width, height);
    }
}
