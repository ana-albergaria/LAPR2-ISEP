package app.ui.console;

import app.controller.CreateTestTypeController;

public class CreateTestTypeUI implements Runnable{
    private CreateTestTypeController ctrl;

    public void CreateTestTypeUI()
    {
        ctrl = new CreateTestTypeController();
    }

    @Override
    public void run() {

    }
}
