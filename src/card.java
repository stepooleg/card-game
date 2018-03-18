import javax.imageio.*;
import java.awt.*;
import java.io.*;

public class card {
	public int x,y;
	public Image img;
	public boolean backOfACard; //признак рубашки карты
	public Image backOfACardImage; //изображение рубашки карты
	public int suit; //масть
	public int typeCard; //тип карты
	public boolean mouseMov; //признак захвата мышью
	public boolean colorSuit; //цвет масти
	
	public card(String path, Image backOfACardImage, int nominal){
		mouseMov=false;
		this.backOfACardImage=backOfACardImage;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = 30;
		y = 15;
		
		backOfACard = true;
		suit = (nominal-1)%4;
		typeCard = (nominal-1)/4;
		colorSuit = true;
		if(suit<=1) colorSuit = false;
	}
	
	public void draw(Graphics gr) {
		if(backOfACard==false) {
			gr.drawImage(img, x, y, 72, 97, null);

		}
		else {
			gr.drawImage(backOfACardImage, x, y, 72, 97, null);
		}
		if(mouseMov==true) {
			gr.setColor(Color.YELLOW);
			gr.drawRect(x,y,72,97);
			
		}
	}
	

}
