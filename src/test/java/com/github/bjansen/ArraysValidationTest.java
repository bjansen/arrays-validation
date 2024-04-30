package com.github.bjansen;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ArraysValidationTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void should_accept_non_empty_array() {
		var bean = new NonEmptyArray();

		bean.nonEmptyArray = new String[]{"one"};

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).isEmpty();
	}

	@Test
	void should_reject_empty_array() {
		var bean = new NonEmptyArray();

		bean.nonEmptyArray = new String[]{};

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).hasSize(1);
	}

	@Test
	void should_accept_array_with_non_empty_values() {
		var bean = new ArrayWithNonEmptyValues();

		bean.arrayWithNonEmptyValues = new String[]{"one", "two", "one", "two"};

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).isEmpty();
	}

	@Test
	void should_reject_array_with_invalid_values() {
		var bean = new ArrayWithNonEmptyValues();

		bean.arrayWithNonEmptyValues = new String[]{"", "four"};

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).hasSize(2);
	}

	@Test
	void should_reject_array_with_invalid_values2() {
		var bean = new ArrayWithNonEmptyValues();

		bean.arrayWithNonEmptyValues = new String[]{"two", "three", "four"};

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).hasSize(1);
	}
}
