package com.banco.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBaseTest implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		// Ejemplo de consulta SELECT para verificar la conexión
		String sql = "SELECT NAME FROM USUARIOS WHERE ID = 1"; 
		try {
			String result = jdbcTemplate.queryForObject(sql, String.class);
			System.out.println("Resultado de la consulta: " + result);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("No se encontraron resultados para la consulta: " + e);
		}

	}

}
