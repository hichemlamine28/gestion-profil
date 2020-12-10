package com.arkeup.link_innov.gestion_profil_mcs.commun.logging;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * WARNING : ne peut être utilisé que sur des variables public.
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Log {	
}
