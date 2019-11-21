package monoliths.shipments;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(ShipmentModuleConfiguration.class)
public @interface EnableShipmentModule {

}
