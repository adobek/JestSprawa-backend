package com.pwr.jestsprawa;

import com.pwr.jestsprawa.model.Status;
import com.pwr.jestsprawa.repositories.StatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JestSprawaApplicationTests {

	@Autowired
	private StatusRepository statusRepository;

	@Test
	void contextLoads() {
	}

}