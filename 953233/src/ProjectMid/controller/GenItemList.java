package ProjectMid.controller;


import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GenItemList {
    public static ArrayList<BasedCharacter> setUpIList() {
        ArrayList<BasedCharacter> itemLists = new ArrayList<BasedCharacter>();
        itemLists.add(new listCharacter("Day", "/ProjectMid/assets/Background.png",  "ProjectMid/assets/super-mario.png"));
        itemLists.add(new listCharacter("Era2", "/ProjectMid/assets/era.png",  "ProjectMid/assets/forest.png"));
        itemLists.add(new listCharacter("Era", "/ProjectMid/assets/era2.png",  "ProjectMid/assets/sun.png"));

        return itemLists;
    }

}
