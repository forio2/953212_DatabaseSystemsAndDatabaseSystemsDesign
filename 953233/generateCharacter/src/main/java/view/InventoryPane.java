package view;

import model.Item.BasedEquipment;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static controller.AllCustomHandler.onDragDetected;
import static controller.AllCustomHandler.onEquipDone;

public class InventoryPane extends ScrollPane{
    /*1.4.3
    private BasedEquipment[] equipmentArray;
    public InventoryPane(){}
    private Pane getDetailsPane(){
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));
        if (equipmentArray!=null){
            ImageView[] imageViewsList = new ImageView[equipmentArray.length];
            for (int i=0; i< equipmentArray.length;i++){
                imageViewsList[i] = new ImageView();
                imageViewsList[i].setImage(new Image(getClass().getClassLoader().
                        getResource(equipmentArray[i].getImagepath()).toString()));
            }
            inventoryInfoPane.getChildren().addAll(imageViewsList);
        }
        return inventoryInfoPane;
    }
    public void drawPane(BasedEquipment[] equipmentArray){
        this.equipmentArray = equipmentArray;
        Pane inventoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(inventoryInfo);
        */
    private ArrayList<BasedEquipment> equipmentArray;
/*1.4.3-1.4.4
    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));
        if (equipmentArray != null) {
            ImageView[] imageViewList = new ImageView[equipmentArray.size()];
            for (int i = 0; i < equipmentArray.size(); i++) {
                imageViewList[i] = new ImageView();
                imageViewList[i].setImage(new Image(getClass().getClassLoader().
                        getResource(equipmentArray.get(i).getImagepath()).toString()));
            }
            inventoryInfoPane.getChildren().addAll(imageViewList);
        }
        return inventoryInfoPane;
    }
 */
    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));
        if (equipmentArray != null) {
            ImageView[] imageViewList = new ImageView[equipmentArray.size()];
            for (int i = 0; i < equipmentArray.size(); i++) {
                imageViewList[i] = new ImageView();
                imageViewList[i].setImage(new Image(getClass().getClassLoader().
                        getResource(equipmentArray.get(i).getImagepath()).toString()));

                int finalI = i;
                imageViewList[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        onDragDetected(event, equipmentArray.get(finalI), imageViewList[finalI]);
                    }
                });
                //1.4.6
                imageViewList[i].setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        onEquipDone(event);
                    }
                });
            }
            inventoryInfoPane.getChildren().addAll(imageViewList);
        }
        return inventoryInfoPane;
    }

    public void drawPane(ArrayList<BasedEquipment> equipmentArray) {
        this.equipmentArray = equipmentArray;
        Pane inventoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(inventoryInfo);
    }
}



