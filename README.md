# enum-objectify
Java Enum bean object conversion Utilities

```java
DescriptiveEnumObject<SexEnum, Character> sexEnum = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, SexEnum.FEMALE);
System.out.println(String.format("sex enum = %s", gson.toJson(sexEnum)));
```
