package org.ixkit.anvilj.core;

import org.ixkit.land.lang.Argument;

import javax.persistence.Transient;
import java.util.Map;

public abstract  class XObject {
    @Transient
    private Argument bucket ;
    public Object get(Object key){
        if (bucket == null){
            return null;
        }
        return bucket.get(key);
    }

    public XObject put(Object key, Object value){
        if (null == bucket){
            bucket = Argument.of();
        }
        bucket.put(key,value);
        return this;
    }
    public XObject putAll(Map map){
        if (null == bucket){
            bucket = Argument.of();
        }
        bucket.putAll(map);
        return this;
    }
}
