package app.ui.console;

import app.controller.ShowAllTestsController;
import app.ui.console.utils.Utils;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class showTestsUI implements Runnable{

    ShowAllTestsController ctrl;

    public showTestsUI()
    {
        this.ctrl = new ShowAllTestsController();
    }
    public void run()
    {
        Utils.showList(ctrl.getAllTests(), "All registered tests");
    }
}
