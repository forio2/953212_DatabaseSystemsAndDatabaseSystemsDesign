package ProjectMid.model;

import ProjectMid.view.EquipPane;
import ProjectMid.view.InventoryPane;
import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;
import ProjectMid.controller.GenItemList;
import ProjectMid.view.Platform;
import ProjectMid.view.TopPane;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Select extends SubScene {
    private static Stage primaryStage = new Stage();
    private static BasedCharacter mainCharacter = null;
    private static Scene mainScene;
    private static ArrayList<BasedCharacter> allEquipments = null;
    private static listCharacter equipmentArmor = null;
    private static TopPane topPane = null;
    private static Pane mainPane;
    private static Popup popup;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;
    public Select(){
        super(new AnchorPane(), 1000, 0);
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        allEquipments = GenItemList.setUpIList();
        mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        mainPane.setLeft(equipPane);
        mainPane.setCenter(inventoryPane);
        refreshPane();
        return mainPane;
    }

    public static void refreshPane() {
        equipPane.drawPane(equipmentArmor);
        inventoryPane.drawPane(allEquipments);
    }


    public static void setEquippedArmor(listCharacter retrievedEquipment) {
        equipmentArmor = retrievedEquipment;
    }

    public static void setAllEquipments(ArrayList<BasedCharacter> allEquipments) {
        Select.allEquipments = allEquipments;
    }

    public static ArrayList<BasedCharacter> getBasedCharacter() {
        return allEquipments;
    }

    public static BasedCharacter getEquippedArmor() {
        return equipmentArmor;
    }

}