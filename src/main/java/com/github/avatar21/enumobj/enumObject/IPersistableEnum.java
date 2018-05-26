package com.github.avatar21.enumobj.enumObject;

/**
 * <p>persistable enum type (used by JPA pojo annotation for instance), must implement IDescriptive</p>
 * usage:
 * <pre><code>
 *     public enum SexEnum implements IDescriptiveEnum{@literal <}Character{@literal >}, IPersistableEnum{@literal <}SexEnum, Character{@literal >} {
 *         // implement IDescriptiveEnum's methods ...
 *         {@literal @}Override
 *          public SexEnum returnEnum(Character persistedValue) {
 *              return EnumObjectUtils.getByCode(SexEnum.class, persistedValue);
 *          }
 *     }
 * </code></pre>
 *
 * @param <E> enum type
 * @param <K> code type of enum
 */
public interface IPersistableEnum<E extends Enum, K> {
    /**
     * <p>persistable enum type</p>
     *
     * @param persistedValue database field value
     * @return enum type E
     */
    E returnEnum(K persistedValue);
}
