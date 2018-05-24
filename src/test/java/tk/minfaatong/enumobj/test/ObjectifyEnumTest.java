package tk.minfaatong.enumobj.test;

import com.google.gson.Gson;
import org.junit.Test;
import tk.minfaatong.enumobj.enumObject.DescriptiveEnumObject;
import tk.minfaatong.enumobj.constants.ModifyStatus;
import tk.minfaatong.enumobj.constants.SexEnum;
import tk.minfaatong.enumobj.utils.EnumObjectUtils;

import java.util.List;
import java.util.Map;

public class ObjectifyEnumTest {
    @Test
    public void test() {
        Gson gson = new Gson();
        System.out.println(String.format("ModifyStatus[0] = %s", EnumObjectUtils.getByCode(ModifyStatus.class, "0")));

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
