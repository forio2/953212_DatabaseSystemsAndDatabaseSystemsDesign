package test;

import Chapter5Two.controller.GameLoop;
import Chapter5Two.model.Direction;
import Chapter5Two.model.Food;
import Chapter5Two.model.Snake;
import Chapter5Two.view.Platform;
import javafx.geometry.Point2D;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update;
    private Method collision;
    private Method redraw;
    @Before
    public void init() throws NoSuchMethodException {
        gameLoopUnderTest = new GameLoop(
                new Platform(),
                new Snake(new Point2D(0, 0)),
                new Food());
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);
    }
    private void clockTickHelper() throws InvocationTargetException,
            IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
    }
    @Test
    public void testClockTick() throws InvocationTargetException,
            IllegalAccessException {
        gameLoopUnderTest = new GameLoop(
                new Platform(),
                new Snake(new Point2D(0,0)),
                new Food());
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
    }
    @Test
    public void testNoBack() throws InvocationTargetException,
            IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food());
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,2));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,3));
    }

    @Test
    public void scoreWillIncreaseIfSnakeEatFood() throws InvocationTargetException,
    IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food(new Point2D(0,1)));
        gameLoopUnderTest.setRandom(2);
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getScore(), 1);
    }

    @Test
    public void scoreWillIncreaseFiveIfSnakeEatGreenFood() throws InvocationTargetException,
            IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food(new Point2D(0,1)));
        gameLoopUnderTest.setRandom(1); //green;
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getScore(), 5);
    }
}