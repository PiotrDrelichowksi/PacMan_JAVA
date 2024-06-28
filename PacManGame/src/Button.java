import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Button extends JButton {
    Button(String text){
        super(text);
        setBackground(new Color(130,38,38));
        setForeground(new Color(251,243,54));
        setBorder(new LineBorder(Color.BLACK,10));

    }
}
