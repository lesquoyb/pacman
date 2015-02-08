package pacman.view.screens;

import pacman.controller.MainMenuController;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {


	private Stage stage;
	private Table table;
	private TextButton playBtn, exitBtn;
	private Label title;
	private Skin skin;
	private MainMenuController controller;
	
	
	public void setController(MainMenuController c){
		controller = c;
	}
	
	
	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		BitmapFont bmFont = ResourceManager.getFont(ResourceManager.menuFont);
		TextureAtlas atlas = ResourceManager.getTextureAtlas(ResourceManager.menuAtlas);
		skin = new Skin(atlas);
		table = new Table(skin);
		
		title = new Label("PuckMan l'incroyable", new LabelStyle(bmFont,Color.YELLOW));
		title.setFontScale(1.5f);
		
		TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.up = skin.getDrawable("up_button");
		buttonStyle.down = skin.getDrawable("down_button");
		buttonStyle.pressedOffsetX = 1;
		buttonStyle.pressedOffsetY = -1;
		buttonStyle.font = bmFont;
		
		playBtn = new TextButton("Play", buttonStyle);
		playBtn.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				controller.Play();
			}
			
		});
		exitBtn = new TextButton("Exit", buttonStyle);
		exitBtn.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				controller.exit();
			}
			
		});

		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		table.add(title).spaceBottom(100);
		table.row().height(100).center();
		table.add(playBtn);
		table.add(exitBtn);
		
		
		
		stage.addActor(table);

				
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//table.drawDebug();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
	}

}
