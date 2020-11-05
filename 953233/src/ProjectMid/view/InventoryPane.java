package ProjectMid.view;

import ProjectMid.Item.BasedCharacter;
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

import static ProjectMid.controller.AllCustomHandler.onDragDetected;
import static ProjectMid.controller.AllCustomHandler.onEquipDone;


public class InventoryPane extends ScrollPane {

    public InventoryPane() { }

    private ArrayList<BasedCharacter> equipmentArray;

    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(10, 10, 10, 10));
        if (equipmentArray != null) {
            ImageView[] imageViewList = new ImageView[equipmentArray.size()];
            try {
                for (int i = 0; i < equipmentArray.size(); i++) {
                    imageViewList[i] = new ImageView();
                    imageViewList[i].setImage(new Image(getClass().getClassLoader().
                            getResource(equipmentArray.get(i).getImagepath()).toString()));
                    int finalI = i;
                    imageViewList[i].setOnDragDetected(event -> onDragDetected(event, equipmentArray.get(finalI), imageViewList[finalI]));
                    imageViewList[i].setOnDragDone(event -> onEquipDone(event));
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            inventoryInfoPane.getChildren().addAll(imageViewList);
        }
        return inventoryInfoPane;
    }

    public void drawPane(ArrayList<BasedCharacter> equipmentArray) {
        this.equipmentArray = equipmentArray;
        Pane inventoryInfo = getDetailsPane();
        this.setContent(inventoryInfo);
    }
}