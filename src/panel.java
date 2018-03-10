import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;


public class panel extends JPanel{
	private Timer tmDraw;
	private JButton btnNG, btnEx;
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
	
	public class myMouse2 implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(newGame.endGame==false) {
				int mX=e.getX();
				int mY=e.getY();
				
				newGame.mouseDragget(mX, mY);
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public panel() {
		addMouseListener(new myMouse1());
		addMouseMotionListener(new myMouse2());
		
		newGame = new game();
		try {
			fon = ImageIO.read(new File("C:\\kos\\fon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);
		btnNG = new JButton();
		btnNG.setText("Новая игра");
		btnNG.setForeground(Color.BLUE);
		btnNG.setFont(new Font("serif",0,20));
		btnNG.setBounds(820,50,150,50);
		btnNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				newGame.start();
			}
			
		});
		add(btnNG);
		
		btnEx = new JButton();
		btnEx.setText("Выход");
		btnEx.setForeground(Color.RED);
		btnEx.setFont(new Font("serif",0,20));
		btnEx.setBounds(820,150,150,50);
		btnEx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		add(btnEx);
		
		tmDraw = new Timer(20,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
			
		});
		
	}

	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		gr.drawImage(fon, 0, 0, 1000,700, null);
		gr.setColor(Color.WHITE);
		for (int i = 0; i < 7; i++) {
			if(i!=2)gr.drawRect(30+i*110, 15, 72, 97);
		}
		for (int i = 0; i < 7; i++) {
			gr.drawRect(30+i*110, 130, 72, 97);
		}
		
		newGame.drawDeck(gr);
	}

}
