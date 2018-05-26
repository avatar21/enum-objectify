package com.github.avatar21.enumobj.bean;

import com.github.avatar21.enumobj.constants.SexEnum;
import com.github.avatar21.enumobj.enumObject.DescriptiveEnumObject;
import com.github.avatar21.enumobj.enumObject.IDescriptiveEnum;

public class SexEnumObject extends DescriptiveEnumObject<SexEnum, Character> {
    public SexEnumObject(SexEnum enumType) {
        super(enumType);
    }
}
