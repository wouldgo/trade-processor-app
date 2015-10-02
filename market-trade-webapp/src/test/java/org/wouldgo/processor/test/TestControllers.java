package org.wouldgo.processor.test;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.wouldgo.processor.test.conf.ConfigurationMock;

/**
 * Tests for controllers.
 *
 * @author "wouldgo"
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigurationMock.class})
public class TestControllers {

	private final static transient Logger logger = LoggerFactory.getLogger(TestControllers.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("setting up the mockMvc object");
		}
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void ping() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing ping call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.get("/ping"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void consumeTradeMessage() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing consume trade message call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/trade-message")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"134256\",\"currencyFrom\": \"EUR\",\"currencyTo\": \"GBP\",\"amountSell\": 1000,\"amountBuy\": 747.10,\"rate\": 0.7471,\"timePlaced\" : \"14-JAN-15 10:27:44\",\"originatingCountry\" : \"FR\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getTradeMessages() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing get trade messages call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/trade-messages")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
	}

	@Test
	public void getNations() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing get nations call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/nations")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	public void getAmountSellByUser() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing get amount sell by user call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/amount-sell")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	public void getAmountBuyByUser() throws Exception {

		if (TestControllers.logger.isDebugEnabled()) {

			TestControllers.logger.debug("Performing get amount buy by user call");
		}
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/amount-buy")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}
}
