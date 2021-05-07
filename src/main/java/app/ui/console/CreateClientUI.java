package app.ui.console;

import app.controller.App;
import app.controller.RegisterClientController;
import app.ui.console.utils.Utils;
import auth.AuthFacade;

import java.util.Date;
import java.util.List;

public class CreateClientUI implements Runnable{

    private RegisterClientController ctrl;

    public CreateClientUI(){
        ctrl = new RegisterClientController();
    }

    @Override
    public void run() {
        boolean success;
        System.out.println("\nCreate a client category:");
        do{
            success = createClient();
        }while (!success);
        System.out.println(App.getInstance().getCompany().getClientStore().getClients());
        System.out.println("\n Client successfully created!");
    }

    private boolean createClient(){
        boolean success = false;
        boolean confirm;
        try {
            String citizenCard = Utils.readLineFromConsole("Enter your citizen card number: ");
            String nhsNumber = Utils.readLineFromConsole("Enter your nhs number: ");
            Date birthDate = Utils.readDateFromConsole("Enter your birth date in the format dd-mm-yyyy: ");
            String tinNumber = Utils.readLineFromConsole("Enter your tin number: ");
            String email = Utils.readLineFromConsole("Enter your email: ");
            String name = Utils.readLineFromConsole("Enter your name: ");
            String phoneNumber = Utils.readLineFromConsole("Enter your phoneNumber: ");
            if(Utils.confirm("Want to add client's sex?")) {
                String sex = Utils.readLineFromConsole("Enter your sex (Male or Female): ");
                ctrl.registerClient(citizenCard, nhsNumber, birthDate,sex, tinNumber, email, name, phoneNumber);
                confirm = Utils.confirm(String.format("Please confirm the data (type `s` if its correct, `n` if it is not):" +
                                "%n Citizen card: %s%n Nhs number: %s%n Birth date: %s%n Tin number: %s%n, Email: %s%n, " +
                                "Name: %s%n Phone number: %s%n Sex: %s%n",
                        citizenCard, nhsNumber, birthDate.toString(), tinNumber, email, name, phoneNumber, sex));
            }else{
                ctrl.registerClient(citizenCard, nhsNumber, birthDate, tinNumber, email, name, phoneNumber);
                confirm = Utils.confirm(String.format("Please confirm the data (type `s` if its correct, `n` if it is not):" +
                                "%n Citizen card: %s%n Nhs number: %s%n Birth date: %s%n Tin number: %s%n, Email: %s%n, Name: %s%n Phone number: %s%n",
                        citizenCard, nhsNumber, birthDate.toString(), tinNumber, email, name, phoneNumber));
            }
            if(!confirm) throw new Exception("Please enter the correct data");
            success = ctrl.saveClient();
            if(!success) throw new Exception("Error: Client either already existent or null, please try again");
            success = ctrl.makeClientAnUserAndSendPassword();
            if(!success) throw new Exception("Error: Client is already an user of the system");
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
