package ProjectMid.controller;
import ProjectMid.model.Character;
import ProjectMid.view.Platform;
import ProjectMid.view.HealthPower;

import java.util.ArrayList;

//Imports are omitted
public class GameLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public GameLoop(Platform platform) {
        this.platform = platform;
        //frameRate = 60;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void update(ArrayList<Character> characterList) {
        for (Character character : characterList ) {
            if (platform.getKeys().isPressed(character.getLeftKey())) {
                if(character.getSelectPlayer() == 'A'){
                    character.setScaleX(-1);
                }
                character.moveLeft();
            }

            if (platform.getKeys().isPressed(character.getRightKey())) {
                if(character.getSelectPlayer() == 'A'){
                    character.setScaleX(1);
                }
                character.moveRight();
            }

            if (!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey())) {
                character.stop();
            }

            if (platform.getKeys().isPressed(character.getUpKey())) {
                character.jump();
            }
        }
    }

    private void updateScore(ArrayList<HealthPower> healthPowerList, ArrayList<Character>
            characterList) {
        javafx.application.Platform.runLater(() -> {
            for (int i = 0; i< healthPowerList.size() ; i++) {
                healthPowerList.get(i).setPoint(characterList.get(i).getHp());
            }
        });
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(platform.getCharacterList());
            updateScore(platform.getScoreList(),platform.getCharacterList());
            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
