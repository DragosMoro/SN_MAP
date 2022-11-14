package domain.validator;

import domain.User;
import exceptions.ValidationException;

import java.util.Objects;

public class UserValidator implements Validator<User>{
    private static final UserValidator instance = new UserValidator();

    private UserValidator() {}

    public static UserValidator getInstance() {
        return instance;
    }
    public void validate(User entity) throws ValidationException {
        String err="";

        if (Objects.equals(entity.getFirstName(), ""))
        {
            err = err + "First name can't be empty.\n";
        }
        if (Objects.equals(entity.getLastName(), ""))
        {
            err = err + "Last name can't be empty.\n";
        }
        if(!entity.getEmail().matches("^(.+)@(.+)$"))
        {
            err = err + "The email is invalid.\n";
        }

        if (err.length() > 0) {
            throw new ValidationException(err);
        }
    }
}
