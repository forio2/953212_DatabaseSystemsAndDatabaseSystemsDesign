package Chapter4.model;

import Chapter4.view.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Character extends Pane {
    Logger logger = LoggerFactory.getLogger(Character.class.getName());
    //public static final int CHARACTER_WIDTH = 64;
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    //private ImageView imageView;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    char selectPlayer;
    private String url;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;
    //4.3.4
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    int highestJump = 100;
    public void moveLeft() {
        //x = x - xVelocity;
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight() {
        //x = x + xVelocity;
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        //xVelocity = 0;
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void moveX() {
        setTranslateX(x);
        if(isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);
        if(isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity;
        }
        else if(isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity;
        }
    }

    public void moveXX() {
        setTranslateX(x);
        if(isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity+10 : xVelocity+xAcceleration+3;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity+10 : xVelocity+xAcceleration+3;
            x = x + xVelocity;
        }
    }

    public void moveYY() {
        setTranslateY(y);
        if(isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration+50;
            y = y + yVelocity;
        }
        else if(isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity;
        }
    }

    //public Character(int x, int y, KeyCode leftKey, KeyCode rightKey, KeyCode upKey) {
    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                     KeyCode rightKey, KeyCode upKey, String url , int weight, int height, char i) {
        this.x = x;
        this.y = y;
        this.url = url;
        this.selectPlayer = i;
        this.setTranslateX(x);
        this.setTranslateY(y);
        //this.characterImg = new Image(getClass().getResourceAsStream("/Chapter4/assets/StillMario.png"));
        this.characterImg = new Image(getClass().getResourceAsStream(url));
        //this.imageView = new ImageView(characterImg);
        this.imageView = new AnimatedSprite(characterImg,4,4,1,offsetX,offsetY
                ,weight,height);
        if(i == 'A'){
            this.imageView.setFitWidth(CHARACTER_WIDTH);
            this.imageView.setFitHeight(CHARACTER_HEIGHT);
        }
        else if(i == 'B'){
            this.imageView.setFitWidth(CHARACTER_WIDTH+40);
            this.imageView.setFitHeight(CHARACTER_HEIGHT);
        }
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void checkReachGameWall() {
        if(x <= 0) {
            x = 0;
        } else if( x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH-(int)getWidth(); }
    }

    public void jump() {
        if (canJump) {
            //yVelocity = 5;
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }
    public void checkReachHighest () {
        //if(isJumping && y <= Platform.GROUND - CHARACTER_HEIGHT - highestJump) {
        if(isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if(isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void repaint() {
        //setTranslateX(x);
        //setTranslateY(y);
        if(selectPlayer == 'A'){
            moveX();
            moveY();
        }
        else if(selectPlayer == 'B'){
            moveXX();
            moveYY();
        }
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }
}