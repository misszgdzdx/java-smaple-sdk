package com.poor.chef;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Test {

    public static void main(String[] args) {
        CommonRet<Object> ret = CommonRet.Builder.success();
        System.out.println(JSONObject.toJSONString(ret, SerializerFeature.WRITE_MAP_NULL_FEATURES));

        CommonRet<Object> ret2 = CommonRet.REP().initCode(1111).initMsg("1111");
        System.out.println(JSONObject.toJSONString(ret2, SerializerFeature.WRITE_MAP_NULL_FEATURES));
    }
}
