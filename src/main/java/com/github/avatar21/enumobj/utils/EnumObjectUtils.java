package com.github.avatar21.enumobj.utils;

import com.github.avatar21.enumobj.configKey.ConfigKey;
import com.github.avatar21.enumobj.configKey.IConfigSubKey;
import com.github.avatar21.enumobj.enumObject.AbstractDescriptiveEnumObject;
import com.github.avatar21.enumobj.enumObject.DescriptiveEnumObject;
import com.github.avatar21.enumobj.enumObject.IDescriptiveEnum;
import com.github.avatar21.generics.utils.GenericBeanUtils;
import com.googlecode.gentyref.GenericTypeReflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * <p>Utilities for descriptive enum, such as:</p>
 * <ul>
 *     <li>Turn descriptive enum into POJO Object</li>
 *     <li>get enum by code</li>
 * </ul>
 *
 */
public class EnumObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(
        EnumObjectUtils.class);
    /**
     * <p>get enum by code</p>
     * usage:
     * <pre>
     * <code>
     *     // === enum class ... ===
     *     public enum SampleEnum implements IDescriptiveEnum{@literal <}String{@literal >} {
     *          // implement enum ...
     *     }
     *     // === usage ===
     *     SampleEnum enumIns = EnumObjectUtils.getByCode(SampleEnum.class, "0");
     * </code>
     * </pre>
     *
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param code &lt;K&gt; code type
     * @return &lt;E&gt;
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>> E getByCode(Class<E> enumClass, K code) {
        E instance = null;
        if (code != null) {
            for (E value : asEnumArray(enumClass)) {
                if (value.getCode().equals(code)) {
                    instance = value;
                    break;
                }
            }
        }
        return instance;
    }

    /**
     * <p>转换字符串成指定enum类(泛类enum)</p>
     *
     * @param enumType {@link Class} 想要转换的enum类
     * @param enumName 该enum类toString()的值
     * @param <T> 泛类继承enum
     * @return 返回成功转换的enum类实例
     */
    public static <T extends Enum<T>> T stringToEnum(Class<T> enumType, String enumName) {
        T foundEnum = null;
        if (!StringUtils.isEmpty(enumName)) {
            for (T mEnum : enumType.getEnumConstants()) {
                if (mEnum.toString().equals(enumName)) {
                    foundEnum = mEnum;
                    break;
                }
            }
        }
        return foundEnum;
    }

    /**
     * <p>convert enum into POJO object</p>
     * usage:
     * <pre>
     * <code>
     * // === enum class ... ===
     * public enum SexEnum implements IDescriptiveEnum{@literal <}Character{@literal >} {
     *     MALE('M', "male"), FEMALE('F', "FEMALE");
     *
     *     SexEnum(char code, String description) {
     *         // implement enum ...
     *     }
     * }
     *
     * // === enum object class ===
     * class SexEnumObject extends DescriptiveEnumObject{@literal <}SexEnum, Character{@literal >} {
     *     // implement constructor ...
     *     public SexEnumObject(SexEnum enumInstance) {
     *         super(enumInstance);
     *     }
     * }
     *
     * // === usage ===
     * public class TestMain {
     *     public static void main(String[] args) {
     *          Gson gson = new Gson();
     *          SexEnumObject mSexObj = EnumObjectUtils.asDescriptiveEnumObject(SexEnumObject.class, SexEnum.MALE);
     *          System.out.println("sex = "+gson.toJson(mSexOb));
     *      }
     * }
     * </code>
     * </pre>
     *
     * @param enumObjectClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param enumInstance enum instance
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @param <T> a child class of AbstractDescriptiveEnumObject class, as a container bean for accepting the result of enum conversion
     * @return {@link AbstractDescriptiveEnumObject} pojo instance representing enum
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>, T extends AbstractDescriptiveEnumObject<E, K>> T asDescriptiveEnumObject(
        Class<T> enumObjectClass, E enumInstance) {
        T enumObject = null;
        try {
            if (enumObjectClass.equals(DescriptiveEnumObject.class)) {
                enumObject = (T) new DescriptiveEnumObject(enumInstance);
            } else {
                Constructor<T> mArgsConstructor = null;

                try {
                    mArgsConstructor = enumObjectClass.getDeclaredConstructor(enumInstance.getClass());
                } catch (Exception e1) {
                }

                if (mArgsConstructor != null) {
                    enumObject = mArgsConstructor.newInstance(enumInstance);
                } else {
                    enumObject = enumObjectClass.getDeclaredConstructor().newInstance();
                    enumObject.setEnumInstance(enumInstance);
                }

            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e.getLocalizedMessage());
        }
        return enumObject;
    }

    /**
     * <p>retrieve all enum values as array(泛类enum)</p>
     *
     * @param enumClass generic enum class
     * @param <E> generic enum type
     * @return possible values array of enum E
     */
    public static <E extends Enum> E[] asEnumArray(Class<E> enumClass) {
        return enumClass.getEnumConstants();
    }

    /**
     * <p>retrieve all enum values as list</p>
     *
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @return possible values {@link List} of enum E
     */
    public static <E extends Enum & IDescriptiveEnum> List<E> asDescriptiveEnumList(Class<E> enumClass) {
        return Arrays.asList(enumClass.getEnumConstants());
    }

    /**
     * <p>retrieve all enum values as code-&gt;enum {@link HashMap}</p>
     *
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @return possible values {@link Map} of code K-&gt;enum E
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>> Map<K, E> asDescriptiveEnumMap(Class<E> enumClass) {
        Map<K, E> enumMap = new HashMap<>();
        for (E value : asEnumArray(enumClass)) {
            enumMap.put(value.getCode(), value);
        }
        return enumMap;
    }

    /**
     * <p>retrieve all enum values as pojo array</p>
     *
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param enumObjectClass enum object class, inherits {@link AbstractDescriptiveEnumObject}
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @param <T> enum object, subclass of {@link AbstractDescriptiveEnumObject}
     * @return possible values array of pojo {@link AbstractDescriptiveEnumObject} representing E
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>, T extends AbstractDescriptiveEnumObject<E, K>> T[] asDescriptiveEnumObjectArray(Class<E> enumClass, Class<T> enumObjectClass) {
        return (T[]) asDescriptiveEnumObjectList(enumClass, enumObjectClass).toArray();
    }

    /**
     * <p>retrieve all enum values as pojo list</p>
     *
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param enumObjectClass enum object class, inherits {@link AbstractDescriptiveEnumObject}
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @param <T> enum object, subclass of {@link AbstractDescriptiveEnumObject}
     * @return possible values {@link List} of pojo {@link AbstractDescriptiveEnumObject} representing E
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>, T extends AbstractDescriptiveEnumObject<E, K>> List<T> asDescriptiveEnumObjectList(Class<E> enumClass, Class<T> enumObjectClass) {
        List<T> objList = new ArrayList<>();
        for (E value: Arrays.asList(enumClass.getEnumConstants())) {
            objList.add(asDescriptiveEnumObject(enumObjectClass, value));
        }
        return objList;
    }

    /**
     * <p>retrieve all enum values as code-&gt;pojo map</p>
     *
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @param <T> enum object, subclass of {@link AbstractDescriptiveEnumObject}
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param enumObjectClass enum object class, inherits {@link AbstractDescriptiveEnumObject}
     * @return possible values {@link Map} of code K-&gt;pojo {@link AbstractDescriptiveEnumObject} representing E
     */
    public static <K, E extends Enum & IDescriptiveEnum<K>, T extends AbstractDescriptiveEnumObject<E, K>> Map<K, T> asDescriptiveEnumObjectMap(Class<E> enumClass, Class<T> enumObjectClass) {
        Map<K, T> objMap = new HashMap<>();
        List<T> objList = asDescriptiveEnumObjectList(enumClass, enumObjectClass);
        for (T value : objList) {
            objMap.put(value.getCode(), value);
        }
        return objMap;
    }

    /**
     * <p>get enum by code</p>
     * usage:
     * <pre>
     * <code>
     *     // === enum class ... ===
     *     public enum SampleEnum implements IDescriptiveEnum{@literal <}String{@literal >} {
     *          // implement enum ...
     *     }
     *     // === usage ===
     *     SampleEnum enumIns = EnumObjectUtils.getByCode(SampleEnum.class, "0");
     * </code>
     * </pre>
     *
     * @param <K> code type of descriptive enum
     * @param <E> descriptive enum type (implement {@link IConfigSubKey})
     * @param enumClass must be implementing {@link IConfigSubKey}&lt;K&gt; interface
     * @param code &lt;K&gt; code type
     * @return &lt;K&gt;
     */
    public static <K, E extends Enum & IConfigSubKey<K>> E getConfigSubKeyByCode(Class<E> enumClass, K code) {
        E instance = null;
        if (code != null) {
            for (E value : asEnumArray(enumClass)) {
                if (value.getCode().equals(code)) {
                    instance = value;
                    break;
                }
            }
        }
        return instance;
    }


    /**
     * 以编码获取ConfigKey 枚举类
     *
     * @param code 租户配置组编码
     * @return {@link ConfigKey} 枚举类
     */
    public static ConfigKey getConfigKeyByCode(String code) {
        ConfigKey instance = null;
        if (code != null) {
            for (ConfigKey value : ConfigKey.class.getEnumConstants()) {
                if (value.getCode().equals(code)) {
                    instance = value;
                    break;
                }
            }
        }
        return instance;
    }

    /**
     * 获取配置组的“子配置”的枚举类
     *
     * @param <E> (实现 {@link IConfigSubKey}) “子配置”的枚举类
     * @param configKey {@link ConfigKey} 银行专属配置枚举类（组）
     * @return 返回{@link IConfigSubKey} “子配置”的枚举类
     * @see IConfigSubKey
     */
    public static <E extends Enum & IConfigSubKey> Class<E> getSubKeyClassForConfigKey(ConfigKey configKey) {
        return configKey.getSubClass();
    }

    /**
     * <p>retrieve all enum values as array</p>
     *
     * @param enumClass must be implementing {@link IDescriptiveEnum}&lt;K&gt; interface
     * @param <E> descriptive enum type (implement {@link IDescriptiveEnum})
     * @return possible values array of enum E
     */
    public static <E extends Enum & IDescriptiveEnum> E[] asDescriptiveEnumArray(Class<E> enumClass) {
        return enumClass.getEnumConstants();
    }

    /**
     * <p>retrieve all enum values as array</p>
     *
     * @param enumClass must be implementing {@link IConfigSubKey}&lt;K&gt; interface
     * @param <E> configSubKey enum type (implement {@link IConfigSubKey})
     * @return possible values array of enum E
     */
    public static <E extends Enum & IConfigSubKey> E[] asConfigSubKeyEnumArray(Class<E> enumClass) {
        return enumClass.getEnumConstants();
    }

    /**
     * <p>如果知晓IDescriptiveEnum参数类型，可以使用{@link #getByCode(Class, Object)} 方法</p>
     * <p>若不知晓参数类型，可以使用此方法，统一传入String 类</p>
     *
     * @param <K> {@link IDescriptiveEnum} 枚举类的参数“编码”类型
     * @param <T> {@link IDescriptiveEnum} 枚举类
     * @param enumClass 想获得解析的实际枚举类
     * @param code 想获得的编码(字符串)
     * @return 返回已解析成功的枚举
     * @throws ClassNotFoundException enum class is missing
     * @throws ClassCastException failed to cast code to enum
     */
    public static <K, T extends Enum & IDescriptiveEnum<K>> T getByGenericCode(Class<T> enumClass, String code) throws Exception, ClassNotFoundException, ClassCastException {
        Type enumType = enumClass;

        // get the parameterized type, recursively resolving type parameters
        Type paramType = GenericTypeReflector.getExactSuperType(enumType, IDescriptiveEnum.class);
        T instance = null;

        if (paramType instanceof Class<?>) {
            // raw class, type parameters not known
            throw new Exception("未知/ 不支持的IDescriptiveEnum枚举参数类型");
        } else {
            ParameterizedType pBaseType = (ParameterizedType) paramType;
            assert pBaseType.getRawType() == IDescriptiveEnum.class; // always true
            Type typeParamForIDescEnum = pBaseType.getActualTypeArguments()[0];
            logger.debug("parameter type = {}", typeParamForIDescEnum);

            Class<?> clazz = null;
            try {
                clazz = Class.forName(typeParamForIDescEnum.toString().replace("class ", ""));
            } catch (ClassNotFoundException e) {
                throw e;
            }
            K actualCode = (K) GenericBeanUtils.parseStringToJavaType(
                clazz , code);
            instance = EnumObjectUtils
                .getByCode(enumClass, actualCode);
        }
        return instance;
    }
}
