package org.ixkit.anvilj.website.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.ixkit.land.lang.Argument;

public class Context extends Argument {

    public String toString(){
        String result = JSON.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
        return result;
    }
}
