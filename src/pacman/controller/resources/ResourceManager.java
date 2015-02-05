package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class ResourceManager {

		
	public static final String pacman = "pacman";
	public static final String GreenG = "fantomeV";
	public static final String BlueG = "fantomeB";
	public static final String RedG = "fantomeR";
	public static final String YellowG = "fantomeJ";
	public static final String wall = "wall";
	public static final String wormhole = "wormhole32";
	public static final String rawFloor = "floor";
	public static final String floorGum = "floorGum";
	public static final String splash = "splash";
	public static final String menuFont = "menuFont";
	public static final String menuAtlas = "menuAtlas";
	public static final String fondScore = "fondScore";
	public static final String defaite = "defaite";
	public static final String victoire = "victoire";
	
	public static HashMap<String,String>  files  = new HashMap<String,String>();

	static {
		
		
		files.put(pacman, "images/pacman.jpg");
		files.put(GreenG,"images/fantomeV.jpg" );
		files.put(BlueG, "images/fantomeB.jpg");
		files.put(YellowG, "images/fantomeJ.jpg");
		files.put(RedG, "images/fantomeR.jpg");
		files.put(wall, "images/wall.jpg");
		files.put(wormhole, "images/wormhole32.jpg");
		files.put(rawFloor, "images/floor.jpg");
		files.put(splash,"ui/splashScreen.jpg" );
		files.put(floorGum, "images/floorGum.jpg");
		files.put(menuFont, "fonts/white_white_rabbit.fnt");
		files.put(fondScore, "images/fondScore.png");
		files.put(defaite, "ui/defaite.jpg");
		files.put(victoire, "ui/victoire.jpg");


		TextureAtlasFactory.loadTextureAtlas(menuAtlas, Gdx.files.internal("ui/buttons.pack"));
	
	
	}
	
	
	public static Texture getTexture(String s){ return TextureFactory.getTexture(s);}
	
	public static BitmapFont getFont(String s){return FontFactory.getFont(s);}
	
	public static TextureAtlas getTextureAtlas(String s){ return TextureAtlasFactory.getTextAtlas(s);}

	public static void setFontColor(String font, Color c){ FontFactory.setFontColor(font, c);}
	
	public static void dispose(){
		TextureFactory.dispose();
		TextureAtlasFactory.dispose();
		FontFactory.dispose();
	}
		
	
	
}
