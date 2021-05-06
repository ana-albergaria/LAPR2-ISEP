package app.ui.console;

import app.controller.CreateParameterController;
import app.domain.model.ParameterCategory;
import app.mappers.dto.CategoriesDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CreateParameterUI {

    private CreateParameterController ctrl;

    public CreateParameterUI(){
        ctrl = new CreateParameterController();
    }

    /*public void run(){
        boolean success;
        System.out.println("To create a new Paramter, please insert the requested data.");
        do{
            success = createParameter();
        } while (!success);
        System.out.println("\nParameter created!");
    }*/

    /*private boolean createParameter(){
        String prmCode = Utils.readLineFromConsole("Enter parameter code:");
        String shortName = Utils.readLineFromConsole("Enter name:");
        String description = Utils.readLineFromConsole("Enter description:");
        List<String> categoriesCodes = getCategoriesToCodes();

    }*/

    /*private List<String> getCategoriesToCodes (){
        List<CategoriesDTO> parameterCategories = new ArrayList<>();
        parameterCategories = showListAndSelectObjects(parameterCategories);
        List<String> parameterCategoriesCodes = new ArrayList<>();
        for(CategoriesDTO p: parameterCategories){
            parameterCategoriesCodes.add(p.getCode());
        }
        return parameterCategoriesCodes;
    }*/

}
