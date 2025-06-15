package inventory.tracking.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Context;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.container.ResourceInfo;
import java.io.IOException;
import java.security.Principal;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtRequestFilter implements ContainerRequestFilter
{
    @Context
    private ResourceInfo resourceInfo; // Inject ResourceInfo

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        // Check if the method is annotated with @PermitAll
        boolean isPermitAll = resourceInfo.getResourceMethod().isAnnotationPresent(PermitAll.class) ||
                                resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class);

        // Temporarily allow access to all resources, should be commented in a production environment
        if (isPermitAll)
        {
            return; // Allow access without authentication for @PermitAll methods
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer "))
        {
            try
            {
                String token = authHeader.substring("Bearer ".length());
                DecodedJWT jwt = JwtUtil.verifyToken(token);
                final SecurityContext originalContext = requestContext.getSecurityContext();

                requestContext.setSecurityContext(new SecurityContext()
                {
                    @Override public Principal getUserPrincipal()
                    {
                        return () -> jwt.getSubject();
                    }

                    @Override public boolean isUserInRole(String role)
                    {
                        return jwt.getClaim("role").asString().equals(role);
                    }

                    @Override public boolean isSecure() { return originalContext.isSecure(); }
                    @Override public String getAuthenticationScheme() { return "Bearer"; }
                });
            }
            catch (JWTVerificationException e)
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        else
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}