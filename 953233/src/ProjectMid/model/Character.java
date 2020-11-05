package ProjectMid.model;

import ProjectMid.view.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.Random;


public class Character extends Pane {
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private int hp = 20;
    private Image characterImg;
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
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    Random GenObj;

    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        //xVelocity = 0;
        isMoveLeft = false;
        isMoveRight = false;
        if (selectPlayer == 'B'){
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity;
        }
    }
    public void moveX() {
        setTranslateX(x);
        if (selectPlayer == 'A'){
            if(isMoveLeft) {
                xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x - xVelocity;
            }
            if(isMoveRight) {
                xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x + xVelocity;
            }
        }
        else if(selectPlayer == 'B'){
            if(isMoveLeft) {
                xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x - xVelocity;
            }
            if(isMoveRight) {
                xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
                x = x - xVelocity;
            }
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

    //public Character(int x, int y, KeyCode leftKey, KeyCode rightKey, KeyCode upKey) {
    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                     KeyCode rightKey, KeyCode upKey, String url , int weight, int height, char i) {
        this.x = x;
        this.y = y;
        this.url = url;
        this.selectPlayer = i;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(getClass().getResourceAsStream(url));
        this.imageView = new AnimatedSprite(characterImg,4,4,1,offsetX,offsetY
                ,weight,height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void collided(Character c) {
        if (isMoveLeft) {
            hp--;
            stop();
        }
        else if (isMoveRight) {
            if(selectPlayer == 'A'){
                x = c.getX() - CHARACTER_WIDTH + 1;
            }
            hp--;
            stop();
        }
    }

    private int getX() {
        return x;
    }

    public void checkReachGameWall() {
        if(x <= 0) {
            x = 0;
        } else if( x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH-(int)getWidth(); }
    }

    public void checkReachGameWallObj() {
        GenObj = new Random();
        if(x <= Platform.WIDTH-800) {
            x = Platform.WIDTH+50;
        }
    }

    public void jump() {
        if (selectPlayer == 'A') {
            if (canJump) {
                yVelocity = yMaxVelocity;
                canJump = false;
                isJumping = true;
                isFalling = false;
            }
        }
    }
    public void checkReachHighest () {
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
        moveX();
        moveY();
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

    public char getSelectPlayer(){
        return selectPlayer;
    }

    public int getHp() {
        if (hp < 0){
            hp = 0;
            System.exit(0);
        }
        return hp;
    }
}