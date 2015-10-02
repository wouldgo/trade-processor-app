package org.wouldgo.common.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wouldgo.common.dto.ErrorMessage;
import org.wouldgo.common.http.status.BadRequestException;
import org.wouldgo.common.http.status.InternalServerErrorException;
import org.wouldgo.common.http.status.MovedTemporarilyException;
import org.wouldgo.common.http.status.NotAuthorizedException;
import org.wouldgo.common.http.status.NotFoundException;
import org.wouldgo.common.utils.HttpConvertionUtil;

/**
 * Tests the {@linkplain HttpConvertionUtil} component.
 *
 * @author "wouldgo"
 *
 */
public class TestHttpConvertionUtil {

	private static final transient Logger logger = LoggerFactory.getLogger(TestHttpConvertionUtil.class);
	private static final RuntimeException[] runtimeExceptionsToConvert = new RuntimeException[3];
	private static final RuntimeException[] springAnnotatedExceptions = new RuntimeException[5];

	@BeforeClass
	public static final void onlyOnce() {

		TestHttpConvertionUtil.runtimeExceptionsToConvert[0] = new IllegalArgumentException("iAE");
		TestHttpConvertionUtil.runtimeExceptionsToConvert[1] = new AccessDeniedException("aDE");
		TestHttpConvertionUtil.runtimeExceptionsToConvert[2] = new ArithmeticException("aE");

		TestHttpConvertionUtil.springAnnotatedExceptions[0] = new BadRequestException("bad-request");
		TestHttpConvertionUtil.springAnnotatedExceptions[1] = new InternalServerErrorException("internal-server-error");
		TestHttpConvertionUtil.springAnnotatedExceptions[2] = new MovedTemporarilyException("moved-temporarily");
		TestHttpConvertionUtil.springAnnotatedExceptions[3] = new NotAuthorizedException("not-authorized");
		TestHttpConvertionUtil.springAnnotatedExceptions[4] = new NotFoundException("not-found");
	}

	@Test
	@SuppressWarnings("static-method")
	public void httpRuntimeExceptionConversion() {

		if (TestHttpConvertionUtil.logger.isDebugEnabled()) {

			TestHttpConvertionUtil.logger.debug("Converting runtime exceptions");
		}
		for (RuntimeException aRuntimeException : TestHttpConvertionUtil.runtimeExceptionsToConvert) {

			ResponseEntity<ErrorMessage> fromExceptionToStatusCode = HttpConvertionUtil.fromExceptionToStatusCode(aRuntimeException);
			Assert.assertNotNull(fromExceptionToStatusCode);
			ErrorMessage errorMessage = fromExceptionToStatusCode.getBody();
			Assert.assertEquals(fromExceptionToStatusCode.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
			Assert.assertEquals(errorMessage.getErrorMessage(), aRuntimeException.getMessage());
			Assert.assertEquals(errorMessage.getErrorCode(), 0l);
		}
	}

	@Test
	@SuppressWarnings("static-method")
	public void httpSpringAnnotedExceptionConversion() {

		if (TestHttpConvertionUtil.logger.isDebugEnabled()) {

			TestHttpConvertionUtil.logger.debug("Converting spring annotated exceptions");
		}
		for (RuntimeException aspringAnnotatedException : TestHttpConvertionUtil.springAnnotatedExceptions) {

			Class<? extends RuntimeException> exceptionClazz = aspringAnnotatedException.getClass();
			if (exceptionClazz.isAnnotationPresent(ResponseStatus.class)) {

				ResponseEntity<ErrorMessage> fromExceptionToStatusCode = HttpConvertionUtil.fromExceptionToStatusCode(aspringAnnotatedException);
				Assert.assertNotNull(fromExceptionToStatusCode);

				ErrorMessage errorMessage = fromExceptionToStatusCode.getBody();
				ResponseStatus responseStatusAnnotation = exceptionClazz.getAnnotation(ResponseStatus.class);
				HttpStatus httpStatus = responseStatusAnnotation.value();

				Assert.assertEquals(fromExceptionToStatusCode.getStatusCode(), httpStatus);
				Assert.assertEquals(errorMessage.getErrorMessage(), aspringAnnotatedException.getMessage());
				Assert.assertEquals(errorMessage.getErrorCode(), 0l);
			} else {

				Assert.fail();
			}
		}
	}

	@Test
	@SuppressWarnings("static-method")
	public void httpNullConversion() {

		if (TestHttpConvertionUtil.logger.isDebugEnabled()) {

			TestHttpConvertionUtil.logger.debug("Converting null");
		}
		ResponseEntity<ErrorMessage> fromExceptionToStatusCode = HttpConvertionUtil.fromExceptionToStatusCode(null);
		Assert.assertNotNull(fromExceptionToStatusCode);
		ErrorMessage errorMessage = fromExceptionToStatusCode.getBody();
		Assert.assertEquals(fromExceptionToStatusCode.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
		Assert.assertEquals(errorMessage.getErrorMessage(), "N/A");
		Assert.assertEquals(errorMessage.getErrorCode(), Long.MIN_VALUE);
	}
}
