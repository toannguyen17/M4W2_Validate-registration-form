package redt.validation.Unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String table() default "Users";
    String column() default "email";

    String message() default "Already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
