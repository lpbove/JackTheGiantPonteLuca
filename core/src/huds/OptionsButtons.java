package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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

public class OptionsButtons {
    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton easy, medium, hard;
    private Image sign;

    private ImageButton backBtn;

    public OptionsButtons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());

        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(sign);
        stage.addActor(backBtn);

    }

    private void createAndPositionButtons() {

        easy = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Options Menu/Easy.png"))));

        medium = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Options Menu/Medium.png"))));

        hard = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Options Menu/Hard.png"))));

        backBtn = new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Options Menu/Back.png"))));

        sign = new Image(
                new Texture("Buttons/Options Menu/Check Sign.png"));

        backBtn.setPosition(17, 17, Align.bottomLeft);

        easy.setPosition(  GameInfo.WIDTH/2, GameInfo.HEIGHT/2 + 40, Align.center);
        medium.setPosition(GameInfo.WIDTH/2, GameInfo.HEIGHT/2 - 40, Align.center);
        hard.setPosition(  GameInfo.WIDTH/2, GameInfo.HEIGHT/2 - 120, Align.center);

        positionTheSign();

    }

    void addAllListeners(){
        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(easy.getY() + 13);
            }
        });

        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(medium.getY() + 13);
            }
        });

        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(hard.getY() + 13);
            }
        });

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

    }

    void positionTheSign() {

        if(GameManager.getInstance().gameData.isEasyDifficulty()){
            sign.setPosition(GameInfo.WIDTH / 2 +76, easy.getY() +13, Align.bottomLeft);
        }

        if(GameManager.getInstance().gameData.isMediumDifficulty()){
            sign.setPosition(GameInfo.WIDTH / 2 +76, medium.getY() +13, Align.bottomLeft);
        }

        if(GameManager.getInstance().gameData.isHardDifficulty()){
            sign.setPosition(GameInfo.WIDTH / 2 +76, hard.getY() +13, Align.bottomLeft);
        }
    }

    void changeDifficulty(int difficulty){
        switch (difficulty){
            case 0:
                GameManager.getInstance().gameData.setEasyDifficulty(true);
                GameManager.getInstance().gameData.setMediumDifficulty(false);
                GameManager.getInstance().gameData.setHardDifficulty(false);
                break;
            case 1:
                GameManager.getInstance().gameData.setEasyDifficulty(false);
                GameManager.getInstance().gameData.setMediumDifficulty(true);
                GameManager.getInstance().gameData.setHardDifficulty(false);
                break;
            case 2:
                GameManager.getInstance().gameData.setEasyDifficulty(false);
                GameManager.getInstance().gameData.setMediumDifficulty(false);
                GameManager.getInstance().gameData.setHardDifficulty(true);
                break;

        }

        GameManager.getInstance().saveData();
    }

    public Stage getStage() {
        return stage;
    }
}
