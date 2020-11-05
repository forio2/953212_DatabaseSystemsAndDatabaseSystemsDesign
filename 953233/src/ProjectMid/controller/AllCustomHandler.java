package ProjectMid.controller;

import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;
import ProjectMid.model.Select;
import ProjectMid.view.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class AllCustomHandler {
    public static String bg = "/ProjectMid/assets/era2.png";
    static boolean dragCompleted;
    protected static GenItemList itemLists;

    public static class Unequip implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (Select.getEquippedArmor() != null) {
                Select.setEquippedArmor(null);
                Select.setAllEquipments(GenItemList.setUpIList());
            }
            Select.refreshPane();
        }
    }

    public static class sort implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            ArrayList<BasedCharacter> itemlistArr  = itemLists.setUpIList();
            System.out.println(itemlistArr);
            Object[] arr=  itemlistArr.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).toArray();
            itemlistArr.clear();
            for (int i=0;i<arr.length;i++){
                itemlistArr.add((BasedCharacter) arr[i]);
            }
            System.out.println(itemlistArr);
            Select.setEquippedArmor(null);
            Select.setAllEquipments(itemlistArr);
            Select.refreshPane();
        }
    }
    public static void onDragDetected(MouseEvent event, BasedCharacter equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(BasedCharacter.DATA_FORMAT);
        if (dragboard.hasContent(BasedCharacter.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            event.acceptTransferModes(TransferMode.MOVE);
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedCharacter> allEquipments = Select.getBasedCharacter();
        if (dragboard.hasContent(BasedCharacter.DATA_FORMAT)) {
            BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(BasedCharacter.DATA_FORMAT);
            //ArrayList<BasedCharacter> character = Launcher.getMainCharacter();

            if (retrievedEquipment.getClass().getSimpleName().equals("listCharacter")) {
                if (Select.getEquippedArmor() != null) {
                    allEquipments.add(Select.getEquippedArmor());
                }
                Select.setEquippedArmor((listCharacter) retrievedEquipment);
                setBackground(retrievedEquipment.getUrl());

            }
            Select.setAllEquipments(allEquipments);
            Select.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Select.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }

    public String getBackground(){
        return bg;
    }

    public static void setBackground(String background){
        bg = background;
    }

    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedCharacter> allEquipments = Select.getBasedCharacter();
        BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(
                BasedCharacter.DATA_FORMAT);
        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Select.setAllEquipments(allEquipments);
        Select.refreshPane();
    }
}
