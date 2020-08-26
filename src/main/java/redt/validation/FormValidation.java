package redt.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import redt.form.FormRegister;

public class FormValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FormValidation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormRegister formRegister = (FormRegister) target;

        if (formRegister.getPassword() != null &&
                formRegister.getPasswordConfirmation() != null &&
                !formRegister.getPassword().equals(formRegister.getPasswordConfirmation()))
        {
            errors.rejectValue("passwordConfirmation", "passwordConfirmation.invalid");
        }
    }
}
