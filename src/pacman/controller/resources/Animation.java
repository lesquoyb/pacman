package pacman.controller.resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

	
	private TextureRegion[] textures;
	private int currentFrame;
	private float delay;
	private float time;
	private static float defaultDelay = 1/12f;
	
	public Animation (Array<AtlasRegion> textures){
		this(textures,defaultDelay);
	}
	public Animation(Array<AtlasRegion> textures,float delay){
		time = 0;
		this.delay = delay;
		int i = 0;
		this.textures = new AtlasRegion[textures.size];
		for(AtlasRegion a : textures){
			this.textures[i] = a;
			this.textures[i].flip(false, true);
			i++;
		}
		currentFrame = 0;
	}
	
	
	
	public void update(float delta){
		if(delay <= 0) return;
		time += delta;
		while(time >= delay){
			step();
		}
	}
	
	private void step(){
		time -= delay;
		currentFrame++;
		if(currentFrame == textures.length){
			currentFrame = 0;
		}
	}
	
	public TextureRegion getTexture(){return textures[currentFrame];}
}
