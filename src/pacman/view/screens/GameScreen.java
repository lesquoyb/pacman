package pacman.view.screens;

import javax.swing.JOptionPane;

import pacman.models.Map;
import pacman.models.Pacman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen {

	

	SpriteBatch batch;
	Texture img;
	Map map;
	OrthographicCamera camera;
	World world;
	Box2DDebugRenderer debugRenderer;
	
	
	@Override
	public void show() {
		try {
			world = new World(new Vector2(0,0),true);
			debugRenderer = new Box2DDebugRenderer();

			camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch = new SpriteBatch();
			FileHandle map1 = Gdx.files.internal("config/map1.map");
			map = new Map(map1);
			Pacman pacman = new Pacman((byte)0,(byte) 0);
			map.elements.add(pacman);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			Gdx.app.exit();
		}
	}

	@Override
	public void render(float delta) {
		
		map.update();

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		//map.render(batch);
		debugRenderer.render(world, camera.projection);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
