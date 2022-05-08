package com.portal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
	private HttpStatus status;

	private String errorMessage;

	private LocalDateTime timeStamp;

	private String requestId;

}
