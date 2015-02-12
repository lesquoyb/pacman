package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class ResourceManager {

	//simples textures
	public static final String wall = "wall";
	public static final String wormhole = "wormhole";
	public static final String rawFloor = "floor";
	public static final String floorGum = "floorGum";
	public static final String splash = "splash";
	public static final String menuFont = "menuFont";
	public static final String menuAtlas = "menuAtlas";
	public static final String fondScore = "fondScore";
	public static final String defaite = "defaite";
	public static final String victoire = "victoire";
	public static final String qContinue = "continue";
	
	//animation
	public static final String GreenGLeft = "GreenGLeft";
	public static final String GreenGRight = "GreenGRight";
	public static final String GreenGUp = "GreenGUp";
	public static final String GreenGDown = "GreenGDown";
	public static final String pacmanLeft = "GreenGLeft";
	public static final String pacmanRight = "GreenGRight";
	public static final String pacmanUp = "GreenGUp";
	public static final String pacmanDown = "GreenGDown";
	public static final String RedGLeft = "GreenGLeft";
	public static final String RedGRight = "GreenGRight";
	public static final String RedGUp = "GreenGUp";
	public static final String RedGDown = "GreenGDown";
	public static final String BlueGLeft = "GreenGLeft";
	public static final String BlueGRight = "GreenGRight";
	public static final String BlueGUp = "GreenGUp";
	public static final String BlueGDown = "GreenGDown";
	public static final String YellowGLeft = "GreenGLeft";
	public static final String YellowGRight = "GreenGRight";
	public static final String YellowGUp = "GreenGUp";
	public static final String YellowGDown = "GreenGDown";
	
	
	public static HashMap<String,String>  files  = new HashMap<String,String>();

	static {
		
		files.put(wall, "images/wall.jpg");
		files.put(wormhole, "images/wormhole.jpg");
		files.put(rawFloor, "images/floor.jpg");
		files.put(splash,"ui/splashScreen.jpg" );
		files.put(floorGum, "images/floorGum.jpg");
		files.put(menuFont, "fonts/white_white_rabbit.fnt");
		files.put(fondScore, "images/fondScore.png");
		files.put(defaite, "ui/defaite.jpg");
		files.put(victoire, "ui/victoire.jpg");
		files.put(menuAtlas, "ui/buttons.pack");
		files.put(qContinue, "ui/continue.jpg");
		
		files.put(GreenGLeft, "animations/cop/left/GreenGLeft.pack");
		files.put(GreenGRight, "animations/cop/right/GreenGRight.pack");
		files.put(GreenGDown, "animations/cop/up/GreenGUp.pack");
		files.put(GreenGUp, "animations/cop/down/GreenGDown.pack");

		
	
	
	}
	
	
	public static TextureRegion getTextureRegion(String s){return AnimationFactory.getTextureRegion(s);}
	
	public static TextureRegion getTexture(String s,boolean isAnimated){ 
		if(! isAnimated)
		return new TextureRegion(TextureFactory.getTexture(s));
		else{
			return AnimationFactory.getTextureRegion(s);
		}
	}
	
	public static BitmapFont getFont(String s){return FontFactory.getFont(s);}
	
	public static TextureAtlas getTextureAtlas(String s){ return TextureAtlasFactory.getTextAtlas(s);}

	public static void setFontColor(String font, Color c){ FontFactory.setFontColor(font, c);}
	
	public static void dispose(){
		TextureFactory.dispose();
		TextureAtlasFactory.dispose();
		FontFactory.dispose();
	}
		
	
	
}
