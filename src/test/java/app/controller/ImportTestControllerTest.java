package app.controller;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.shared.Constants;
import app.domain.store.ClientStore;
import app.domain.store.ParameterStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ImportTestControllerTest {
    private List<Parameter> parametersBlood;
    private List<Parameter> parametersCovid;
    private List<ParameterCategory> pcListBlood;
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private ParameterCategory p3;
    private TestType t1;
    private TestType t2;
    private Date d1;
    ParameterStore parameterStore = new ParameterStore();

    @Before
    public void setUp() throws ParseException {
        TestTypeStore testTypeStore = App.getInstance().getCompany().getTestTypeStore();
        ParameterStore parameterStore = App.getInstance().getCompany().getParameterStore();

        parametersBlood = new ArrayList<>();
        parametersCovid = new ArrayList<>();

        d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");

        pcListBlood = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Hemogram");
        pcListBlood.add(p1);
        Parameter rbc = parameterStore.createParameter("RBC00", "rbc", "redbloodcells", p1);
        parameterStore.saveParameter(rbc);
        Parameter wbc =parameterStore.createParameter("WBC00", "wbc", "whitebloodcells", p1);
        parameterStore.saveParameter(wbc);
        Parameter plt = parameterStore.createParameter("PLT00", "plt", "plt", p1);
        parameterStore.saveParameter(plt);
        Parameter hb = parameterStore.createParameter("HB000", "hb", "hb", p1);
        parameterStore.saveParameter(hb);
        Parameter hdl = parameterStore.createParameter("HDL00", "hdl", "cholest", p1);
        parameterStore.saveParameter(hdl);

        pcList = new ArrayList<>();
        p2 = new ParameterCategory("CODE1","covid");
        pcList.add(p2);
        Parameter igg = new Parameter("IgGAN", "iga", "covidParam", p2);
        parameterStore.saveParameter(igg);
        parametersBlood.add(rbc);
        parametersBlood.add(wbc);
        parametersCovid.add(igg);

        t1 = new TestType("Blood","blood test","blood",pcListBlood, Constants.BLOOD_EXTERNAL_ADAPTER_3);
        t2 = new TestType("Covid","covid","swab",pcList, Constants.COVID_EXTERNAL_ADAPTER);

    }

    @Test
    public void createCovidTestsFromFile() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        ImportTestController ctrl = new ImportTestController();
        ctrl.importTestsFromFile("C:/Users/jluca/Downloads/tests_CovidMATCPCSV.csv");
        System.out.println(App.getInstance().getCompany().getTestStore().getTests().get(2));
    }

    @Test
    public void createBloodCovidTests() throws ClassNotFoundException, InstantiationException, ParseException, IllegalAccessException {
        ImportTestController ctrl = new ImportTestController();
        ctrl.importTestsFromFile("C:/Users/jluca/Desktop/dadosteste.csv");

    }

}