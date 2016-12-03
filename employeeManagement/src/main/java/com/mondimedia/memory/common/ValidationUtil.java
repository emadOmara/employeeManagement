package com.mondimedia.memory.common;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * Generic validator which uses Bean validation to validate model
 *
 * @author <a href="mailto:emad.omara85@gmail.com">Emad Omara</a>
 *
 */
public class ValidationUtil {

	public static <T> Set<ConstraintViolation<T>> validate(T obj) {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Set<ConstraintViolation<T>> result = vf.getValidator().validate(obj);
		return result;

	}
}
