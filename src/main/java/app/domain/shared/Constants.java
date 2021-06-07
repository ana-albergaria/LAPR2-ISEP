package app.domain.shared;

import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_MED_LAB_TECHNICIAN = "MED LAB TECHNICIAN";
    public static final String ROLE_CLINICAL_CHEM_TECHNOLOGIST = "C CHEM TECH";
    public static final String ROLE_SPECIALIST_DOCTOR = "SPEC DOCTOR";
    public static final String ROLE_LAB_COORDINATOR = "LAB COORDINATOR";
    public static final String ROLE_CLIENT = "CLIENT";


    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    //created because it's handy for the tests
    public static final String CLASS_BARCODE_API = "app.domain.adapters.BarbecueAdapter";
    public static final String CLASS_REGRESSION_MODEL = "app.domain.adapters.SimpleLinearRegressionAdapter";
    public static final String DATE_INTERVAL = "28/05/2021-7/06/2021";
    public static final String HISTORICAL_POINTS = "15";

    public static final String BLOOD_MODULE_1_NAME = "BloodModule1 (for blood tests, requires a key access)";
    public static final String BLOOD_MODULE_2_NAME = "BloodModule2 (for Blood tests, no access key)";
    public static final String COVID_MODULE_NAME = "CovidModule (for Covid 19 Tests)";

    public static final String BLOOD_EXTERNAL_ADAPTER_2 = "app.domain.adapters.ExternalModule2APIAdapter";
    public static final String BLOOD_EXTERNAL_ADAPTER_3 = "app.domain.adapters.ExternalModule3APIAdapter";
    public static final String COVID_EXTERNAL_ADAPTER = "app.domain.adapters.CovidReferenceValues1APIAdapter";

    public static final String [] BASE_CSV_DATA = {"Test_Code","NHS_Code","Lab_ID","CitizenCard_Number","NHS_Number","TIN","BirthDay","PhoneNumber","Name","E-mail","Address","TestType","Category","HB000","WBC00","PLT00" ,"RBC00" ,"Category","HDL00" ,"Category","IgGAN","Test_Reg_DateHour","Test_Chemical_DateHour" ,"Test_Doctor_DateHour" ,"Test_Validation_DateHour"};

    //Mathematics
    //-> Simple Linear Regression
    public static final double A0 = 0;
    public static final double B0 = 0;


}
