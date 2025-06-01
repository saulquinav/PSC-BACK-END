package file.service.auth;

import jakarta.ws.rs.core.SecurityContext;
import file.service.entity.UserEntity;

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
        // You can implement roles if needed
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
