package org.ixkit.anvilj.core;

import org.ixkit.land.lang.Argument;

import javax.persistence.Transient;

public abstract  class XObject {
    @Transient
    private Argument _bucket ;
    public Object get(Object key){
        if (_bucket == null){
            return null;
        }
        return _bucket.get(key);
    }

    public XObject put(Object key, Object value){
        if (null == _bucket){
            _bucket = Argument.of();
        }
        _bucket.put(key,value);
        return this;
    }
}
