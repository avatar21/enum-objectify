package tk.minfaatong.enumobj.configKey.subKey;

import tk.minfaatong.enumobj.configKey.IConfigSubKey;
import tk.minfaatong.enumobj.configKey.IPersistableConfigSubKeyEnum;
import tk.minfaatong.enumobj.utils.EnumObjectUtils;

/**
 * <p>Module Permission Config</p>
 *
 * @author avatar21
 * @version 1.0
 * @since 2017-10-16 Ver 1.0
 */
public enum PerConfigSubKey implements IConfigSubKey<String>,
    IPersistableConfigSubKeyEnum<PerConfigSubKey, String> {
    PER_ALLOW_DUMMY_ACTION("allow.dummyAction","Allow dummy action"),;

    private String code;
    private String description;

    PerConfigSubKey(String code, String description) {
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
    public PerConfigSubKey returnEnum(String persistedValue) {
        return EnumObjectUtils.getConfigSubKeyByCode(PerConfigSubKey.class, persistedValue);
    }
}
