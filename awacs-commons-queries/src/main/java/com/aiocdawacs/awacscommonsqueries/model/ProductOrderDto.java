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

	LocalDateTime creationDate;

	public ProductOrderDto(Long id, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
	}

	public ProductOrderDto() {
		super();
	}
}
