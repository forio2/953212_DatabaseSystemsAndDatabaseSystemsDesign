package ProjectMid.Item;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
/*1.4.5*/
public class BasedCharacter implements Serializable{
    public static final DataFormat DATA_FORMAT = new DataFormat("src.model.Item. BasedEquipment");
    protected String name;
    protected String imgpath;
    protected String url;

    public String getName() {
        return name;
    }

    public String getImagepath() {
        return imgpath;
    }

    public String getUrl() {
        return url;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}