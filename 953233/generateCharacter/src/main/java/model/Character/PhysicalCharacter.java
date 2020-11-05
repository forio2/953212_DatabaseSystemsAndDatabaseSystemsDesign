package model.Character;
import model.DamageType;

public class PhysicalCharacter extends BasedCharacter {
    public PhysicalCharacter(String name, String imgpath, int basedDef, int basedRes, int basedSPD){
        this.name = name;
        this.type = DamageType.physical;
        this.imgpath = imgpath;
        this.fullHp = 50;
        this.basedPow = 30;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.basedSPD = basedSPD;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}