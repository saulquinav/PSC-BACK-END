package inventory.tracking.resource;

import inventory.tracking.auth.CorsFilter;

import inventory.tracking.auth.JwtRequestFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Configures RESTful Web Services for the application.
 */
@ApplicationPath("resources")
public class RestApplication extends Application
{
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(AuthResource.class);
//        classes.add(HelloWorldResource.class);
//        classes.add(InventoryItemResource.class);
//        classes.add(InventoryLogResource.class);
//        classes.add(PreflightResource.class);
//        classes.add(UserInfoResource.class);
//        classes.add(UserResource.class);
//
//        classes.add(CorsFilter.class); // Make sure this is added
//        classes.add(JwtRequestFilter.class);
//        return classes;
//    }
}