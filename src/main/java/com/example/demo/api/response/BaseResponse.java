package com.example.demo.api.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
	private boolean success;
	private Integer statusCode;
	private String message;
	private T data;
}
