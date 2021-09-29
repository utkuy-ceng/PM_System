package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("double_input_validator")
public class DoubleInput implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uıc, Object o) throws ValidatorException {
        
        if(!isDoubleFormat(o.toString())){
            FacesMessage message = new FacesMessage(" Please use the xx.xx format!");
            throw new ValidatorException(message);
        }
        
        if(o.toString().length()>45){
            FacesMessage message = new FacesMessage(" Input is too long!");
            throw new ValidatorException(message);
        }
    }
    
    private boolean isDoubleFormat(String input) {
        boolean result = false;

        if (input.charAt(0) == '.' || input.charAt(input.length() - 1) == '.') {
            return false;
        }

        int dotCount = 0;

        for (int i = 0; i < input.length(); i++) {
            if (dotCount > 1) {
                return false;
            }

            if (input.charAt(i) == '.') {
                dotCount++;
            }

            // şuanki karaker nokta veya sayı değilse
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.') {
                return false;
            }

            if (i == input.length() - 1) {         
                result = true;
            }
        }
        return result;
    }
    
}
