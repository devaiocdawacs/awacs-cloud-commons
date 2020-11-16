package com.aiocdawacs.cloud.stream.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum CloudEventType {

	PRODUCT_GET_BY_PRODUCT_ID,
	GET_ALL_PRODUCT_DETAILS,
	GET_ALL_DISTRIBUTOR_DETAILS,
	GET_ALL_PHARMASISTS_DETAILS,
	GET_ALL_PRODUCT_ORDER_DETAILS,
	CREATE_PRODUCT,
	CREATE_ORDER,
	GET_ORDER_BY_ORDER_ID,
	APPLICATION_START_EVENT,
	APPLICATION_ALIVE_EVENT
}
