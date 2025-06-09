package file.service.resource;

import file.service.auth.CorsFilter;
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
//        classes.add(file.service.resource.DocumentMetadataResource.class);
//        classes.add(file.service.auth.CorsFilter.class); // Make sure this is added
//        return classes;
//    }
}