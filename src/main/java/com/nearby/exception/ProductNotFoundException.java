package com.nearby.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product with provided id was not found!")
public class ProductNotFoundException extends RuntimeException {}
