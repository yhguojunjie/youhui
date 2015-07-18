package com.yoxi.hudongtui.controllers;

import java.util.Date;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import com.yoxi.hudongtui.constants.ErrorEnum;
import com.yoxi.hudongtui.utils.common.ValidateUtils;



/**
 * 参数不为空校验器
 * 
 * 
 */
public class NotBlankParamValidator implements ParamValidator {

	@Override
	public boolean supports(ParamMetaData metaData){
		return metaData.getAnnotation(NotBlank.class) != null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object validate(ParamMetaData metaData, Invocation inv, Object target, Errors errors){
		NotBlank notBlank = metaData.getAnnotation(NotBlank.class);
		Class paramTypeClass = metaData.getParamType();
		String paramName = metaData.getParamName();
		String baseResult = "@json:{request:" + inv.getRequest().getRequestURI() + ",error_code:";
		if (String.class.equals(paramTypeClass)) {
			if (StringUtils.isBlank((String) target)) {
				return baseResult + ErrorEnum.PARAM_IS_NULL_ERROR.getCode() + ",error:\"" + paramName + " "
								+ ErrorEnum.PARAM_IS_NULL_ERROR.getDesc() + "\"}";
			} else {
				boolean strTypeFlag = StringUtils.isNotBlank(notBlank.strType());
				if (strTypeFlag) {
					if (StringUtils.equalsIgnoreCase(notBlank.strType(), "date")) {
						boolean patternFlag = StringUtils.isNotBlank(notBlank.pattern());
						if (patternFlag) {
							if (!ValidateUtils.validateDateString((String) target, notBlank.pattern())) {
								return baseResult + ErrorEnum.DATE_FORMAT_ERROR.getCode() + ",error:\"" + paramName
												+ " " + ErrorEnum.DATE_FORMAT_ERROR.getDesc() + "\"}";
							}
						} else {
							return baseResult + ErrorEnum.PARAM_IS_NULL_ERROR.getCode()
											+ ",error:\"服务端错误：NotBlank strType为date类型时，pattern参数不为空\"}";
						}
					} else if (StringUtils.equalsIgnoreCase(notBlank.strType(), "email")) {
						if (!ValidateUtils.isEmail((String) target)) {
							return baseResult + ErrorEnum.DATE_FORMAT_ERROR.getCode() + ",error:\"" + paramName + " "
											+ ErrorEnum.DATE_FORMAT_ERROR.getDesc() + "\"}";
						}
					} else if (StringUtils.equalsIgnoreCase(notBlank.strType(), "mobile")) {
						if (!ValidateUtils.isMobileNO((String) target)) {
							return baseResult + ErrorEnum.PHONENO_FORMAT_ERROR.getCode() + ",error:\"" + paramName
											+ " " + ErrorEnum.PHONENO_FORMAT_ERROR.getDesc() + "\"}";
						}
					}
				}
			}
		} else if (Number.class.equals(paramTypeClass.getSuperclass())) {
			if (target == null) {
				return baseResult + ErrorEnum.PARAM_IS_NULL_ERROR.getCode() + ",error:\"" + paramName + " "
								+ ErrorEnum.PARAM_IS_NULL_ERROR.getDesc() + "\"}";
			}
		} else if (Date.class.equals(paramTypeClass)) {
			if (target == null) {
				return baseResult + ErrorEnum.PARAM_IS_NULL_ERROR.getCode() + ",error:\"" + paramName + " "
								+ ErrorEnum.PARAM_IS_NULL_ERROR.getDesc() + "\"}";
			}
		}
		return null;
	}

}
