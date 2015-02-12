package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFactory {

	
	private static HashMap<String,Animation> regions = new HashMap<String, Animation>() ;
	
	
	public static TextureRegion getTextureRegion(String s){
		Animation temp = regions.get(s);
		if(temp == null){
			regions.put(s,new Animation(TextureAtlasFactory.getTextAtlas(s).getRegions())  );
			temp = regions.get(s);
		}
		return temp.getTexture();
	}
	
	public static void update(float seconds){
		for(Animation a: regions.values()){
			a.update(seconds);
		}
	}
	public static void dispose(){
		regions.clear();
	}
	
}
