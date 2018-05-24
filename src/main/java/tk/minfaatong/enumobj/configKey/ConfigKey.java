package tk.minfaatong.enumobj.configKey;

import tk.minfaatong.enumobj.configKey.subKey.PerConfigSubKey;

/**
 * <p>Configuration Key</p>
 *
 * @author avatar21
 * @version 1.0
 * @since 2017-08-17 Ver 1.0
 */
public enum ConfigKey implements IConfigKey {
    PER("per", "permision", PerConfigSubKey.class);

    private String code;
    private String description;
    private Class subClass;

    <E extends Enum & IConfigSubKey> ConfigKey(String code, String description, Class<E> subClass) {
        this.code = code;
        this.description = description;
        this.subClass = subClass;
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
    public <E extends Enum & IConfigSubKey> Class<E> getSubClass() {
        return subClass;
    }
}
