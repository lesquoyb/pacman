package pacman.controller.resources;

import com.badlogic.gdx.graphics.Texture;

public final class ResourceManager {

		
	public static final String pacman = "pacman";
	public static final String GreenG = "fantomeV";
	public static final String BlueG = "fantomeB";
	public static final String RedG = "fantomeR";
	public static final String YellowG = "fantomeJ";
	public static final String wall = "wall";
	public static final String wormhole = "wormhole32";
	public static final String floor = "floor";
		

	
	
	public static Texture getPacmanText(){return TextureFactory.getTexture(pacman);}
	public static Texture getGreenGText(){return TextureFactory.getTexture(GreenG);}
	public static Texture getBlueGText(){return TextureFactory.getTexture(BlueG);}
	public static Texture getYellowGText(){return TextureFactory.getTexture(YellowG);}
	public static Texture getRedGText(){return TextureFactory.getTexture(RedG);}
	public static Texture getWallText(){return TextureFactory.getTexture(wall);}
	public static Texture getWormholeText(){return TextureFactory.getTexture(wormhole);}
	
	public static Texture getFloorText(){return TextureFactory.getTexture(floor);}


		
	
	
}
