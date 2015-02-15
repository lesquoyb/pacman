package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
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
	public static final String menuAtlas = "menuAtlas";
	public static final String defaite = "defaite";
	public static final String victoire = "victoire";
	public static final String qContinue = "continue";
	public static final String heart = "heart";
	public static final String redCircle = "redCircle";
	
	
	
	//animation
	public static final String GreenGLeft = "GreenGLeft";
	public static final String GreenGRight = "GreenGRight";
	public static final String GreenGUp = "GreenGUp";
	public static final String GreenGDown = "GreenGDown";
	public static final String pacmanLeft = "GreenGLeft";
	public static final String pacmanRight = "GreenGRight";
	public static final String pacmanUp = "GreenGUp";
	public static final String pacmanDown = "GreenGDown";
	public static final String RedGLeft = "RedLeft";
	public static final String RedGRight = "RedRight";
	public static final String RedGUp = "RedUp";
	public static final String RedGDown = "RedDown";
	public static final String BlueGLeft = "GreenGLeft";
	public static final String BlueGRight = "GreenGRight";
	public static final String BlueGUp = "GreenGUp";
	public static final String BlueGDown = "GreenGDown";
	public static final String YellowGLeft = "YellowLeft";
	public static final String YellowGRight = "YellowGRight";
	public static final String YellowGUp = "YellowUp";
	public static final String YellowGDown = "YellowDown";
	
	//sounds
	public static final String pacmanDeath = "pacmanDeath";
	public static final String mainTheme = "mainTheme";
	public static final String countdown = "countdown";
	
	
	//fonts
	public static final String menuFont = "menuFont";
	public static final String scoreFont = "scoreFont";
	public static final String titleFont = "titleFont";
	
	
	public static HashMap<String,String>  files  = new HashMap<String,String>();

	static {
		
		//images
		files.put(wall, "images/wall.jpg");
		files.put(wormhole, "images/wormhole.jpg");
		files.put(rawFloor, "images/floor.jpg");
		files.put(splash,"ui/splashScreen.jpg" );
		files.put(floorGum, "images/floorGum.jpg");
		files.put(defaite, "ui/defaite.jpg");
		files.put(victoire, "ui/victoire.jpg");
		files.put(menuAtlas, "ui/buttons.pack");
		files.put(qContinue, "ui/continue.jpg");
		files.put(heart, "images/heart.png");
		files.put(redCircle, "images/circle.png");
		
		
		
		//animations
		files.put(RedGDown, "animations/flying robot/down/FlyingRobotDown.pack");
		files.put(RedGUp, "animations/flying robot/up/FlyingRobotUp.pack");
		files.put(RedGLeft, "animations/flying robot/left/FlyingRobotLeft.pack");
		files.put(RedGRight, "animations/flying robot/right/FlyingRobotRight.pack");
		files.put(YellowGDown, "animations/sentinel/down/SentinelDown.pack");
		files.put(YellowGUp, "animations/sentinel/up/SentinelUp.pack");
		files.put(YellowGLeft, "animations/sentinel/left/SentinelLeft.pack");
		files.put(YellowGRight, "animations/sentinel/right/SentinelRight.pack");
		files.put(GreenGLeft, "animations/cop/left/copLeft.pack");
		files.put(GreenGRight, "animations/cop/right/CopRight.pack");
		files.put(GreenGDown, "animations/cop/up/CopUp.pack");
		files.put(GreenGUp, "animations/cop/bottom/CopBottom.pack");
		

		
		//sounds
		files.put(mainTheme, "sounds/mainTheme.wav");
		files.put(pacmanDeath, "sounds/death.wav");
		files.put(countdown, "sounds/countdown.wav");
		
		//fonts
		files.put(menuFont, "fonts/fallout.fnt");
		files.put(scoreFont, "fonts/score.fnt");
		files.put(titleFont, "fonts/title.fnt");
		
	
	
	}
	
	public static Music getSound(String s){return SoundFactory.getSound(s);}
	
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
