package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("only_digit_validator")
public class OnlyDigit implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uıc, Object o) throws ValidatorException {
        
        if(!IsOnlyDigit(o.toString())){
            FacesMessage message = new FacesMessage(" Please use only digit!");
            throw new ValidatorException(message);
        }
        
        if(o.toString().length()>45){
            FacesMessage message = new FacesMessage(" Input is too long!");
            throw new ValidatorException(message);
        }
    }
    
    private boolean IsOnlyDigit(String input) {
        boolean result = false;

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }

            if (i == input.length() - 1 && Character.isDigit(input.charAt(i))) {
                result = true;
            }
        }
        return result;
    }
    
}
