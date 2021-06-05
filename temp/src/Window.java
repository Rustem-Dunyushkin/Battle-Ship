
import java.awt.Container;

import javax.swing.JFrame;

class Window extends JFrame {
    public Window() {
        Container cont = getContentPane();
        cont.add(new Panel());
        setBounds(0, 0, 900, 600);

        setTitle("Игра морской бой");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

}