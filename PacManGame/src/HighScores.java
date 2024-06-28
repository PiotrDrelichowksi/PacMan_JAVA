import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HighScores extends JFrame {
    JPanel panel;
    Button backButton;
    JList<Player> scoreJList;
    JLabel tytul;
    ArrayList<Player> listaGraczy;
    public HighScores(){
        super("High Scores");
        listaGraczy=wyniki();
        listaGraczy.sort(Player::compareTo);
        backButton=new Button("Back");
        backButton.setFont(new Font(Font.SERIF,Font.ITALIC,40));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel=new JPanel(new BorderLayout());
        panel.setBackground(new Color(130,38,38));
        tytul=new JLabel("HIGH SCORES");
        tytul.setBorder(new LineBorder(Color.BLACK,10));
        tytul.setForeground(new Color(251,243,54));
        tytul.setFont(new Font(Font.SERIF,Font.ITALIC,50));
        setSize(1000, 800);
        DefaultListModel<Player> dlmGracz=new DefaultListModel<>();
        for (Player p :listaGraczy) {
            dlmGracz.addElement(p);
        }
        scoreJList=new JList<>(dlmGracz);
        scoreJList.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        scoreJList.setBackground(new Color(130,38,38));
        scoreJList.setForeground(new Color(251,243,54));
        scoreJList.setBorder(new LineBorder(Color.BLACK,10));
        JScrollPane scroll = new JScrollPane(scoreJList);
        panel.add(tytul,BorderLayout.PAGE_START);
        panel.add(scroll,BorderLayout.CENTER);
        panel.add(backButton,BorderLayout.WEST);
        add(panel);
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new MainMenu());
                HighScores.this.dispose();
            }
        });

        setLocationRelativeTo(null);

    }
    private ArrayList<Player> wyniki() {
        try (FileInputStream fis = new FileInputStream("highScores.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (ArrayList<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
