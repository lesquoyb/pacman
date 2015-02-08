package pacman.controller.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

	
	private TextureRegion[] textures;
	private int currentFrame;
	private float delay;
	private float time;
	private static float defaultDelay = 1/12f;
	
	public Animation (TextureRegion[] textures){
		new Animation(textures,defaultDelay);
	}
	public Animation(TextureRegion[] textures,float delay){
		time = 0;
		this.delay = delay;
		this.textures = textures;
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
