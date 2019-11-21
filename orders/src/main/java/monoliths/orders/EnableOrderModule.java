package monoliths.orders;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(OrderModuleConfiguration.class)
public @interface EnableOrderModule {

}
