package com.application.social.media.wrapper;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse implements Serializable {

	private String status;
	private String message;
	private Object data;
	private Integer statusCode;
	
}