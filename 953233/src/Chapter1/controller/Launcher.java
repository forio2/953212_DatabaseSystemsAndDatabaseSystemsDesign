package Chapter1.controller;

import Chapter1.model.Character.BasedCharacter;
import Chapter1.model.Item.Armor;
import Chapter1.model.Item.BasedEquipment;
import Chapter1.model.Item.Weapon;
import Chapter1.view.CharacterPane;
import Chapter1.view.EquipPane;
import Chapter1.view.InventoryPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/* 1.4.3
public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;
    private static BasedEquipment[] allEquipments = null;
    private static Weapon equippedWeapon = null;
    private static Armor equippedArmor = null;
    private static CharacterPane characterPane = null;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;
 */
//1.4.4-1.4.5
public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;
    private static ArrayList<BasedEquipment> allEquipments = null;
    private static Weapon equippedWeapon = null;
    private static Armor equippedArmor = null;
    private static CharacterPane characterPane = null;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;
    //1.4.6 getAllEquipments, setAllEquipments, getEquippedWeapon, getEquippedArmor
    public static ArrayList<BasedEquipment> getAllEquipments(){
        return allEquipments;
    }

    public static void setAllEquipments(ArrayList<BasedEquipment> allEquipments) {
        Launcher.allEquipments = allEquipments;
    }

    public static BasedEquipment getEquippedWeapon() {
        return equippedWeapon;
    }

    public static BasedEquipment getEquippedArmor() {
        return equippedArmor;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Intro to RPG");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter = GenCharacter.setUpCharacter();
        allEquipments = GenItemList.setUpItemList();
        Pane mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }

    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }
    public static void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon,equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }
    public static BasedCharacter getMainCharacter() {
        return mainCharacter;
    }
    public static void setMainCharacter(BasedCharacter mainCharacter) {
        Launcher.mainCharacter = mainCharacter;
    }
    public static void main(String[] args) {
        launch(args);
    }

    //1.4.5
    public static void setEquippedWeapon(Weapon retrievedEquipment) {
        equippedWeapon = retrievedEquipment;
    }

    public static void setEquippedArmor(Armor retrievedEquipment) {
        equippedArmor = retrievedEquipment;
    }
}