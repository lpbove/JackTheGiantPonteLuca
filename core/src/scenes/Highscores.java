package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import helpers.GameInfo;
import huds.HighscoresButtons;

/**
 * Created by Luca on 08/04/2018.
 */

public class Highscores implements Screen {
    private GameMain game;

    private OrthographicCamera mainCamera;
    private Viewport gameViewport;

    private Texture bg;

    private HighscoresButtons btns;


    @Override
    public void show() {

    }


    public Highscores(GameMain game) {
        this.game = game;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        mainCamera.position.set(GameInfo.WIDTH/2, GameInfo.HEIGHT, 0);

        gameViewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, mainCamera);

        bg = new Texture("Backgrounds/Highscore BG.png");

        btns = new HighscoresButtons(game);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        game.getBatch().begin();

        game.getBatch().draw(bg, 0, 0);

        game.getBatch().end();

        game.getBatch().setProjectionMatrix(
                btns.getStage().getCamera().combined);

        btns.getStage().draw();
    }



    @Override
    public void resize(int width, int height) {

        gameViewport.update(width, height);
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
        bg.dispose();
        btns.getStage().dispose();
    }
}
