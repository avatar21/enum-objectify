# enum-objectify
Java Enum bean object conversion Utilities

1. method#1 - use the generic DescriptiveEnumObject as a receiver, then use EnumObjectUtils to do the conversion
```java
class Test {
    public static void main(String[] args){
        DescriptiveEnumObject<SexEnum, Character> sexEnum = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, SexEnum.FEMALE);
        System.out.println(String.format("sex enum = %s", gson.toJson(sexEnum)));
    }
}
```
2. method#2 - inherit DescriptiveEnumObject and convert directly from EnumObjectUtils
```java
// === enum class ... ===
public enum SexEnum implements IDescriptiveEnum<Character> {
    MALE('M', "male"), FEMALE('F', "FEMALE");
    
    SexEnum(char code, String description) {
        // implement enum ...
    }
}

// === enum object class ===
class SexEnumObject extends DescriptiveEnumObject<SexEnum, Character> {
    // implement constructor ...
    public SexEnumObject(SexEnum enumInstance) {
        super(enumInstance);
    }
}

// === usage ===
public class TestMain {
    public static void main(String[] args) {
        Gson gson = new Gson();
        SexEnumObject mSexObj = EnumObjectUtils.asDescriptiveEnumObject(SexEnumObject.class, SexEnum.MALE);
        System.out.println("sex = "+gson.toJson(mSexOb));
     }
}
```
3. Get enum by specifying code
```java
// === usage ===
public class TestMain {
    public static void main(String[] args) {
        SexEnum mSex = EnumObjectUtils.getByCode(SexEnum.class, "M"); 
    }
}
```
4. List all possible values to EnumObject List
```java
// === usage ===
public class TestMain {
    public static void main(String[] args) {
        List<SexEnumObject> sexEnumObjList = EnumObjectUtils.asDescriptiveEnumObjectList(SexEnum.class, SexEnumObject.class);
                System.out.println(String.format("SexEnum.values() as obj list = %s", gson.toJson(sexEnumObjList)));
    }
}
```
5. List all possible values as EnumObject Map
```java
// === usage ===
public class TestMain {
    public static void main(String[] args) {
        Map<Character, SexEnumObject> sexEnumObjMap = EnumObjectUtils.asDescriptiveEnumObjectMap(SexEnum.class, SexEnumObject.class);
        System.out.println(String.format("SexEnum.values() as obj map = %s", gson.toJson(sexEnumObjMap))); 
    }
}
```
