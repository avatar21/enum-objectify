package com.github.avatar21.enumobj.test;

import com.github.avatar21.enumobj.constants.ModifyStatus;
import com.github.avatar21.enumobj.constants.SexEnum;
import com.github.avatar21.enumobj.enumObject.DescriptiveEnumObject;
import com.github.avatar21.enumobj.utils.EnumObjectUtils;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ObjectifyEnumTest {
    @Test
    public void test() {
        Gson gson = new Gson();
        System.out.println(String.format("ModifyStatus[0] = %s", EnumObjectUtils
            .getByCode(ModifyStatus.class, "0")));

        DescriptiveEnumObject<ModifyStatus, String> modifyStatusEnum = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, ModifyStatus.IS_MODIFIED);
        System.out.println(String.format("ModifyStatus = %s", gson.toJson(modifyStatusEnum)));

        DescriptiveEnumObject<SexEnum, Character> sexEnum = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, SexEnum.FEMALE);
        System.out.println(String.format("SexEnum = %s", gson.toJson(sexEnum)));

        Map<Character, SexEnum> mSexEnumMap = EnumObjectUtils.asDescriptiveEnumMap(SexEnum.class);
        System.out.println(String.format("SexEnum map = %s", gson.toJson(mSexEnumMap)));

        List<DescriptiveEnumObject> mSexEnumList = EnumObjectUtils.asDescriptiveEnumObjectList(SexEnum.class, DescriptiveEnumObject.class);
        System.out.println(String.format("SexEnum list = %s", gson.toJson(mSexEnumList)));
    }
}
