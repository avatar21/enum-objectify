package tk.minfaatong.enumobj.constants;

import tk.minfaatong.enumobj.enumObject.IDescriptiveEnum;
import tk.minfaatong.enumobj.enumObject.IPersistableEnum;
import tk.minfaatong.enumobj.utils.EnumObjectUtils;

/**
 * modified status enum
 *
 * @author avatar21
 * @since 2017-12-25
 */
public enum ModifyStatus implements IDescriptiveEnum<String>, IPersistableEnum<ModifyStatus, String> {
    NOT_MODIFIED("0", "not modified"), IS_MODIFIED("1", "modified");

    private String code;
    private String description;

    ModifyStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ModifyStatus returnEnum(String persistedValue) {
        return EnumObjectUtils.getByCode(ModifyStatus.class, persistedValue);
    }
}
