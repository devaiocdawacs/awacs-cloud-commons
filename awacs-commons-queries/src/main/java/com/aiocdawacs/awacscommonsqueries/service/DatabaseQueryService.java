package com.aiocdawacs.awacscommonsqueries.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.aiocdawacs.awacscommonsqueries.model.ProductOrderDto;
import com.aiocdawacs.awacscommonsqueries.query.AwacsMedicineOrderQueries;

@Service
public class DatabaseQueryService {

	private final AwacsMedicineOrderQueries awacsMedicineOrderQueries;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public DatabaseQueryService(AwacsMedicineOrderQueries awacsMedicineOrderQueries,
			DataSource dataSource) {
		this.awacsMedicineOrderQueries = awacsMedicineOrderQueries;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public List<ProductOrderDto> getByBulletOrderSearchQuery( String title, LocalDateTime startDate) {
		return jdbcTemplate.query( awacsMedicineOrderQueries.getByBulletOrderSearchQuery(),
				new Object[] {title, startDate}, 
				new ResultSetExtractor<List<ProductOrderDto>>() {

			@Override
			public List<ProductOrderDto> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ProductOrderDto> orders = new ArrayList<>();
				while(rs.next()) {
					ProductOrderDto order = new ProductOrderDto(
							rs.getLong("id"), 
							rs.getTimestamp("creation_date").toLocalDateTime());
					orders.add(order);
				}
				return orders;
			}
		} );
	}
}