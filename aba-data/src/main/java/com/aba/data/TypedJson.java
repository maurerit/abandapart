package com.aba.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A simple json super type which will insert a javaType attribute into the json string so
 * deserialization is easier and can be more dynamic on the receiving end.
 * 
 *	@author Marq Aideron <marq.aideron@gmail.com>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property="javaType")
public abstract class TypedJson {

}
