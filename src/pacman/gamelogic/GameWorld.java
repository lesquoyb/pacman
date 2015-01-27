package pacman.gamelogic;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameWorld {
	
	private SpriteBatch batch;
	private Map map;
	private OrthographicCamera camera;
	public static World world;
	private Box2DDebugRenderer debugRenderer;
	
	
	
	public GameWorld(){
		try {
			world = new World(new Vector2(0,0),true);
			debugRenderer = new Box2DDebugRenderer();

			camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch = new SpriteBatch();
			FileHandle map1 = Gdx.files.internal("config/map1.map");
			map = new Map( map1 );
			camera.projection.translate(new Vector3( - Gdx.graphics.getWidth()/2,- Gdx.graphics.getHeight()/2,0));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			Gdx.app.exit();
		}
	}
	
	
	public void render(){

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
			map.render(batch);
			debugRenderer.render(world, camera.projection);
		batch.end();
	}
	
	public void resize(int width,int height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;	
	}
	
	public void update(){
		map.update();
		world.step(0.12f, 6, 2);
	}
	
	
	public void dispose(){
		world.dispose();
		map.dispose();
		batch.dispose();
		debugRenderer.dispose();
	}

	
}
