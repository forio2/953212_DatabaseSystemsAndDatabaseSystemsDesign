package Chapter1.model.Character;

import Chapter1.model.DamageType;
import Chapter1.model.Item.Armor;
import Chapter1.model.Item.Weapon;

public class BasedCharacter{
    protected String name, imgpath;
    protected DamageType type;
    //1.4.7.3 add SPD
    protected Integer fullHp, basedDef, basedRes, basedPow, basedSPD;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor armor;
    public String getName () {
        return name;
    }
    public String getImagepath() {
        return imgpath;
    }
    public Integer getHp() {
        return hp;
    }
    public Integer getFullHp() {

        return fullHp;
    }
    public Integer getPower() {

        return power;
    }
    public Integer getDefense() {

        return defense;
    }
    public Integer getResistance() {
        return resistance;
    }
    public DamageType getType() {
        return  type;
    }
    //1.4.7.3
    public Integer getSPD() {
        return basedSPD;
    }

    //1.4.6
    public void equipWeapon( Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }

    public void equipArmor( Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }

    public void unequipWeapon() {
        this.power = basedPow;
    }

    public void unequipArmor() {
        this.defense = this.basedDef;
        this.resistance = this.basedRes;
    }

    @Override
    public String toString() {
        return name;
    }
}
