package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("only_letter_validator")
public class OnlyLetter implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uıc, Object o) throws ValidatorException {
        
        if(!IsOnlyLetter(o.toString())){
            FacesMessage message = new FacesMessage(" Please use only letters!");
            throw new ValidatorException(message);
        }
        
        if(o.toString().length()>45){
            FacesMessage message = new FacesMessage(" Input is too long!");
            throw new ValidatorException(message);
        }
    }
    
    public boolean IsOnlyLetter(String input) {
        boolean result = false;

        // ilk karakter boşluk olamaz
        if (input.charAt(0) == ' ') {
            return false;
        }

        // son karakter boşluk olamaz
        if (input.charAt(input.length() - 1) == ' ') {
            return false;
        }

        // string trimleniyor boşluklar siliniyor en son temiz karakterler kontrol ediliyor
        String inp2 = input.replaceAll(" ", "");

        for (int i = 0; i < inp2.length(); i++) {

            if (!Character.isLetter(inp2.charAt(i))) {
                return false;
            }

            if (i == inp2.length() - 1 && Character.isLetter(inp2.charAt(i))) {
                result = true;
            }
        }
        // peşpeşe birden fazla boşluk gelmemesi kontrolü

        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                continue;
            }

            if (i == input.length() - 1) {
                continue;
            }

            if ((input.charAt(i) == ' ' && input.charAt(i + 1) == ' ') || (input.charAt(i) == ' ' && input.charAt(i - 1) == ' ')) {
                return false;
            }

            if (i == input.length() - 1) {
                result = true;
            }
        }
        return result;
    }
    
}
