package app.ui.console;

import app.controller.RecordSamplesController;
import app.controller.SelectCalController;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.mappers.dto.ClinicalAnalysisLaboratoryDTO;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MedLabTechUI implements Runnable {
    private SelectCalController ctrl;
    private String laboratoryID;

    public MedLabTechUI() {
        this.ctrl = new SelectCalController();
    }

    @Override
    public void run() {

        laboratoryID = getSelectedCalID();
        if(laboratoryID != null) {
            List<MenuItem> options = new ArrayList<MenuItem>();
            options.add(new MenuItem("Record the Samples collected of a Test", new RecordSamplesUI(laboratoryID)));

            int option = 0;
            do
            {
                option = Utils.showAndSelectIndex(options, "\n\nMedical Lab Technician Menu:");

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }
            }
            while (option != -1 );
        }


    }

    private String getSelectedCalID() {

        ClinicalAnalysisLaboratoryDTO selectedCalDto = (ClinicalAnalysisLaboratoryDTO) Utils.showAndSelectOne(ctrl.getCalListDTO(),
                "Please selected in which Clinical Analysis Laboratory you are working: ");

        if(selectedCalDto != null)
            return selectedCalDto.getLaboratoryID();
        else
            return null;
    }
}


