package com.github.bjansen;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ListsValidationTest {

	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void should_accept_non_empty_list() {
		var bean = new NonEmptyList();

		bean.nonEmptyList = List.of("one");

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).isEmpty();
	}

	@Test
	void should_reject_empty_list() {
		var bean = new NonEmptyList();

		bean.nonEmptyList = Collections.emptyList();

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).hasSize(1);
	}

	@Test
	void should_accept_list_with_non_empty_values() {
		var bean = new ListWithNonEmptyValues();

		bean.listWithNonEmptyValues = List.of("one", "two", "one", "two");

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).isEmpty();
	}

	@Test
	void should_reject_list_with_invalid_values() {
		var bean = new ListWithNonEmptyValues();

		bean.listWithNonEmptyValues = List.of("", "four");

		var violations = validator.validate(bean);

		Assertions.assertThat(violations).hasSize(2);
	}
}
