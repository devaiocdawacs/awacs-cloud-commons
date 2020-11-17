package com.aiocdawacs.awacscommonsqueries.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_order")
public class ProductOrderDto {

	@Id
	Long id;
	String pharmasistCode;
	LocalDateTime creationDate;

	public ProductOrderDto(Long id, String pharmasistCode, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.pharmasistCode = pharmasistCode;
		this.creationDate = creationDate;
	}

	public ProductOrderDto() {
		super();
	}
}
