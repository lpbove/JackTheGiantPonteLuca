package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;

import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;


/**
 * Created by Luca on 08/04/2018.
 */

public class HighscoresButtons {


    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private Label scoreLabel;
    private Label coinsLabel;

    private ImageButton backBtn;



    public HighscoresButtons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);
        createAndPositionUIElements();
        addAllListeners();

        stage.addActor(scoreLabel);
        stage.addActor(coinsLabel);
        stage.addActor(backBtn);
    }
    private void createAndPositionUIElements() {

        backBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Options Menu/Back.png"))));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameters.size = 50;

        BitmapFont scoreFont = generator.generateFont(parameters);
//        BitmapFont coinsFont = generator.generateFont(parameters);

        scoreLabel = new Label(String.valueOf(GameManager.getInstance().gameData.getHighscore()),
                new Label.LabelStyle(scoreFont, Color.WHITE));
        coinsLabel = new Label(String.valueOf(GameManager.getInstance().gameData.getCoinHighscore()),
                new Label.LabelStyle(scoreFont, Color.WHITE));

        backBtn.setPosition(17, 17, Align.bottomLeft);

        scoreLabel.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT/2 - 120);
        coinsLabel.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT/2 - 220);

    }
    private void addAllListeners() {

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
    }

    public Stage getStage() {
        return stage;
    }
}
