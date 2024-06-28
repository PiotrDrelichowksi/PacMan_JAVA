import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WyborMapy extends JFrame {
    JPanel panel;
    Button back;
    Button startGame;
    RadioButton m1,m2,m3,m4,m5;

    public WyborMapy(){
        super("Wybor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel=new JPanel(new GridLayout(7,1));
        setSize(1000, 800);
        back=new Button("Back");
        back.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        startGame=new Button("Start Game");
        startGame.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        m1=new RadioButton("Mapa 1 (35x30)");
        m2=new RadioButton("Mapa 2 (12x12)");
        m3=new RadioButton("Mapa 3 (20x20)");
        m4=new RadioButton("Mapa 4 (30x10)");
        m5=new RadioButton("Mapa 5 (16x9)");
        ButtonGroup bg=new ButtonGroup();
        bg.add(m1);
        bg.add(m2);
        bg.add(m3);
        bg.add(m4);
        bg.add(m5);
        panel.add(back);
        panel.add(m1);
        panel.add(m2);
        panel.add(m3);
        panel.add(m4);
        panel.add(m5);
        panel.add(startGame);
        add(panel);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new MainMenu());
                WyborMapy.this.dispose();
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!m1.isSelected() && !m2.isSelected() && !m3.isSelected() && !m4.isSelected() && !m5.isSelected()) {
                    SwingUtilities.invokeLater(()->JOptionPane.showMessageDialog(WyborMapy.this, "Proszę wybrać mapę", "Błąd", JOptionPane.ERROR_MESSAGE));
                } else if (m1.isSelected()){
                    SwingUtilities.invokeLater(()->new Game("zdjecia/mapy/mapa1.png"));
                    WyborMapy.this.dispose();
                }else if (m2.isSelected()){
                    SwingUtilities.invokeLater(()->new Game("zdjecia/mapy/mapa2.png"));
                    WyborMapy.this.dispose();
                }else if (m3.isSelected()){
                    SwingUtilities.invokeLater(()->new Game("zdjecia/mapy/mapa3.png"));
                    WyborMapy.this.dispose();
                }else if (m4.isSelected()){
                    SwingUtilities.invokeLater(()->new Game("zdjecia/mapy/mapa4.png"));
                    WyborMapy.this.dispose();
                }else if (m5.isSelected()){
                    SwingUtilities.invokeLater(()->new Game("zdjecia/mapy/mapa5.png"));
                    WyborMapy.this.dispose();
                }
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);

    }
}
