import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ScoreSaver extends JFrame {
    private JTextField nickname;
    private Button saveButton;
    private JLabel score;
    private JLabel nickLabel;
    private int points;
    private JPanel panel;
    private Player player;
    private ArrayList<Player> listaGraczy;

    public ScoreSaver(int points){
        super("ScoreSaver");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.points=points;
        panel=new JPanel(new GridLayout(4, 1));
        score=new JLabel("YOUR SCORE: "+points);
        listaGraczy=wyniki();
        score.setBorder(new LineBorder(Color.BLACK,10));
        score.setForeground(new Color(251,243,54));
        score.setFont(new Font(Font.SERIF,Font.ITALIC,50));
        score.setBackground(new Color(130,38,38));
        nickLabel=new JLabel("NICKNAME: ");
        nickLabel.setBorder(new LineBorder(Color.BLACK,10));
        nickLabel.setForeground(new Color(251,243,54));
        nickLabel.setFont(new Font(Font.SERIF,Font.ITALIC,50));
        nickLabel.setBackground(new Color(130,38,38));
        nickname=new JTextField();
        nickname.setForeground(new Color(251,243,54));
        nickname.setBackground(new Color(130,38,38));
        nickname.setFont(new Font(Font.SERIF,Font.BOLD,40));
        nickname.setBorder(new LineBorder(Color.BLACK,10));
        saveButton=new Button("SAVE");
        saveButton.setFont(new Font(Font.SERIF,Font.ITALIC,40));
        panel.setBackground(new Color(130,38,38));
        panel.add(score);
        panel.add(nickLabel);
        panel.add(nickname);
        panel.add(saveButton);
        add(panel);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });


        setLocationRelativeTo(null);
        setVisible(true);

    }
    public void save(){
        if (!nickname.getText().isEmpty()) {
            player = new Player(nickname.getText(), points);
            listaGraczy.add(player);
            try (FileOutputStream fos = new FileOutputStream("highScores.ser");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(listaGraczy);
                JOptionPane.showMessageDialog(this, "Wynik zapisany");
                SwingUtilities.invokeLater(() -> new MainMenu());
                this.dispose();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Błąd w zapisie");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Podan nickname do zapisu wyniku");
        }
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


    public ArrayList<Player> getListaGraczy() {
        return listaGraczy;
    }
}
