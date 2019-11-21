package monoliths.catalogs;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(CatalogModuleConfiguration.class)
public @interface EnableCatalogModule {

}
