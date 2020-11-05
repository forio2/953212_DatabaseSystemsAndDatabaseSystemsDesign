package Chapter1.controller;

import Chapter1.model.Character.BasedCharacter;
import Chapter1.model.Character.BattleMageCharacter;
import Chapter1.model.Character.MagicalCharacter;
import Chapter1.model.Character.PhysicalCharacter;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand = new Random();
        /*1.4.3-1.4.6
        int type = rand.nextInt(2) + 1;
         */
        //1.4.7.2
        int type = rand.nextInt(3) + 1;
        int basedDef = rand.nextInt(50) + 1;
        int basedRes = rand.nextInt(50) + 1;
        int basedSPD = rand.nextInt(50) + 1;
        if (type == 1) {
            character = new MagicalCharacter("MagicChar1", "Chapter1/assets/wizard.png", basedDef, basedRes, basedSPD);
        }
        else if(type == 2){
            character = new PhysicalCharacter("PhysicalChar1", "Chapter1/assets/knight.png", basedRes, basedRes, basedSPD);
        }
        else{
            character = new BattleMageCharacter("BattleMageChar1", "Chapter1/assets/ogre.png", basedDef, basedRes, basedSPD);
        }
        return character;
    }
}