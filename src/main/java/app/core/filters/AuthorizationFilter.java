package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.auth.UserCredentials;
import app.core.auth.UserCredentials.ClientType;

public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("=== Authorization filter started");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// to handle pre-flight requests in case of cross-origin situations
		if (httpRequest.getMethod().equalsIgnoreCase("options")) {
			System.out.println("=== PREFLIGHT (Authorization filter)");
			chain.doFilter(request, response);
		} else {
			String requestUri = httpRequest.getRequestURI();
			UserCredentials user = (UserCredentials) httpRequest.getAttribute("user");
			System.out.println("=== Authorization filter - request uri: " + requestUri);

			if (requestUri.contains("/api/admin") && user.getClientType() != ClientType.ADMIN) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"ADMIN API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only Admin can access this zone!");
			} else if (requestUri.contains("/api/school_director")
					&& user.getClientType() != ClientType.SCHOOL_DIRECTOR) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"SCHOOL_DIRECTOR API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only school_director can access this zone!");
			} else if (requestUri.contains("/api/teacher")
					&& user.getClientType() != ClientType.TEACHER) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"TEACHER API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only teacher can access this zone!");
			} else if (requestUri.contains("/api/parent")
					&& user.getClientType() != ClientType.PARENT) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"PARENT API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only teacher can access this zone!");
			} else {
				chain.doFilter(request, response);
			}
		}

		// 3. some more actions if needed
		System.out.println("=== Authorization filter is done");
	}

}