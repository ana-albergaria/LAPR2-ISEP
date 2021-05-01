package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ClinicalAnalysisLaboratoryTest {
    private List<ParameterCategory> pcList;
    private ParameterCategory p1;
    private ParameterCategory p2;
    private List<TestType> selectedTT;
    private TestType t1;
    private TestType t2;

    @Before
    public void setUp() {
        pcList = new ArrayList<>();
        p1 = new ParameterCategory("CODE1","Description");
        p2 = new ParameterCategory("CODE2","Description");
        pcList.add(p1);
        pcList.add(p2);
        t1 = new TestType("CODE3","Description","swab",pcList);
        t2 = new TestType("CODE4","Description","swab",pcList);
        selectedTT = new ArrayList<>();
        selectedTT.add(t1);
        selectedTT.add(t2);
    }

    //Test 1: Check that it is not possible to create an instance of the ClinicalAnalysisLaboratory
    // class with null values.
    @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
        System.out.println("ensureNullIsNotAllowed");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(null,
                null,null,null,null,null);
    }


    //AC2 - The Laboratory ID must have five alphanumeric characters.
    //Test 2, 3, 4: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a blank (null, empty (""), or whitespace) laboratoryID.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotNull() {
        System.out.println("ensureAC2LaboratoryIDNotNull");

        //- Null laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(null,
                "CAL","Lisboa","91841378811","1234567890", selectedTT);

    }
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotEmpty() {
        System.out.println("ensureAC2LaboratoryIDNotEmpty");

        //- Empty laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDNotWhiteSpace() {
        System.out.println("ensureAC2LaboratoryIDNotWhiteSpace");

        //- Whitespace laboratoryID
        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory(" ",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    //Test 5: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a laboratoryID length different than 5.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDWithRightLength() {
        System.out.println("ensureAC2LaboratoryIDWithRightLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL123456",
                "CAL","Lisboa","91841378811","1234567890",selectedTT);
    }



    //Test 6: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a laboratoryID that
    //doesn't contain alphanumeric characters.
    @Test(expected = IllegalArgumentException.class)
    public void ensureLaboratoryIDIsAlphanumeric() {
        System.out.println("ensureAC2LaboratoryIDIsAlphanumeric");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("C.L1@",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    //MUTATION - laboratoryID IS alphanumeric
    @Test
    public void LaboratoryIDAlreadyIsAlphanumeric() {
        System.out.println("AC2LaboratoryIDAlreadyIsAlphanumeric");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", selectedTT);
    }

    //AC3 - The name is a string with no more than 20 characters.
    //Test 7, 8, 9: Check that it is not possible to create an instance of the
    //ClinicalAnalysisLaboratory class with a with a blank
    // (null, empty (""), or whitespace) name.

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotNull() {
        System.out.println("ensureAC3NameNotNull");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                null,"Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotEmpty() {
        System.out.println("ensureAC3NameNotEmpty");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "","Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameNotWhiteSpace() {
        System.out.println("ensureAC3NameNotWhiteSpace");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                " ","Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameOnlyLetters() {
        System.out.printf("ensureAC3NameOnlyLetters");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "Ana24","Lisboa","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNameWithRightLength() {
        System.out.println("ensureAC3NameWithRightLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "Clinical Laboratory ManyLabs ","Lisboa","91841378811","1234567890", selectedTT);
    }

    //CONFIRM - MUTATION - if name.length = 20
    @Test
    public void ensureNameWithLengthOf20() {
        System.out.println("ensureMutationNameWithMaxLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "Laboratorio de Santa","Lisboa","91841378811","1234567890", selectedTT);
    }

    //AC4 - Address: A string with no more than 30 characters.

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotNull() {
        System.out.println("ensureAC4AddressNotNull");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL",null,"91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotEmpty() {
        System.out.println("ensureAC4AddressNotEmpty");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressNotWhiteSpace() {
        System.out.println("ensureAC4AddressNotWhiteSpace");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL"," ","91841378811","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureAddressWithRightLength() {
        System.out.println("ensureAC4AddressWithRightLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Av. 25 de Abril, Ed. Sao Joao, 127-1º Fte, Dir e Esq.","91841378811","1234567890",selectedTT);
    }

    //CONFIRM - MUTATION - address.length() <= 30
    @Test
    public void ensureAddressWithLengthLesserThan30() {
        System.out.println("ensureAC4AddressWithLengthLesserThan30");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Av. 25 de Abril","91841378811","1234567890",selectedTT);
    }

    //CONFIRM - MUTATION - address.length() == 30
    @Test
    public void ensureAddressWithLengthEqualsTo30() {
        System.out.println("ensureAC4AddressWithLengthEqualsTo30");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","thisAddressHasLengthofThirty01","91841378811","1234567890",selectedTT);
    }

    //AC5 - The Phone Number is a 11 digit number.
    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberNotNull() {
        System.out.println("ensureAC5PhoneNumberNotNull");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa",null,"1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberNotEmpty() {
        System.out.println("ensureAC5PhoneNumberNotEmpty");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberWhiteSpace() {
        System.out.println("ensureAC5PhoneNumberWhiteSpace");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa"," ","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberOnlyDigits() {
        System.out.println("ensureAC5PhoneNumberOnlyDigits");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","918413a7881","1234567890", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensurePhoneNumberWithRightLength() {
        System.out.println("ensureAC5PhoneNumberWithRightLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","918413788112","1234567890", selectedTT);
    }

    //AC6 - The TIN Number is a 10 digit number.
    @Test(expected = IllegalArgumentException.class)
    public void ensureTINNumberNotNull() {
        System.out.println("ensureAC6TINNumberNotNull");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811",null, selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTINNumberNotEmpty() {
        System.out.println("ensureAC6TINNumberNotEmpty");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTINNumberWhiteSpace() {
        System.out.println("ensureAC6TINNumberNotWhiteSpace");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811"," ", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTINNumberOnlyDigits() {
        System.out.println("ensureAC6TINNumberOnlyDigits");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","123456789!", selectedTT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTINNumberWithRightLength() {
        System.out.println("ensureAC6TINNumberWithRightLength");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","123456789011", selectedTT);
    }

    //AC7 - Type of tests must be an attribute of the Clinical Analysis Laboratory.
    @Test(expected = IllegalArgumentException.class)
    public void ensureListOfTestTypesNotNull() {
        System.out.println("ensureAC7ListOfTestTypesNotNull");

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureListOfTestTypesNotEmpty() {
        System.out.println("ensureAC6TINNumberNotEmpty");

        List<TestType> emptyList = new ArrayList<>();

        ClinicalAnalysisLaboratory instance = new ClinicalAnalysisLaboratory("CAL12",
                "CAL","Lisboa","91841378811","1234567890", emptyList);
    }

}