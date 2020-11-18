package com.aiocdawacs.awacscommonsqueries.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.aiocdawacs.awacscommonsqueries.model.ProductOrderDto;

@Service
public class DatabaseQueryService {

	
	@Value("${BULLET_ORDER_QUERY:}")
	String BULLET_ORDER_QUERY;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public DatabaseQueryService() {

	}

	@Transactional
	public List<ProductOrderDto> getByBulletOrderSearchQuery(String pharmasistCode) {
		return jdbcTemplate.query( BULLET_ORDER_QUERY,
				new Object[] {pharmasistCode}, 
				new ResultSetExtractor<List<ProductOrderDto>>() {

			@Override
			public List<ProductOrderDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ProductOrderDto> orders = new ArrayList<>();
				while(rs.next()) {
					ProductOrderDto order = new ProductOrderDto(
							rs.getLong("id"), 
							rs.getString("pharmasistId"),
							rs.getTimestamp("creation_date").toLocalDateTime());
					orders.add(order);
				}
				return orders;
			}
		} );
	}
}