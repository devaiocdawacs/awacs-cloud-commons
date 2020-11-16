package com.aiocdawacs.cloud.stream.service;

public class CloudEventNameConstants {

	public static final String APPLICATION_START_EVENT				= "awacs.cloud.applicationstart";
	public static final String APPLICATION_ALIVE_EVENT				= "awacs.cloud.alive";
	
	public static final String DISTRIBUTOR_GETALL_EVENT_NAME 		= "awacs.cloud.distributor.getall";
	public static final String PHARMASIST_GETALL_EVENT_NAME  		= "awacs.cloud.pharmasist.getall";
	
	// ProductController
	public static final String PRODUCT_DETAILS_GETALL_EVENT_NAME  	= "awacs.cloud.product.getall";
	public static final String PRODUCT_SAVE_EVENT_NAME				= "awacs.cloud.product.save";
	public static final String PRODUCT_GET_BY_ID_EVENT_NAME			= "awacs.cloud.product.get";
	
	// ProductOrderController
	public static final String PRODUCT_ORDER_NEW_EVENT_NAME			= "awacs.cloud.product.order.new";
	public static final String PRODUCT_ORDER_GET_BY_ID				= "awacs.cloud.product.order.get";
	public static final String PRODUCT_ORDER_GET_ALL				= "awacs.cloud.product.order.getall";
}
