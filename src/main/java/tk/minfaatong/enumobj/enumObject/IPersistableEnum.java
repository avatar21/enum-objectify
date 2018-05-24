package tk.minfaatong.enumobj.enumObject;

/**
 * <p>persistable enum type (used by JPA pojo annotation for instance), must implement IDescriptive</p>
 * <p>usage:
 * <code>
 *     public enum SexEnum implements IDescriptiveEnum<Character>, IPersistableEnum<SexEnum, Character> {
 *         // implement IDescriptiveEnum's methods ...
 *         @Override
 *          public SexEnum returnEnum(Character persistedValue) {
 *              return EnumObjectUtils.getByCode(SexEnum.class, persistedValue);
 *          }
 *     }
 * </code>
 * </p>
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
