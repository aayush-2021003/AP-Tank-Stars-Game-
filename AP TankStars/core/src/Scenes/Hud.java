package Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameConstants;

import javax.swing.text.View;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer health1;
    private Integer health2;
    private Label player1health;
    private Label player2health;
    private Skin skin;
    private Label health1label;
    private Label health1labe2;

    public Hud (SpriteBatch sb){
//        health1=100;
//        health2=100;
        skin = GameConstants.skin;
        viewport = new FitViewport(800,480,new OrthographicCamera());
//        viewport = new ScreenViewport();
        stage = new Stage(viewport,sb);

        Table root = new Table();
        root.top();
        root.setFillParent(true);

        player1health = new Label("P1 Health",skin);
        player2health = new Label("P2 Health",skin);
//        health1label = new Label(String.format("%03d",health1),skin);
//        health1labe2 = new Label(String.format("%03d",health2),skin);

        root.add(player1health).expandX().padTop(10);
        root.add(player2health).expandX().padTop(10);
//        root.row();
//        root.add(health1label);
//        root.add(health1labe2);

        stage.addActor(root);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
