package inventory.tracking.auth;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ContainerResponseFilter;

import java.io.IOException;

/* This filter is for allowing to access the back-end from the front-end.
** This filter allows all origins (*). For production, we should replace * with our
** actual front-end origin (e.g., http://localhost:3000). */
@Provider
@Priority(Priorities.HEADER_DECORATOR) // make this filter run early
public class CorsFilter implements ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException
    {
//        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

        // For production, replace "http://localhost:3000" with "https://your-frontend.com"
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
//        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        // set for how long browsers will cache preflight results; 86400 means 24 hours
        responseContext.getHeaders().add("Access-Control-Max-Age", "86400");

        // Manually set OK status for OPTIONS preflight (optional for some cases)
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod()))
            responseContext.setStatus(Response.Status.OK.getStatusCode()); // a better choice

//            responseContext.setStatus(HttpServletResponse.SC_OK); // not recommended
    }
}


//@Provider
//@Priority(Priorities.HEADER_DECORATOR)
//public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter
//{
//    private static final String ALLOWED_ORIGIN = "http://localhost:3000";
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        // Intercept preflight request before it hits resource methods
//        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
//            requestContext.abortWith(Response.ok().build());
//        }
//    }
//
//    @Override
//    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//        responseContext.getHeaders().add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        responseContext.getHeaders().add("Access-Control-Max-Age", "86400");
//    }
//}
