package inventory.tracking.auth;

import jakarta.annotation.Priority;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.security.Principal;


//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class JwtRequestFilter implements ContainerRequestFilter
//{
//    @Context
//    private ResourceInfo resourceInfo; // Inject ResourceInfo
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException
//    {
//        // Check if the method is annotated with @PermitAll
//        boolean isPermitAll = resourceInfo.getResourceMethod().isAnnotationPresent(PermitAll.class) ||
//                                resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class);
//
//        // Temporarily allow access to all resources, should be commented in a production environment
//        if (isPermitAll)
//        {
//            return; // Allow access without authentication for @PermitAll methods
//        }
//
//        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        if (authHeader != null && authHeader.startsWith("Bearer "))
//        {
//            try
//            {
//                String token = authHeader.substring("Bearer ".length());
//                DecodedJWT jwt = JwtUtil.verifyToken(token);
//                final SecurityContext originalContext = requestContext.getSecurityContext();
//
//                requestContext.setSecurityContext(new SecurityContext()
//                {
//                    @Override public Principal getUserPrincipal()
//                    {
//                        return () -> jwt.getSubject();
//                    }
//
//                    @Override public boolean isUserInRole(String role)
//                    {
//                        return jwt.getClaim("role").asString().equals(role);
//                    }
//
//                    @Override public boolean isSecure() { return originalContext.isSecure(); }
//                    @Override public String getAuthenticationScheme() { return "Bearer"; }
//                });
//            }
//            catch (JWTVerificationException e)
//            {
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//            }
//        }
//        else
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//    }
//}

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtRequestFilter implements ContainerRequestFilter
{
    @Inject
    private JwtUtil jwtTokenUtil;

    @Context
    private ResourceInfo resourceInfo; // Inject ResourceInfo

    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        String path = requestContext.getUriInfo().getPath();

        // Check if the method is annotated with @PermitAll
        boolean isPermitAll = resourceInfo.getResourceMethod().isAnnotationPresent(PermitAll.class) ||
                resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class);

        // Temporarily allow access to all resources, should be commented in a production environment
        if (isPermitAll)
        {
            return; // Allow access without authentication for @PermitAll methods
        }

        if (path.startsWith("auth/login"))
            return; // Skip authentication for login endpoint


        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
        {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length()).trim();
        try
        {
            String username = jwtTokenUtil.getUsernameFromToken(token);

            // Create a custom SecurityContext
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> username;
                }

                @Override
                public boolean isUserInRole(String role) {
                    // For simplicity, all authenticated users have the "USER" role
                    return "USER".equals(role);
                }

                @Override
                public boolean isSecure() {
                    return requestContext.getUriInfo().getAbsolutePath().toString().startsWith("https");
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Bearer";
                }
            });

        }
        catch (Exception exc)
        {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}