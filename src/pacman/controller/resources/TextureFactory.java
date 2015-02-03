package pacman.controller.resources;


import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class TextureFactory {

	private static HashMap<String,Texture> textures = new HashMap<String, Texture>() ;
	private static Texture currentText = null;
	
	public static Texture getTexture(String s){
		currentText = textures.get(s);
		if (currentText == null){
			currentText = new Texture(Gdx.files.internal("images/"+ s +".jpg"));
			textures.put(s,currentText);
		}
		return currentText;
	}
	
}
