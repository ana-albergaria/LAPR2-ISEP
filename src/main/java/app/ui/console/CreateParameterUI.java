package app.ui.console;

import app.controller.CreateParameterController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.mappers.dto.CategoriesDTO;
import app.ui.console.utils.Utils;
import auth.domain.store.UserRoleStore;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class CreateParameterUI implements Runnable {

    private CreateParameterController ctrl;

    public CreateParameterUI(){
        ctrl = new CreateParameterController();
    }

    public void run(){
        boolean successA = true;
        boolean confirm;
        List<Parameter> prmToValidate = new ArrayList<>();
        Parameter prm;
        do {
            do {
                successA = createParameter(); //shows parameter categories list and asks to select one
            } while (!successA);
            prm = ; //PROBLEMA: COMO IGUALAR AO PARAMETRO CRIADO ?
            prmToValidate = allParametersToValidate(prmToValidate, prm);
            confirm = Utils.confirm("Do you intend to create more parameters?\n[Type 's' for yes or 'n' for no.]");
        } while(confirm);
        //fazer a validação e o save de todos
    }

    private boolean createParameter(){
        System.out.println("To create a new Parameter, please insert the requested data.");
        String parameterCode = Utils.readLineFromConsole("Enter parameter code: ");
        String shortName = Utils.readLineFromConsole("Enter parameter name: ");
        String description = Utils.readLineFromConsole("Enter parameter description: ");
        CategoriesDTO category = showListAndSelectOneObject(); //vai buscar a categoria pretendida
        return ctrl.createParameter(parameterCode, shortName, description, category.getCode()); //US10 SD: 19 a 25
    }

    private List<Parameter> allParametersToValidate(List<Parameter> listPrmToValidate, Parameter prm){
        listPrmToValidate.add(prm);
        return listPrmToValidate;
    }

    private boolean confirmAndSaveParameters(List<Parameter> listPrmToValidate){
        boolean success;
        boolean confirmation;
        for (int i = 0; i < listPrmToValidate.size(); i++) {
            try {
                confirmation = Utils.confirm(String.format(">> PARAMETER " + i+1 + " <<" +
                                "%nPlease confirm the following data:" +
                                "%n> Parameter code: %s;" +
                                "%n> Name: %s;" +
                                "%n> Description: %s;" +
                                "%n> Desired parameter category: %s." +
                                "%n[Type 's' for correct or 'n' for wrong.]",
                        listPrmToValidate.get(i).getPrmCode(), listPrmToValidate.get(i).getShortName(),
                        listPrmToValidate.get(i).getDescription(), listPrmToValidate.get(i).getPc().getName())); //show
                if (!confirmation) throw new Exception("Please enter the correct data."); //PROBLEMA: SE ESTIVER ERRADA COMO É QUE ELE VOLTA A INSERIR ?
                success = ctrl.saveParameter();
                if (!success) throw new Exception("Parameter either already exists or is null. Please try again."); //PROBLEMA: COMO É QUE ELE FAZ O TRY AGAIN ?
            //PROBLEMA: NÃO PERCEBI O QUE ACONTECE A PARTIR DAQUI
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
                success = false;
            }catch(Exception e){
                System.out.println(e.getMessage());
                success = false;
            }
        }
    }

    private CategoriesDTO showListAndSelectOneObject(){
        List<CategoriesDTO> categoriesDTO = ctrl.getParameterCategoriesDTO();
        CategoriesDTO category = (CategoriesDTO) Utils.showAndSelectOne(categoriesDTO, "Enter the number of the category you want to associate with the parameter: ");
        return category;
    }

}
