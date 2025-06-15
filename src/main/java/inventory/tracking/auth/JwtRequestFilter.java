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
import java.io.IOException;
import java.security.Principal;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtRequestFilter implements ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
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
        {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}