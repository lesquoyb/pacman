package pacman.controller.resources;

import com.badlogic.gdx.Gdx;
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
	public static final String floor = "floor";
	public static final String splash = "splash";
	public static final String menuFont = "menuFont";
	public static final String menuAtlas = "menuAtlas";
		

	static {
		TextureFactory.loadTexture(pacman, Gdx.files.internal("images/pacman.jpg"));
		TextureFactory.loadTexture(GreenG, Gdx.files.internal("images/fantomeV.jpg"));
		TextureFactory.loadTexture(BlueG, Gdx.files.internal("images/fantomeB.jpg"));
		TextureFactory.loadTexture(YellowG, Gdx.files.internal("images/fantomeJ.jpg"));
		TextureFactory.loadTexture(RedG, Gdx.files.internal("images/fantomeR.jpg"));
		TextureFactory.loadTexture(wall, Gdx.files.internal("images/wall.jpg"));
		TextureFactory.loadTexture(wormhole, Gdx.files.internal("images/wormhole32.jpg"));
		TextureFactory.loadTexture(floor, Gdx.files.internal("images/floor.jpg"));
		TextureFactory.loadTexture(splash, Gdx.files.internal("ui/splashScreen.jpg"));

		TextureAtlasFactory.loadTextureAtlas(menuAtlas, Gdx.files.internal("ui/buttons.pack"));
	
		FontFactory.loadFont(menuFont, Gdx.files.internal("fonts/white_white_rabbit.fnt"));
	
	}
	
	
	public static Texture getTexture(String s){ return TextureFactory.getTexture(s);}
	
	public static BitmapFont getFont(String s){return FontFactory.getFont(s);}
	
	public static TextureAtlas getTextureAtlas(String s){ return TextureAtlasFactory.getTextAtlas(s);}

	public static void dispose(){
		TextureFactory.dispose();
		TextureAtlasFactory.dispose();
		FontFactory.dispose();
	}
		
	
	
}
