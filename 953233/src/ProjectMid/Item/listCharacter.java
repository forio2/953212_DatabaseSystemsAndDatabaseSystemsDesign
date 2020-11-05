package ProjectMid.Item;

public class listCharacter extends BasedCharacter  {
    public listCharacter(String name, String url, String imgpath) {
        this.name = name;
        this.imgpath = imgpath;
        this.url = url;
    }

    public String getImagepath() {
        return imgpath;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name;
    }
}

