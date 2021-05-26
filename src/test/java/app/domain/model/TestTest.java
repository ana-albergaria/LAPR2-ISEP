package app.domain.model;

import app.domain.store.ClientStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TestTest {

    private List<Parameter> parametersBlood;
    private List<Parameter> parametersCovid;
    private List<ParameterCategory> pcListBlood;
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;
    private Date d1;

    @Before
    public void setUp() throws ParseException {
        parametersBlood = new ArrayList<>();
        parametersCovid = new ArrayList<>();

        d1 = new SimpleDateFormat("dd/MM/yyyy").parse("08/08/2001");

        pcListBlood = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Hemogram");
        pcListBlood.add(p1);
        Parameter rbc = new Parameter("RBC12", "rbc", "redbloodcells", p1);
        Parameter wbc = new Parameter("WBC12", "wbc", "whitebloodcells", p1);

        pcList = new ArrayList<>();
        p2 = new ParameterCategory("CODE1","covid");
        pcList.add(p2);
        Parameter igg = new Parameter("IGG12", "igg", "covidParam", p2);

        parametersBlood.add(rbc);
        parametersBlood.add(wbc);
        parametersCovid.add(igg);

        t1 = new TestType("CODE3","blood test","blood",pcListBlood);
        t2 = new TestType("CODE4","covid","swab",pcList);

        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestWithNullParameters(){
        app.domain.model.Test test = new app.domain.model.Test(null, null, null, null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findNotExistentClient(){
        ClientStore clientStore = new ClientStore();
        clientStore.getClientByCitizenCardNum("1234567890123456");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestWithUnder12CharsNHScode(){
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = new app.domain.model.Test("12345678901", client, t1, parametersBlood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestWithMore12CharsNHScode(){
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = new app.domain.model.Test("1234567890123", client, t1, parametersBlood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestWithNotAlphanumericNHScode(){
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = new app.domain.model.Test("!@#456789012", client, t1, parametersBlood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestWithemptyNHScode(){
        Client client = new Client("1234567890123456", "1234567890", d1, "Male", "1234567890", "alex@gmail.com", "Alex", "12345678901");
        app.domain.model.Test test = new app.domain.model.Test("", client, t1, parametersBlood);
    }

}

