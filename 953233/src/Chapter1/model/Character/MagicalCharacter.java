package Chapter1.model.Character;
import Chapter1.model.DamageType;

public class MagicalCharacter extends BasedCharacter {
    public MagicalCharacter(String name, String imgpath, int basedDef, int basedRes, int basedSPD) {
        this.name = name;
        this.type = DamageType.magical;
        this.imgpath = imgpath;
        this.fullHp = 30;
        this.basedPow = 50;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.basedSPD = basedSPD;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}