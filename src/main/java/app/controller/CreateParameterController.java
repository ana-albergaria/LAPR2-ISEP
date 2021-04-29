package app.controller;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

public class CreateParameterController {

    private ParameterCategory pc;
    private Parameter prm;

    public ParameterCategory getParameterCategory(){
        return pc;
    }

    public CreateParameterController(){

    }

    public CreateParameterController(ParameterCategory pc){
        this.pc = pc;
        this.prm = null;
    }

    /*public boolean createParameter(String parameterCode, String shortName, String description){

    }*/
    //+cat +categoryCode

    /*public boolean saveParameter(){

    }*/

}
