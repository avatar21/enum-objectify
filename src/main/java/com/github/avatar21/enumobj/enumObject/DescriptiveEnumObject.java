package com.github.avatar21.enumobj.enumObject;

/**
 * <p>POJO representative of {@link IDescriptiveEnum} enum</p>
 *
 * @param <K> code type of enum
 */
public class DescriptiveEnumObject<E extends Enum & IDescriptiveEnum<K>, K> extends AbstractDescriptiveEnumObject<E, K> {
    public DescriptiveEnumObject(E enumType) {
        super(enumType);
    }
}
