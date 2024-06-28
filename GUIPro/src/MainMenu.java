import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    JPanel panel;
    Button newGame;
    Button highScores;
    Button exit;
    public MainMenu() {
        super("Menu");
        newGame=new Button("New Game");
        highScores= new Button("High Scores");
        exit=new Button("Exit");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel=new JPanel(new BorderLayout());
        panel=new JPanel(new BorderLayout());
        setSize(1000, 800);
        Font f=new Font(Font.SERIF,Font.ITALIC,50);


        newGame.setBackground(new Color(130,38,38));
        newGame.setForeground(new Color(251,243,54));
        newGame.setFont(f);

        highScores.setBackground(new Color(130,38,38));
        highScores.setForeground(new Color(251,243,54));
        highScores.setFont(f);

        exit.setBackground(new Color(130,38,38));
        exit.setForeground(new Color(251,243,54));
        exit.setFont(f);


        panel.add(newGame,BorderLayout.CENTER);
        panel.add(highScores,BorderLayout.WEST);
        panel.add(exit, BorderLayout.EAST);

        add(panel);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new HighScores());
                MainMenu.this.dispose();
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new WyborMapy());
                MainMenu.this.dispose();
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }



}
