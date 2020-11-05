package Chapter4.view;

import Chapter4.model.Character;
import Chapter4.model.Keys;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public final static int GROUND = 300;
    private Image platformImg;
    private Character character;
    private Character character2;
    private Keys keys;
    public Platform() {
        keys = new Keys();
        platformImg = new Image(getClass().getResourceAsStream("/Chapter4/assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        //character = new Character(30, 30,KeyCode.A,KeyCode.D,KeyCode.W);
        character = new Character(30, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W, "/Chapter4/assets/MarioSheet.png", 16, 32, 'A');
        character2 = new Character(30, 30,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP , "/Chapter4/assets/MegamanSheet.png", 540, 460, 'B');
        getChildren().addAll(backgroundImg, character, character2);
    }
    public Character getCharacter() { return character; }
    public Character getCharacter2() { return character2; }

    public Keys getKeys() { return keys; }
}