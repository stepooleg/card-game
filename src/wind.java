import javax.swing.*;
import java.awt.*;

public class wind extends JFrame{
	public wind() {
		panel pan = new panel();
		
		Container cont = getContentPane();
		cont.add(pan);
		
		setTitle("GAME\"Solitaire - Klondike\"");
		setBounds(0,0,1000,700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
