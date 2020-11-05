package Chapter1.model.Character;
//1.4.7.2
import Chapter1.model.DamageType;
public class BattleMageCharacter extends BasedCharacter{
    public BattleMageCharacter(String name, String imgpath, int basedDef, int basedRes, int basedSPD){
        this.name = name;
        this.type = DamageType.magical;
        this.imgpath = imgpath;
        this.fullHp = 40;
        this.basedPow = 40;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.basedSPD = basedSPD;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}
