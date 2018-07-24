package hello.filters.pre;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import hello.entity.EnvironmentCons;

import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;
import java.net.URL;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 */

public class QueryParamPortPreFilter extends ZuulFilter {
	 
	private static final Logger log = Logger.getLogger(QueryParamPortPreFilter.class.getName());
	
	public int filterOrder() {
		return 5 + 1;
	}

	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = getCurrentContext();
		log.info("Should route: "+ (ctx.getRequest().getParameter("environment") == null ? false : true) );
		return ctx.getRequest().getParameter("environment") != null;
	}

	public Object run() {
		RequestContext ctx = getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String environment = request.getParameter("environment");
		String urlEnvironment;
		try {
			switch (environment) {
			case EnvironmentCons.ENV_PROD:
				urlEnvironment = "http://zuul.microserviciodemo.svc:8765";
				break;

			default:
				urlEnvironment = "http://zuul.microserviciodemo.svc:8765";
				break;
			}
			log.info("URL: --> " + urlEnvironment);
			URL url = new URL(urlEnvironment);
			log.info("Route: --> " + url);
			ctx.setRouteHost(url);
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}
}