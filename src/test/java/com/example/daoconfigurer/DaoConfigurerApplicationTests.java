package com.example.daoconfigurer;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DaoConfigurerApplicationTests {

	@Autowired
    MockMvc mockMvc;

	@Test
	void daoAuthentication() throws Exception {
		mockMvc.perform(get("/").with(httpBasic("user", "user"))).andExpect(status().isOk()).andExpect(content().string("Hello user!"));
	}

}
