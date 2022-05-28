package pl.training.shop.commons.aop;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Primary
@Transactional
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalProxy {
}
