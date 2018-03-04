import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;


public class panel extends JPanel{
	private Timer tmDraw;
	private JButton btn1, btn2;
	private Image fon;
	private game newGame;
	
	public class myMouse1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(newGame.endGame==false) {
				int mX = e.getX();
				int mY = e.getY();
				
				if((e.getButton()==1)&&(e.getClickCount()==1)) {
					newGame.mousePressed(mX, mY);
				}
				else if((e.getButton()==1)&&(e.getClickCount()==2)) {
					newGame.mouseDoublePressed(mX, mY);
				}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
