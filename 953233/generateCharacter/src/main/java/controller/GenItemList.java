package controller;

import model.DamageType;
import model.Item.Armor;
import model.Item.BasedEquipment;
import model.Item.Weapon;

import java.util.ArrayList;

public class GenItemList {
    /*1.4.3
    public static BasedEquipment[] setUpItemList() {
        BasedEquipment[] itemLists = new BasedEquipment[5];
        itemLists[0] = new Weapon("Sword",10, DamageType.physical,"Chap1GameInventory/assets/sword1.png");
        itemLists[1] = new Weapon("Gun",20,DamageType.physical,"Chap1GameInventory/assets/gun1.png");
        itemLists[2] = new Weapon("Staff",30,DamageType.magical,"Chap1GameInventory/assets/staff1.png");
        itemLists[3] = new Armor("shirt",0,50,"Chap1GameInventory/assets/shirt1.png");
        itemLists[4] = new Armor("armor",50,0,"Chap1GameInventory/assets/armor1.png");
         */

        public static ArrayList<BasedEquipment> setUpItemList() {
            ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(8);
            itemLists.add(new Weapon("Sword", 10, DamageType.physical, "assets/sword1.png"));
            itemLists.add(new Weapon("Gun", 20, DamageType.physical, "assets/gun1.png"));
            itemLists.add(new Weapon("Staff", 30, DamageType.magical, "assets/staff1.png"));
            //1.4.7.1
            itemLists.add(new Weapon("dango", 2000, DamageType.physical, "assets/dango.png"));
            itemLists.add(new Weapon("hot-dog", 20, DamageType.physical, "assets/hot-dog.png"));
            itemLists.add(new Weapon("sushi", 1000, DamageType.physical, "assets/sushi.png"));
            itemLists.add(new Armor("shirt", 0, 50, "assets/shirt1.png"));
            itemLists.add(new Armor("armor", 50, 0, "assets/armor1.png"));
            return itemLists;
        }
}
