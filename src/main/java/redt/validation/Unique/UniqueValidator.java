package redt.validation.Unique;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private static final String queryTemplate = "SELECT c FROM :table c WHERE :column = :value";

    @PersistenceContext
    private EntityManager entityManager;

    private Unique constraintAnnotation;
    @Override
    public void initialize(Unique constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String query = queryTemplate.replace(":table", constraintAnnotation.table());
        query = query.replace(":column", constraintAnnotation.column());

        List<Object> list = entityManager.createQuery(query).setParameter("value", value).setMaxResults(1).getResultList();
        if(list.size() == 0)
            return true;
        return false;
    }
}
