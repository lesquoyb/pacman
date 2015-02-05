package pacman.controller.resources;


import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class TextureFactory {

	private static HashMap<String,Texture> textures = new HashMap<String, Texture>() ;
	
	
	public static Texture getTexture(String s){
		Texture temp = textures.get(s);
		if(temp == null){
			textures.put(s, new Texture(Gdx.files.internal(ResourceManager.files.get(s))));
			temp = textures.get(s);
		}
		return temp;
	}
	
	
	public static void dispose(){
		for(Texture t : textures.values()){
			t.dispose();
		}
		textures.clear();
	}
}
