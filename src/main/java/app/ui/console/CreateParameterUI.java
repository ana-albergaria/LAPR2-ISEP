package app.ui.console;

import app.controller.CreateParameterController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.mappers.dto.CategoriesDTO;
import app.ui.console.utils.Utils;
import auth.domain.store.UserRoleStore;
import sun.security.krb5.internal.crypto.Aes128CtsHmacSha1EType;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

public class CreateParameterUI implements Runnable {

    private CreateParameterController ctrl;

    public CreateParameterUI(){
        ctrl = new CreateParameterController();
    }

    public void run(){
        boolean successA;
        boolean confirm;
        List<Parameter> prmToValidate = new ArrayList<>();
        Parameter prm;
        do {
            do {
                successA = createParameter(); //shows parameter categories list and asks to select one
            } while (!successA);
            prm = ctrl.getParameter(); //PROBLEMA: COMO IGUALAR AO PARAMETRO CRIADO ?
            prmToValidate = allParametersToValidate(prmToValidate, prm);
            confirm = Utils.confirm("Do you intend to create more parameters?\n[Type 's' for yes or 'n' for no.]");
        } while(confirm);
        do{
            prmToValidate = confirmParameters(prmToValidate);
        } while (prmToValidate.size() != 0); //fazer a validação e o save de todos
        System.out.println("Parameter successfully created!");
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

    private List<Parameter> confirmParameters(List<Parameter> listPrmToValidate){
        boolean success;
        boolean confirmation;
        List<Parameter> review = new ArrayList<>();
        for (int i = 0; i < listPrmToValidate.size(); i++) {
            confirmation = Utils.confirm(String.format(">> PARAMETER <<" +
                            "%nPlease confirm the following data:" +
                            "%n> Parameter code: %s;" +
                            "%n> Name: %s;" +
                            "%n> Description: %s;" +
                            "%n> Desired parameter category: %s." +
                            "%n[Type 's' for correct or 'n' for wrong.]",
                    listPrmToValidate.get(i).getPrmCode(), listPrmToValidate.get(i).getShortName(),
                    listPrmToValidate.get(i).getDescription(), listPrmToValidate.get(i).getPc().getName())); //vai repetir isto se não colocar 's' nem 'n'
            success = ctrl.saveParameter(); //ver se já existe ou é null
            //ESTÁ SEMPRE A VALIDAR O MESMO
            //POR ISSO DÁ SEMPRE QUE A PARTIR DO 1 JÁ EXISTEM
            try {
                if (listPrmToValidate.size() == 1) {
                    if (!confirmation) throw new Exception("Please enter the correct data.");
                    if (!success) throw new Exception("Parameter either already exists or is null. Please try again.");
                } else {
                    if (!confirmation)
                        throw new Exception("Please enter the correct data after confirming all the parameters.");
                    if (!success)
                        throw new Exception("Parameter either already exists or is null. Please try again after confirming all the parameters.");
                }
            }catch (IllegalArgumentException exception){ //validações
                System.out.println(exception.getMessage()); //recebe a mensagem
            }catch(Exception e){ //exceptions do try
                System.out.println(e.getMessage());
            }
            if(confirmation && success){
                review.remove(listPrmToValidate.get(i));
            }
        }
        return review;
    }

    private CategoriesDTO showListAndSelectOneObject(){
        List<CategoriesDTO> categoriesDTO = ctrl.getParameterCategoriesDTO();
        CategoriesDTO category = (CategoriesDTO) Utils.showAndSelectOne(categoriesDTO, "Enter the number of the category you want to associate with the parameter: ");
        return category;
    }

}
