package tk.minfaatong.enumobj.constants;

import tk.minfaatong.enumobj.enumObject.IDescriptiveEnum;
import tk.minfaatong.enumobj.enumObject.IPersistableEnum;
import tk.minfaatong.enumobj.utils.EnumObjectUtils;

public enum SexEnum implements IDescriptiveEnum<Character>, IPersistableEnum<SexEnum, Character> {
    MALE('M', "male"), FEMALE('F', "FEMALE");

    Character code;
    String description;

    SexEnum(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public void setCode(Character code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Character getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public SexEnum returnEnum(Character persistedValue) {
        return EnumObjectUtils.getByCode(SexEnum.class, persistedValue);
    }
}
