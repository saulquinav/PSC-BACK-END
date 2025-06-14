package inventory.tracking.auth;

import jakarta.ws.rs.core.SecurityContext;
import inventory.tracking.entity.UserEntity;

import java.security.Principal;

// Not used in this version of the project. Instead, we use an inline implementation
// of the 'SecurityContext' interface
public class AppSecurityContext implements SecurityContext
{
    private final UserEntity userEntity;

    public AppSecurityContext(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    @Override
    public Principal getUserPrincipal()
    {
        return () -> userEntity.getUsername();
    }

    @Override
    public boolean isUserInRole(String role)
    {
        // Here we should implement roles, if necessary
        return false;
    }

    @Override
    public boolean isSecure()
    {
        return false; // Or check the URI scheme
    }

    @Override
    public String getAuthenticationScheme()
    {
        return "Bearer";
    }
}
