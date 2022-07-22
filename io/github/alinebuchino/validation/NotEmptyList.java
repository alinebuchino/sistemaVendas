package io.github.alinebuchino.validation;

import io.github.alinebuchino.validation.contrainstvalidation.NotEmpyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // será verificada em tempo de execução
@Target(ElementType.FIELD) // diz onde poderá ser colocada essa anotation
@Constraint(validatedBy = NotEmpyListValidator.class) // é o que declara que essa anotation é uma anotation de validação
public @interface NotEmptyList {
    String message() default "A lista não pode estar vazia!";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
