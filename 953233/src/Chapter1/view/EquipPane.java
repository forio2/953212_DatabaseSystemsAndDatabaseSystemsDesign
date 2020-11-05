package Chapter1.view;


import Chapter1.controller.AllCustomHandler;
import Chapter1.controller.Launcher;
import Chapter1.model.Item.Armor;
import Chapter1.model.Item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static Chapter1.controller.AllCustomHandler.onDragDropped;
import static Chapter1.controller.AllCustomHandler.onDragOver;

public class EquipPane extends ScrollPane {
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    public EquipPane() { }
    /*1.4.3-1.4.4
    private Pane getDetailsPane() {
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(25, 25, 25, 25));
        Label weaponLbl,armorLbl;
        ImageView weaponImg = new ImageView();
        ImageView armorImg = new ImageView();
        if (equippedWeapon != null) {
            weaponLbl = new Label("Weapon:\n"+equippedWeapon.getName());
            weaponImg.setImage(new Image(getClass().getClassLoader().getResource(equippedWeapon.getImagepath()).toString()));
        }
        else{
            weaponLbl = new Label("Weapon:");
            weaponImg.setImage(new Image(getClass().getClassLoader().getResource("Chap1GameInventory/assets/blank.png").toString()));
        }
        if (equippedArmor != null) {
            armorLbl = new Label("Armor: \n"+equippedArmor.getName());
            armorImg.setImage(new Image(getClass().getClassLoader().getResource(equippedArmor.getImagepath()).toString()));
        }
        else {
            armorLbl = new Label("Armor:");
            armorImg.setImage(new Image(getClass().getClassLoader().getResource("Chap1GameInventory/assets/blank.png").toString()));
        }
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImg, armorLbl, armorImg);
        return equipmentInfoPane;
    }
     */
    private Pane getDetailsPane() {
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(25, 25, 25, 25));
        Label weaponLbl, armorLbl;
        StackPane weaponImgGroup = new StackPane();
        StackPane armorImgGroup = new StackPane();
        ImageView bg1 = new ImageView();
        ImageView bg2 = new ImageView();
        ImageView weaponImg = new ImageView();
        ImageView armorImg = new ImageView();
        bg1.setImage(new Image(getClass().getClassLoader().getResource("Chapter1/assets/blank.png").toString()));
        bg2.setImage(new Image(getClass().getClassLoader().getResource("Chapter1/assets/blank.png").toString()));
        weaponImgGroup.getChildren().add(bg1);
        armorImgGroup.getChildren().add(bg2);
            //name weapon
        if (equippedWeapon != null) {
        weaponLbl = new Label("Weapon: \n" + equippedWeapon.getName());
        weaponImg.setImage(new Image(getClass().getClassLoader().getResource(equippedWeapon.getImagepath()).toString()));
        weaponImgGroup.getChildren().add(weaponImg);
        }
        else {
        weaponLbl = new Label("Weapon:");
        weaponImg.setImage(new Image(getClass().getClassLoader().getResource("Chapter1/assets/blank.png").toString()));
        }
            //name armor
        if (equippedArmor != null) {
        armorLbl = new Label("Armor: \n" + equippedArmor.getName());
        armorImg.setImage(new Image(getClass().getClassLoader().getResource(equippedArmor.getImagepath()).toString()));
        armorImgGroup.getChildren().add(armorImg);
        }
        else {
        armorLbl = new Label("Armor:");
        armorImg.setImage(new Image(getClass().getClassLoader().getResource("Chapter1/assets/blank.png").toString()));
        }

        //onDragOver Weapon
        weaponImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) { onDragOver(e,"Weapon"); }
        });
        //onDragOver Armor
        armorImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                onDragOver(e, "Armor");
            }
        });
        weaponImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                onDragDropped(e, weaponLbl, weaponImgGroup);
            }
        });
        armorImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {
                onDragDropped(e, armorLbl, armorImgGroup);
            }
        });
        //1.4.7.5
        Button unEquip = new Button();
        unEquip.setText("Unequip");
        unEquip.setOnAction(new AllCustomHandler.UnEquip());
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImgGroup, armorLbl, armorImgGroup, unEquip);
        return equipmentInfoPane;
    }

    public void drawPane(Weapon equippedWeapon, Armor equippedArmor) {
        this.equippedWeapon = equippedWeapon;
        this.equippedArmor = equippedArmor;
        Pane equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(equipmentInfo);
    }
}
