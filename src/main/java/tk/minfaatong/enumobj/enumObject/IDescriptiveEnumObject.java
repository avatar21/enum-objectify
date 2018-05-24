package tk.minfaatong.enumobj.enumObject;

/**
 * <p>POJO representative of {@link IDescriptiveEnum} enum</p>
 *
 * @param <K> code type of enum
 * @param <E>
 */
public interface IDescriptiveEnumObject<E extends Enum & IDescriptiveEnum<K>, K> {

    E getEnumInstance();

    void setEnumInstance(E enumInstance);

    K getCode();

    String getDescription();
}
