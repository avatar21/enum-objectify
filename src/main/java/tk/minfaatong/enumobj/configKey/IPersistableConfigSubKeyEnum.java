package tk.minfaatong.enumobj.configKey;

/**
 * <p>persistable enum type (used by JPA pojo annotation for instance), must implement IDescriptive</p>
 * <p>usage:
 * <pre>
 *     public enum SexEnum implements IDescriptiveEnum<Character>, IPersistableDescriptiveEnum<SexEnum, Character> {
 *         // implement IDescriptiveEnum's methods ...
 *         @Override
 *          public SexEnum returnEnum(Character persistedValue) {
 *              return EnumObjectUtils.getByCode(SexEnum.class, persistedValue);
 *          }
 *     }
 * </pre>
 * </p>
 *
 * @param <E> enum type
 * @param <K> code type of enum
 */
public interface IPersistableConfigSubKeyEnum<E extends Enum & IConfigSubKey, K> {
    /**
     * <p>persistable enum type</p>
     *
     * @param persistedValue database field value
     * @return enum type E
     */
    E returnEnum(K persistedValue);
}
