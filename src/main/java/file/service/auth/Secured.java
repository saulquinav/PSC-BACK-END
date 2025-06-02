package file.service.auth;

/* This is a custom annotation used solely as marker.
** It acts as a marker for another custom class, the JwtRequestFilter class.
** We should apply this @Secured annotation to 'Resource' (controller) classes
** or to methods of 'Resource' classes where we want the JwtRequestFilter to be applied.
** The custom JwtRequestFilter will only be applied where the @Secured annotation is used. */

import jakarta.ws.rs.NameBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// This interface should be removed
/* Very important! We use the @NameBinding annotation here, which tells JAX-RS library
* that the @Secured annotation can be used to associate filters/interceptors with
* the methods or resources that have this annotation.
* JwtRequestFilter class will use this custom annotation.
* By using the @Secured annotation to the JwtRequestFilter class, we are basically saying:
* Only apply this JwtRequestFilter to classes or methods that are annotated with @Secured. */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured { }