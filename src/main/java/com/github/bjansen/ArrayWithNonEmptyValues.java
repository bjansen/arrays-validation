package com.github.bjansen;

import jakarta.validation.constraints.Size;

public class ArrayWithNonEmptyValues {

	public @Size(min = 1, max = 3) String[] arrayWithNonEmptyValues;
}
