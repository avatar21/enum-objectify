package tk.minfaatong.enumobj.enumObject;

import java.lang.reflect.Type;

/**
 * <p>POJO representative of {@link IDescriptiveEnum} enum</p>
 *
 * @param <K> code type of enum
 * @param <E>
 */
public abstract class AbstractDescriptiveEnumObject<E extends Enum & IDescriptiveEnum<K>, K> implements IDescriptiveEnumObject<E, K> {
    private K code;
    private String description;
    private E enumInstance;

    public AbstractDescriptiveEnumObject() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) { // sanity check, should never happen
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        //Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
    }

    public AbstractDescriptiveEnumObject(E enumInstance) {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) { // sanity check, should never happen
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        this.enumInstance = enumInstance;
        code = enumInstance.getCode();
        description = enumInstance.getDescription();
    }

    @Override
    public E getEnumInstance() {
        return enumInstance;
    }

    @Override
    public void setEnumInstance(E enumInstance) {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) { // sanity check, should never happen
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        this.enumInstance = enumInstance;
        code = enumInstance.getCode();
        description = enumInstance.getDescription();
    }

    @Override
    public K getCode() {
        if (code == null && enumInstance != null) {
            code = enumInstance.getCode();
        }
        return code;
    }

    public String getDescription() {
        if (description == null && enumInstance != null) {
            description = enumInstance.getDescription();
        }
        return description;
    }
}
