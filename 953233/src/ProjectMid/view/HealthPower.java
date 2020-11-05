package ProjectMid.view;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HealthPower extends Pane {
    Label point;
    public HealthPower(int x, int y) {
        point = new Label("0");
        setTranslateX(x);
        setTranslateY(y);
        point.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        point.setTextFill(Color.web("#FFF"));
        getChildren().addAll(point);
    }



    public void setPoint(int hp) {
        this.point.setText(Integer.toString(hp));
    }
}