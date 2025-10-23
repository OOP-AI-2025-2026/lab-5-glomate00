package ua.opnu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends JFrame implements ActionListener {

    public MainFrame(String title) throws HeadlessException {
        super(title);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        ((JComponent) getContentPane()).setBorder(
                BorderFactory.createMatteBorder(10, 10, 10, 10, Color.WHITE));

        JButton rockButton = new JButton("Камінь");
        rockButton.addActionListener(this);
        rockButton.setActionCommand("rock");
        JButton paperButton = new JButton("Папір");
        paperButton.addActionListener(this);
        paperButton.setActionCommand("paper");
        JButton scissorsButton = new JButton("Ножиці");
        scissorsButton.addActionListener(this);
        scissorsButton.setActionCommand("scissors");

        this.add(rockButton);
        this.add(paperButton);
        this.add(scissorsButton);

        this.pack();
        this.setVisible(true);
    }

    private GameShape generateShape() {
        int random = new Random().nextInt(3);
        
        return switch (random) { 
            case 0 -> new Rock();
            case 1 -> new Scissors();
            case 2 -> new Paper();
            default -> new Rock(); 
        };
    }

    private int checkWinner(GameShape player, GameShape computer) {

        if (player.getClass() == computer.getClass()) {
            return 0;
        }

        if (player instanceof Rock) {
            if (computer instanceof Scissors)
                return 1;
            else
                return -1;

        } else if(player instanceof Scissors) {
            if (computer instanceof Paper)
                return 1;
            else if (computer instanceof Rock)
                return -1;
        } else if(player instanceof Paper){
            if (computer instanceof Rock)
                return 1;
            else if (computer instanceof Scissors)
                return -1;
        }

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameShape computerShape = generateShape();

        GameShape playerShape = null; 
        
        switch (e.getActionCommand()) {
            case "rock" -> playerShape = new Rock();
            case "paper" -> playerShape = new Paper();
            case "scissors" -> playerShape = new Scissors();
        }

        int gameResult = checkWinner(playerShape, computerShape);

        String message = "Гравець: " + playerShape + ". Комп'ютер: " + computerShape + ". ";
        switch (gameResult) {
            case -1:
                message += "Переміг комп'ютер!";
                break;
            case 0:
                message += "Нічия!";
                break;
            case 1:
                message += "Переміг гравець!";
        }

        JOptionPane.showMessageDialog(null, message);
    }
}

        // Вивести діалогове вікно з повідомленням
        JOptionPane.showMessageDialog(null, message);
    }
}
