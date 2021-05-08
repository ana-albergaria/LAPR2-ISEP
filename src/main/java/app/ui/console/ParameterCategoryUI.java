package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.ui.console.utils.Utils;

public class ParameterCategoryUI implements Runnable{
    CreateParameterCategoryController crtl;

    ParameterCategoryUI(){
        crtl = new CreateParameterCategoryController();
    };

    @Override
    public void run() {
        boolean success;
        System.out.println("\nCreate a parameter category:");
        do{
             success = createParameterCategory();
        }while (!success);
        System.out.println("\n Parameter category successfully created!");

    }

    private boolean createParameterCategory (){
        boolean success;
        boolean confirm;
        try {
            String code = Utils.readLineFromConsole("Enter category code ");
            String name = Utils.readLineFromConsole("Enter category name: ");
            crtl.createParameterCategory(code, name);
            confirm = Utils.confirm(String.format("Please confirm the data (type `s` if its correct, `n` if it is not):%n name: %s%n code: %s%n", name, code));
            if(!confirm) throw new Exception("Please enter the correct data");
            success = crtl.saveParameterCategory();
            if(!success) throw new Exception("Parameter Category either already existent or null, please try again");
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
            success = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }
}
