package org.ixkit.land.asset.meta.aop;

public interface Property {
    <P extends Property> P propertyFactory();
}