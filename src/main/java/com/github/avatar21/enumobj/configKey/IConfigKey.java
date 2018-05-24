package com.github.avatar21.enumobj.configKey;

/**
 * <p>configuration key enum objectify interface</p>
 *
 * @author avatar21
 * @version 1.0
 * @since 2017-08-17 Ver 1.0
 */
public interface IConfigKey {

    String getCode();

    String getDescription();

    <E extends Enum & IConfigSubKey> Class<E> getSubClass();
}
