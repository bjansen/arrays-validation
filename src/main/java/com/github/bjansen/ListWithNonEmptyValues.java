package com.github.bjansen;

import jakarta.validation.constraints.Size;

import java.util.List;

public class ListWithNonEmptyValues {
	public List<@Size(min = 1, max = 3) String> listWithNonEmptyValues;
}
