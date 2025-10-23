package ua.opnu.view;

import ua.opnu.model.DrawShape;
import ua.opnu.model.Ellipse; 

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class DrawFrame extends JFrame {

    private PaintSurface surface;

    public DrawFrame(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

       
        surface = new PaintSurface(); 

        this.add(setButtonPanel(), BorderLayout.NORTH);
        this.add(surface, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private JPanel setButtonPanel() {

        JPanel buttonPanel = new JPanel(true);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

        
        BigTextButton rect = new BigTextButton("Rectangle");
        rect.addActionListener(e -> {
            surface.setShapeType(DrawShape.SHAPE_RECTANGLE);
        });
        buttonPanel.add(rect);

  
        BigTextButton rounded_rect = new BigTextButton("Rounded rect.");
        rounded_rect.addActionListener(e -> {
            surface.setShapeType(DrawShape.SHAPE_ROUNDED_RECT);
        });
        buttonPanel.add(rounded_rect);

   
        BigTextButton ellipseButton = new BigTextButton("Ellipse");
        ellipseButton.addActionListener(e -> {
            surface.setShapeType(Ellipse.SHAPE_ELLIPSE); 
        });
        buttonPanel.add(ellipseButton); 

      
        BigTextButton clearButton = new BigTextButton("Clear");
        clearButton.addActionListener(e -> {
            surface.clearShapes();
        });
        buttonPanel.add(clearButton);
        
        return buttonPanel;
    }
}
