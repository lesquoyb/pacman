package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontFactory {

	
private static HashMap<String,BitmapFont> fonts = new HashMap<String, BitmapFont>() ;
	
	
	public static BitmapFont getFont(String s){
		BitmapFont temp = fonts.get(s);
		if(temp == null){
			fonts.put(s, new BitmapFont(Gdx.files.internal(ResourceManager.files.get(s))));
			temp = fonts.get(s);
		}
		return temp;
	}
	
	
	public static void setFontColor(String font, Color c){
		getFont(font).setColor(c);
	}
	
	public static void dispose(){
		for(BitmapFont t : fonts.values()){
			t.dispose();
		}
		fonts.clear();
	}
	
}
