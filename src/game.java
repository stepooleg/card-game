import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Iterator;

import javax.imageio.*;

public class game {
	
	public Image backOfACardImage;
	private pack[] pack;
	private boolean primStep;
	public boolean endGame;
	private int numPack;
	private int numCard;
	private int dx, dy;
	private int oldX, oldY;
	private Timer tmEndGame;
	
	
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
		tmEndGame = new Timer(100,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(int i=2;i<=5;i++) {
					card getCard = pack[i].get(0);
					pack[i].add(getCard);
					pack[i].remove(0);
				}
			}
			
		});
		
		start();
		
	}
	
	public void mouseDragget(int mX, int mY) {
		if(numPack>=0) {
			card getCard = pack[numPack].get(numCard);
			getCard.x = mX-dx;
			getCard.y = mY-dy;

			if(getCard.x<0) getCard.x =0;
			if(getCard.x>720) getCard.x =720;
			if(getCard.y<0) getCard.y =0;
			if(getCard.y>650) getCard.y =650;
			
			int y = 20;
			for (int i = numCard+1; i < pack[numPack].size(); i++) {
				pack[numPack].get(i).x = getCard.x;
				pack[numPack].get(i).y = getCard.y+y;
				y+=20;
			}
			
		}
	}
	
	public void mousePressed(int mX, int mY) {
		int num = getNumPackPress(mX, mY);
		setEntered(num, mX, mY);
	}
	
	public void mouseDoublePressed(int mX, int mY) {
		int num = getNumPackPress(mX,mY);
		if((num==1)||(num>=6)&&(num<=12));
		{
			if(pack[num].size()>0){
				int numLast = pack[num].size()-1;
				card getCard = pack[num].get(numLast);
				if((mY>=getCard.y)&&(mY<=(getCard.y+97))){
					for(int i=2; i<=5; i++){
						int rez = -1;
						if(pack[i].size()==0){
							if(getCard.typeCard==12){
								rez=i;
							}
						}
						else{
							int numLast2 = pack[i].size()-1;
							card getCard2 = pack[i].get(numLast2);
							if((getCard2.typeCard==12)&&(getCard.suit==getCard2.suit)&&(getCard.typeCard==0)){
								rez=i;
							}
							else if((getCard2.typeCard>=0)&&(getCard.suit==getCard2.suit)&&(getCard.typeCard<11)){
								if(getCard2.typeCard+1==getCard.typeCard){
									rez=i;
								}
							}
						}
						if(rez>=0){
							getCard.x=(110*(rez+1))+30;
							getCard.y = 15;
							pack[rez].add(getCard);
							pack[num].remove((numLast));
							testEndGame();
							break;
						}
					}
				}
			}
		}
		openCard();
	}
	
	public void mouseReleased(int mX, int mY) {
		int num = getNumPackPress(mX, mY);
		
		if(numPack!=-1) {
			pack[numPack].get(numCard).mouseMov = false;
			if((num==-1)||(testMove(numPack,num)==false)){
			    int y = 0;
                for (int i =numCard; i <pack[numPack].size() ; i++) {
                    card getCard = pack[numPack].get(i);
                    getCard.x = oldX;
                    getCard.y = oldY+y;
                    y+=20;
                }
            }
            numPack = -1;
			numCard = -1;
			openCard();
		}
		else{
		    if(num==0){
		        toDealTheCards();
            }
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
		toDealCards();
		endGame = false;
		primStep=true;
		numCard = -1;
		numPack =-1;
	}
	
	private void load() {
		for (int i = 1; i <= 52; i++) {
			pack[0].add(new card("C:\\kos\\k"+(i)+".png",backOfACardImage,i));
		}
	}
	
	public void drawDeck(Graphics gr) {
		//redrawing the deck �1
		if(pack[0].size()>0) {
			pack[0].get(pack[0].size()-1).draw(gr);
		}
		//redrawing the deck �2
		if(pack[1].size()>1)
		{
			pack[1].get(pack[1].size()-2).draw(gr);
			pack[1].get(pack[1].size()-1).draw(gr);
		}
		else if(pack[1].size()==1) {
			pack[1].get(pack[1].size()-1).draw(gr);
		}
		// four home tutus
		
		for (int i = 2; i<=5; i++) {
			if (pack[i].size()>1) {
				pack[i].get(pack[i].size()-2).draw(gr);
				pack[i].get(pack[i].size()-1).draw(gr);
			}
			else if (pack[i].size()==1) {
				pack[i].get(pack[i].size()-1).draw(gr);
			}
		}
		
		//bottom seven tutus
		for (int i = 6; i <pack.length; i++) {
			if (pack[i].size()>0) {
				for (int j = 0; j < pack[i].size(); j++) {
					if(pack[i].get(j).mouseMov == true) break;
					pack[i].get(j).draw(gr);
				}
			}
			
		}
		//mouse Move card
		if(numPack!=-1) {
			for(int i=numCard;i<pack[numPack].size();i++) {
				pack[numPack].get(i).draw(gr);
			}
		}
		
		
	}
	
	private void toDealCards() {
		int x = 30;
		for (int i = 6; i < pack.length; i++) {
			for (int j = 6; j <=i; j++) {
				int rnd = (int)(Math.random()*pack[0].size());
				card getCard = pack[0].get(rnd);
				if(j<i) getCard.backOfACard = true;
				else getCard.backOfACard = false;
				getCard.x = x;
				getCard.y = 130+pack[i].size()*20;
				pack[i].add(getCard);
				pack[0].remove(rnd);
				
			}
			x+=110;
		}
	}
	
	private void testEndGame() {
		if((pack[2].size()==12)&&(pack[3].size()==12)&(pack[4].size()==12)&&(pack[5].size()==12)){
			endGame = true;
			tmEndGame.start();
		}
	}
	
	private void openCard() {
		for(int i=6;i<=12;i++) {
			if(pack[i].size()>0) {
				int numLast = pack[i].size()-1;
				card getCard = pack[i].get(numLast);
				if(getCard.backOfACard==true) getCard.backOfACard = false;
			}
		}
	}
	private void setEntered(int num, int mX, int mY) {
		if((num>=1) && (num<=5)) {
			if(pack[num].size()>0) {
				int numLast = pack[num].size()-1;
				card getCard = pack[num].get(numLast);
				getCard.mouseMov = true;
				numCard = numLast;
				numPack = num;
				dx = mX- getCard.x;
				dy = mY- getCard.y;
				
				oldX = getCard.x;
				oldY = getCard.y;
			}
		}
		else if((num>=6)&&(num<=12)) {
			if(pack[num].size()>0) {
				int numLast = pack[num].size()-1;
				card getCard = pack[num].get(numLast);
				int numEntered = -1;
				if((mY>=getCard.y)&&(mY<=(getCard.y+97))) {
					numEntered = numLast;
				}
				else if (mY<getCard.y) {
					numEntered = (mY-130)/20;
					if(pack[num].get(numEntered).backOfACard==true) {
						numEntered=-1;
					}
				}
				if(numEntered!=-1) {
					card getCardEntered = pack[num].get(numEntered);
					if(getCardEntered.backOfACard==false) {
						getCardEntered.mouseMov=true;
						numCard = numEntered;
						numPack = num;
						dx = mX-getCardEntered.x;
						dy = mY-getCardEntered.y;
						oldX = getCardEntered.x;
						oldY = getCardEntered.y;

					}
				}
				
			}
		}
	}
	private boolean testMove(int num1, int num2){
	    boolean rez = false;
	    card getCard1 = pack[num1].get(numCard);
        card getCard2 = null;

        if(pack[num2].size()>0){
            getCard2 = pack[num2].get(pack[num2].size()-1);
        }
        if((num2>=2)&&(num2<=5)){
            if(numCard==(pack[num1].size()-1)){
                if(getCard2==null){
                    if(getCard1.typeCard==12) rez = true;

                }
                else if ((getCard2.typeCard==12)&&(getCard1.suit==getCard2.suit)&&(getCard1.typeCard==0)){
                    rez = true;
                }
                else if ((getCard2.typeCard>=0)&&(getCard1.suit==getCard2.suit)&&(getCard2.typeCard<11)){
                    if(getCard2.typeCard+1 == getCard1.typeCard){
                        rez = true;
                    }
                }
                if(rez==true){
                    getCard1.x = (110*(num2+1)+30);
                    getCard1.y = 15;
                    pack[num2].add(getCard1);
                    pack[num1].remove(numCard);
                    testEndGame();
                }
            }
        }
        if((num2>=6)&&(num2<=12)){
            int x = 30+(num2-6)*110;
            int y = 130;
            if(getCard2 == null){
                if(getCard1.typeCard==11) rez = true;
            }
            else {
                if(getCard2.backOfACard==false){
                    if(getCard2.typeCard!=12){
                        if((getCard2.typeCard==getCard1.typeCard+1)||((getCard2.typeCard==0)&&(getCard1.typeCard==12))){
                            if(getCard2.colorSuit!=getCard1.colorSuit){
                                y = getCard2.y+20;
                                rez = true;
                            }
                        }
                    }
                }
            }

            if(rez==true){
                for(int i=numCard; i<pack[num1].size();i++){
                    card getCard_ = pack[num1].get(i);
                    getCard_.x = x;
                    getCard_.y = y;
                    pack[num2].add(getCard_);
                    y+=20;
                }
                for(int i=pack[num1].size()-1;i>=numCard;i--){
                    pack[num1].remove(i);
                }
            }
        }
        return rez;

    }

}
