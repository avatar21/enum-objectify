package com.github.avatar21.enumobj.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.avatar21.enumobj.bean.SexEnumObject;
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

        String code = "0";
        ModifyStatus modifyEnum = EnumObjectUtils.getByCode(ModifyStatus.class, code);
        System.out.println(String.format("ModifyStatus get by code \"%s\" = %s", code,  modifyEnum));

        DescriptiveEnumObject<ModifyStatus, String> modifyStatusEnumTyObj = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, ModifyStatus.IS_MODIFIED);
        System.out.println(String.format("ModifyStatus as typed object = %s", gson.toJson(modifyStatusEnumTyObj)));

        DescriptiveEnumObject<SexEnum, Character> sexEnum = EnumObjectUtils.asDescriptiveEnumObject(DescriptiveEnumObject.class, SexEnum.FEMALE);
        System.out.println(String.format("SexEnum as typed obj = %s", gson.toJson(sexEnum)));

        SexEnumObject sexEnumTyObj = EnumObjectUtils.asDescriptiveEnumObject(SexEnumObject.class, SexEnum.MALE);
        System.out.println(String.format("SexEnum as obj = %s", gson.toJson(sexEnumTyObj)));

        Map<Character, SexEnum> sexEnumMap = EnumObjectUtils.asDescriptiveEnumMap(SexEnum.class);
        System.out.println(String.format("SexEnum.values() as map = %s", gson.toJson(sexEnumMap)));

        Map<Character, SexEnumObject> sexEnumObjMap = EnumObjectUtils.asDescriptiveEnumObjectMap(SexEnum.class, SexEnumObject.class);
        System.out.println(String.format("SexEnum.values() as obj map = %s", gson.toJson(sexEnumObjMap)));

        List<SexEnumObject> sexEnumObjList = EnumObjectUtils.asDescriptiveEnumObjectList(SexEnum.class, SexEnumObject.class);
        System.out.println(String.format("SexEnum.values() as obj list = %s", gson.toJson(sexEnumObjList)));
    }
}
