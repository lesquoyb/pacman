package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundFactory {

	
	
private static HashMap<String,Music> sounds = new HashMap<String, Music>() ;
	
	
	public static Music getSound(String s){
		Music temp = sounds.get(s);
		if(temp == null){
			sounds.put(s, Gdx.audio.newMusic(Gdx.files.internal(ResourceManager.files.get(s))));
			temp = sounds.get(s);
		}
		return temp;
	}
	
	
	public static void dispose(){
		for(Music t : sounds.values()){
			t.dispose();
		}
		sounds.clear();
	}
	
	
	
}
