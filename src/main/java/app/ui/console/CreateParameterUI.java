package app.ui.console;

import app.controller.CreateParameterController;
import app.domain.model.ParameterCategory;
import app.mappers.dto.CategoriesDTO;
import app.ui.console.utils.Utils;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class CreateParameterUI implements Runnable {

    private CreateParameterController ctrl;

    public CreateParameterUI(){
        ctrl = new CreateParameterController();
    }

    public void run(){
        boolean success = true;
        boolean confirm;
        do {
            do {
                success = createParameter(); //shows parameter categories list and asks to select one
            } while (!success);
            confirm = Utils.confirm("Do you intend to create more parameters?\n[Type 's' for yes or 'n' for no.]");
        } while(confirm);
    }

    private boolean createParameter(){
        boolean success;
        boolean confirmation;
        try{
            System.out.println("To create a new Parameter, please insert the requested data.");
            String parameterCode = Utils.readLineFromConsole("Enter parameter code: ");
            String shortName = Utils.readLineFromConsole("Enter parameter name: ");
            String description = Utils.readLineFromConsole("Enter parameter description: ");
            CategoriesDTO category = showListAndSelectOneObject();
            ctrl.createParameter(parameterCode, shortName, description, category.getCode());
            confirmation = Utils.confirm(String.format("Please confirm the following data:" +
                            "%nParameter code: %s;" +
                            "%nName: %s;" +
                            "%nDescription: %s;" +
                            "%nDesired parameter category: %s." +
                            "%n[Type 's' for correct or 'n' for wrong.]",
                    parameterCode, shortName, description, category.getName())); //show
            if (!confirmation) throw new Exception("Please enter the correct data.");
            success = ctrl.saveParameter();
            if (!success) throw new Exception("Parameter either already exists or is null. Please try again.");
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
            success = false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }

    //A CONFIRMAÇÃO TEM DE SER NO FINAL

    /*private boolean confirmAndSaveParameters(){
        boolean confirmation;
        for (int i = 0; i < ; i++) {

        }
    }*/

    private CategoriesDTO showListAndSelectOneObject(){
        List<CategoriesDTO> categoriesDTO = ctrl.getParameterCategoriesDTO();
        CategoriesDTO category = (CategoriesDTO) Utils.showAndSelectOne(categoriesDTO, "Please type the number of the category you want to associate with the parameter");
        return category;
    }

}
