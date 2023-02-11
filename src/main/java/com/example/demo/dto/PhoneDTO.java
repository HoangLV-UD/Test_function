package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private Integer price;
	
	@JsonProperty("quantity")
	private String quantity;
}
