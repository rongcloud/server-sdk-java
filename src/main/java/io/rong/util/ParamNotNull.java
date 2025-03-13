package io.rong.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation used to validate that the transmitted parameter is not null.
 * Created by star on 2017/12/20.
 * @version
 * @author hc
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamNotNull {

}