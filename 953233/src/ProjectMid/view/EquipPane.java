package ProjectMid.view;


import ProjectMid.controller.AllCustomHandler;
import ProjectMid.Item.listCharacter;
import ProjectMid.controller.GameLoop;
import ProjectMid.controller.Launcher;
import ProjectMid.model.DrawingLoop;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static ProjectMid.controller.AllCustomHandler.onDragDropped;
import static ProjectMid.controller.AllCustomHandler.onDragOver;

public class EquipPane extends ScrollPane {
    private listCharacter equippedlistCharacter;

    public EquipPane() { }

    public void drawPane( listCharacter equippedlistCharacter) {
        this.equippedlistCharacter = equippedlistCharacter;
        Pane equipmentInfo = getDetailsPane();
        this.setContent(equipmentInfo);
    }

    private Pane getDetailsPane() {
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(50, 50, 50, 50));
        Label armorLbl;

        StackPane armorImgGroup = new StackPane();

        ImageView bg2 = new ImageView();

        ImageView armorImg = new ImageView();

        bg2.setImage(new Image(getClass().getClassLoader().getResource("ProjectMid/assets/blank.png").toString()));

        armorImgGroup.getChildren().add(bg2);


        if (equippedlistCharacter != null) {
            armorLbl = new Label("Background: \n" + equippedlistCharacter.getName());
            armorImg.setImage(new Image(getClass().getClassLoader().getResource(equippedlistCharacter.getImagepath()).toString()));
            armorImgGroup.getChildren().add(armorImg);
        } else {
            armorLbl = new Label("Background:");
            armorImg.setImage(new Image(getClass().getClassLoader().getResource("ProjectMid/assets/blank.png").toString()));
        }
        armorImgGroup.setOnDragOver(e -> onDragOver(e, "listCharacter"));
        armorImgGroup.setOnDragDropped(e -> onDragDropped(e, armorLbl, armorImgGroup));

        Button unequip = new Button();
        unequip.setText("unequip");
        unequip.setOnAction(new AllCustomHandler.Unequip());

        Button go = new Button();
        go.setText("GO");
        go.setOnAction(event -> {
            try {
                Platform platform = new Platform();
                GameLoop gameLoop = new GameLoop(platform);
                DrawingLoop drawingLoop = new DrawingLoop(platform);
                Launcher.getStage().setScene(new Scene(platform, platform.WIDTH, platform.HEIGHT));
                Launcher.getStage().getScene().setOnKeyPressed(ev-> platform.getKeys().add(ev.getCode()));
                Launcher.getStage().getScene().setOnKeyReleased(ev -> platform.getKeys().remove(ev.getCode()));

                Launcher.getStage().setTitle("platformer");
                Launcher.getStage().setScene(Launcher.getStage().getScene());
                Launcher.getStage().show();
                (new Thread(gameLoop)).start();
                (new Thread(drawingLoop)).start();
            }catch (NullPointerException|IllegalArgumentException e){
                e.printStackTrace();
            }
        });

        Button sort = new Button();
        sort.setText("SORT");
        sort.setOnAction(new AllCustomHandler.sort());
        equipmentInfoPane.getChildren().addAll(armorLbl, armorImgGroup,unequip, go, sort);
        return equipmentInfoPane;
    }


}
