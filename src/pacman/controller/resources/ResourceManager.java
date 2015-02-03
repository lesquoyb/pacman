package pacman.controller.resources;

import com.badlogic.gdx.Gdx;
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
		

	static {
		TextureFactory.loadTexture(pacman, Gdx.files.internal("images/pacman.jpg"));
		TextureFactory.loadTexture(GreenG, Gdx.files.internal("images/fantomeV.jpg"));
		TextureFactory.loadTexture(BlueG, Gdx.files.internal("images/fantomeB.jpg"));
		TextureFactory.loadTexture(YellowG, Gdx.files.internal("images/fantomeJ.jpg"));
		TextureFactory.loadTexture(RedG, Gdx.files.internal("images/fantomeR.jpg"));
		TextureFactory.loadTexture(wall, Gdx.files.internal("images/wall.jpg"));
		TextureFactory.loadTexture(wormhole, Gdx.files.internal("images/wormhole32.jpg"));
		TextureFactory.loadTexture(floor, Gdx.files.internal("images/floor.jpg"));

	}
	
	
	public static Texture getTexture(String s){ return TextureFactory.getTexture(s);}
	
	/*
	
	public static Texture getPacmanText(){return TextureFactory.getTexture(pacman);}
	public static Texture getGreenGText(){return TextureFactory.getTexture(GreenG);}
	public static Texture getBlueGText(){return TextureFactory.getTexture(BlueG);}
	public static Texture getYellowGText(){return TextureFactory.getTexture(YellowG);}
	public static Texture getRedGText(){return TextureFactory.getTexture(RedG);}
	public static Texture getWallText(){return TextureFactory.getTexture(wall);}
	public static Texture getWormholeText(){return TextureFactory.getTexture(wormhole);}
	
	public static Texture getFloorText(){return TextureFactory.getTexture(floor);}
	
	*/


		
	
	
}
