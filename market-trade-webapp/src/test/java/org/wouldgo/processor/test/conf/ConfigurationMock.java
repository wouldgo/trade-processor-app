package org.wouldgo.processor.test.conf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.wouldgo.common.confs.CommonsConfiguration;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.middleware.confs.MiddleWareConfiguration;
import org.wouldgo.middleware.entities.TradeMessage;
import org.wouldgo.middleware.repositories.TradeMessageRepository;
import org.wouldgo.processor.confs.ProcessorConfiguration;
import org.wouldgo.processor.controllers.ExceptionControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.ImmutableList;

/**
 * Mock configuration used in testing the webapplication components.
 *
 * @author "wouldgo"
 *
 */
@Configuration
@ComponentScan(basePackageClasses = ExceptionControllerAdvice.class)
@Import({ CommonsConfiguration.class, MiddleWareConfiguration.class})
@EnableWebMvc
public class ConfigurationMock extends WebMvcConfigurerAdapter {

	@Bean
	public static ObjectMapper objectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaModule());
		objectMapper.registerModule(new Jdk7Module());
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		return objectMapper;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {

		configurer.enable();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		converter.setObjectMapper(ProcessorConfiguration.objectMapper());
		converters.add(converter);
		super.configureMessageConverters(converters);
	}

	@Bean
	@SuppressWarnings("static-method")
	public TradeMessageRepository fakeRepository() {

		return new TradeMessageRepository() {

			/* (non-Javadoc)
			 * @see org.wouldgo.middleware.repositories.TradeMessageRepository#findTop100ByOrderByTimePlacedDesc()
			 */
			@Override
			public Collection<TradeMessage> findTop100ByOrderByTimePlacedDesc() {

				Collection<TradeMessage> toReturn = new ArrayList<>();
				toReturn.add(new TradeMessage("test-value", "test-value", "test-value", "test-value",
						DateTime.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
				toReturn.add(new TradeMessage("test-value-1", "test-value", "test-value", "test-value",
						DateTime.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));
				toReturn.add(new TradeMessage("test-value-2", "test-value", "test-value", "test-value",
						DateTime.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE));

				return ImmutableList.<TradeMessage>copyOf(toReturn);
			}

			/* (non-Javadoc)
			 * @see org.wouldgo.middleware.repositories.TradeMessageRepository#countNations()
			 */
			@Override
			public List<NationCounter> countNations() {
				NationCounter aNationCounter = new NationCounter("UK", 1l);
				return ImmutableList.<NationCounter>of(aNationCounter);
			}

			/* (non-Javadoc)
			 * @see org.wouldgo.middleware.repositories.TradeMessageRepositoryCustom#amountSellByUser()
			 */
			@Override
			public Collection<AmountSellByUser> amountSellByUser() {

				AmountSellByUser anAmount = new AmountSellByUser("one", BigDecimal.ZERO);
				return ImmutableList.<AmountSellByUser>of(anAmount);
			}

			/* (non-Javadoc)
			 * @see org.wouldgo.middleware.repositories.TradeMessageRepositoryCustom#amountBoughtByUser()
			 */
			@Override
			public Collection<AmountBuyByUser> amountBoughtByUser() {

				AmountBuyByUser anAmount = new AmountBuyByUser("one", BigDecimal.ZERO);
				return ImmutableList.<AmountBuyByUser>of(anAmount);
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#save(S)
			 */
			@Override
			public <S extends TradeMessage> S save(S arg0) {

				Assert.assertEquals(arg0.getUserId(), "134256");
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
			 */
			@Override
			public TradeMessage findOne(Long arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
			 */
			@Override
			public boolean exists(Long arg0) {
				return false;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#deleteAll()
			 */
			@Override
			public void deleteAll() {
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
			 */
			@Override
			public void delete(Iterable<? extends TradeMessage> arg0) {
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
			 */
			@Override
			public void delete(TradeMessage arg0) {
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
			 */
			@Override
			public void delete(Long arg0) {
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.CrudRepository#count()
			 */
			@Override
			public long count() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
			 */
			@Override
			public Page<TradeMessage> findAll(Pageable arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(S)
			 */
			@Override
			public <S extends TradeMessage> S saveAndFlush(S arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#save(java.lang.Iterable)
			 */
			@Override
			public <S extends TradeMessage> List<S> save(Iterable<S> arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#getOne(java.io.Serializable)
			 */
			@Override
			public TradeMessage getOne(Long arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#flush()
			 */
			@Override
			public void flush() {
			}

			@Override
			public List<TradeMessage> findAll(Iterable<Long> arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Sort)
			 */
			@Override
			public List<TradeMessage> findAll(Sort arg0) {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
			 */
			@Override
			public List<TradeMessage> findAll() {
				return null;
			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#deleteInBatch(java.lang.Iterable)
			 */
			@Override
			public void deleteInBatch(Iterable<TradeMessage> arg0) {

			}

			/* (non-Javadoc)
			 * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
			 */
			@Override
			public void deleteAllInBatch() {

			}
		};
	}

	@Bean
	@SuppressWarnings("static-method")
	public MessageSendingOperations<String> messagingTemplate() {

		return new SimpMessagingTemplate(new MessageChannel() {

			/* (non-Javadoc)
			 * @see org.springframework.messaging.MessageChannel#send(org.springframework.messaging.Message, long)
			 */
			@Override
			public boolean send(Message<?> message, long timeout) {
				return true;
			}

			/* (non-Javadoc)
			 * @see org.springframework.messaging.MessageChannel#send(org.springframework.messaging.Message)
			 */
			@Override
			public boolean send(Message<?> message) {

				Assert.assertEquals(((org.wouldgo.common.dto.TradeMessage)message.getPayload()).getUserId(), "134256");
				return true;
			}
		});
	}
}
