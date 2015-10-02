package org.wouldgo.processor.web.filters;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@linkplain Filter} that manage the cross origin resource sharing.
 *
 * @author "wouldgo"
 *
 */
public class CORSFilter implements Filter {

	private static final String COMMA_SPACE = ", ",
			CORS_ACAC = "Access-Control-Allow-Credentials",
			CORS_ACAO = "Access-Control-Allow-Origin",
			CORS_ACAM = "Access-Control-Allow-Methods",
			CORS_ACAH = "Access-Control-Allow-Headers",
			CORS_ACMA = "Access-Control-Max-Age", ORIGIN_HEADER = "Origin",
			CONTENT_TYPE_HEADER = "Content-Type",
			CONTENT_LENGTH_HEADER = "Content-Length",
			AUTHORIZATION_HEADER = "Authorization", ACCEPT_HEADER = "Accept",
			X_REQUEST_WITH_HEADER = "X-Requested-With",
			X_HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override",
			GET_METHOD = "GET", PUT_METHOD = "PUT", POST_METHOD = "POST",
			DELETE_METHOD = "DELETE", OPTIONS_METHOD = "OPTIONS",
			ALLOWED_METHODS = CORSFilter.GET_METHOD + CORSFilter.COMMA_SPACE
			+ CORSFilter.PUT_METHOD + CORSFilter.COMMA_SPACE
			+ CORSFilter.POST_METHOD + CORSFilter.COMMA_SPACE
			+ CORSFilter.DELETE_METHOD + CORSFilter.COMMA_SPACE
			+ CORSFilter.OPTIONS_METHOD,
			ALLOWED_HEADERS = CORSFilter.ORIGIN_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.ACCEPT_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.CONTENT_TYPE_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.AUTHORIZATION_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.X_REQUEST_WITH_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.X_HTTP_METHOD_OVERRIDE_HEADER + CORSFilter.COMMA_SPACE
			+ CORSFilter.CONTENT_LENGTH_HEADER;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String originHeader = request.getHeader(CORSFilter.ORIGIN_HEADER);
		String originHeaderEncoded = "*";
		if (originHeader != null) {

			originHeaderEncoded = URLEncoder.encode(originHeader, "UTF-8");
		}
		response.setHeader(CORSFilter.CORS_ACAO, originHeaderEncoded);
		response.setHeader(CORSFilter.CORS_ACAC, "true");
		response.setHeader(CORSFilter.CORS_ACAM, CORSFilter.ALLOWED_METHODS);
		response.setHeader(CORSFilter.CORS_ACAH, CORSFilter.ALLOWED_HEADERS);
		response.setHeader(CORSFilter.CORS_ACMA, "3600");

		chain.doFilter(req, res);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) {
	}
}
