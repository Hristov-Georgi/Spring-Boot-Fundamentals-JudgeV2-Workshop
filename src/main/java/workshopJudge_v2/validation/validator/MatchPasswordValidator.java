package workshopJudge_v2.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import workshopJudge_v2.model.binding.UserRegisterBindingModel;
import workshopJudge_v2.validation.anotations.MatchPasswords;

public class MatchPasswordValidator implements ConstraintValidator<MatchPasswords, Object> {

    private String originalField;
    private String confirmField;
    private String message;

    @Override
    public void initialize(MatchPasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.originalField = constraintAnnotation.originalField();
        this.confirmField = constraintAnnotation.confirmField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object password = new BeanWrapperImpl(value).getPropertyValue(originalField);
        Object confirmPassword = new BeanWrapperImpl(value).getPropertyValue(confirmField);

        boolean passwordMatch = password != null && password.equals(confirmPassword);

        if(!passwordMatch) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmField)
                    .addConstraintViolation();
        }

        return passwordMatch;
    }


}
