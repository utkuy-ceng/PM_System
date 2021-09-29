package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("success_delete_validator")
public class SuccessDelete implements Validator{

    private boolean delSuc =false;
    
    @Override
    public void validate(FacesContext fc, UIComponent uıc, Object o) throws ValidatorException {
        
        
    }

    public void setDelSuc(boolean delSuc) {
        this.delSuc = delSuc;
    }
    
    
    
}
