import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class game {
	
	public Image backOfACardImage;
	private pack[] pack;
	private boolean primStep;
	public boolean endGame;
	
	public game(){
		try {
			backOfACardImage = ImageIO.read(new File("C:\\kos\\k0.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pack = new pack[13];
		
		for (int i = 0; i < pack.length; i++) {
			pack[i] = new pack();
		}
		
		start();
		
	}
	
	public void mouseDragget(int mX, int mY) {
		
	}
	
	public void mousePressed(int mX, int mY) {
		
	}
	
	public void mouseDoublePressed(int mX, int mY) {
		
	}
	
	public void mouseReleased(int mX, int mY) {
		int num = getNumPackPress(mX, mY);
		
		if(num == 0) {
			toDealTheCards();
		}
	}
	
	private int getNumPackPress(int mX, int mY) {
		
		int num = -1;
		if((mY>=15)&&(mY<=(15+97))) {
			if((mX>=30)&&(mX<=(30+72))) num = 0;
			if((mX>=140)&&(mX<=(140+72))) num = 1;
			if((mX>=360)&&(mX<=(360+72))) num = 2;
			if((mX>=470)&&(mX<=(470+72))) num = 3;
			if((mX>=580)&&(mX<=(580+72))) num = 4;
			if((mX>=690)&&(mX<=(690+72))) num = 5;
		}
		
		else if((mY>=130)&&(mY<=(700))) {
			if ((mX>=30)&&(mX<=110*7)) {
				if(((mX-30)%110)<=72) {
					num = (mX-30)/110;
					num +=6;
				}
			}
		}
		return num;
	}
	
	private void toDealTheCards() {
		if(pack[0].size()>0) {
			int num;
			if(primStep == true) {
				num = (int)(Math.random()*pack[0].size());
			}
			else {
				num = pack[0].size()-1;
			}
			card getCard = pack[0].get(num);
			getCard.backOfACard = false;
			System.out.println(getCard.backOfACard);
			getCard.x +=110;
			pack[1].add(getCard);
			pack[0].remove(num);
		}
		else {
			int numEnd = pack[1].size()-1;
			for (int i = numEnd; i >=0; i--) {
				card getCard = pack[1].get(i);
				getCard.backOfACard = true;
				getCard.x -= 110;
				pack[0].add(getCard);
			}
			pack[1].clear();
			primStep = false;
		}
	}
	
	public void start() {
		for (int i = 0; i < pack.length; i++) {
			pack[i].clear();
		}
		
		load();
		endGame = false;
		primStep=true;
	}
	
	private void load() {
		for (int i = 1; i <= 52; i++) {
			pack[0].add(new card("C:\\kos\\k"+(i)+".png",backOfACardImage,i));
		}
	}
	
	public void drawDeck(Graphics gr) {
		if(pack[0].size()>0) {
			pack[0].get(pack[0].size()-1).draw(gr);
		}
		
		if(pack[1].size()>1)
		{
			pack[1].get(pack[1].size()-2).draw(gr);
			pack[1].get(pack[1].size()-1).draw(gr);
		}
		else if(pack[1].size()==1) {
			pack[1].get(pack[1].size()-1).draw(gr);
		}
	}

}
