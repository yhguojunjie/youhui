package com.yoxi.hudongtui.controllers;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将{@link NotBlank}标注在控制器方法的参数上，可以获得在{@link Path}
 * 中的占位符的参数，或者request的请求参数，当这个参数为blank时，请求将中断，抛出对应的出错提示
 * {"S":"Err","R":"test2参数不能为空"}。
 * 
 * 若是 原生数据类型(Primitive DataType)和普通String类型直接使用 "@NotBlank" 注解就可以
 * 若是 String类型，并且是mobile类型，请标明strType为mobile即可如"@NotBlank(strType="mobile")"
 * 若是 String类型，并且是email类型，请标明strType为email即可如"@NotBlank(strType="email")"
 * 若是 String类型，并且是date类型，请标明strType为date,且 pattern也要设计参数接受的日期格式 如"@NotBlank(strType="date",pattern="yyyy-MM-dd HH:mm:ss")"
 * 
 */
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotBlank {
	/**
	 * String类型的日期或者邮箱格式时，对数据校验
	 * 
	 * @return
	 */
	public String pattern() default "";

	/**
	 * 字符串类型 email\date\mobile
	 * 
	 * @return
	 */
	public String strType() default "";
}
