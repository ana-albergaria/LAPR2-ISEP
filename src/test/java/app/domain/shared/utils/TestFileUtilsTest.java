package app.domain.shared.utils;

import app.mappers.dto.TestFileDTO;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestFileUtilsTest {

    @Test
    public void testCsvProcess(){
       List<String[]> procedData = TestFileUtils.getTestDataByFile("C:/Users/jluca/Downloads/tests_BloodCovidMATCPMDISCCSV.csv");
        int totalLines=0;
       for (String[] s : procedData){
           for (String m : s){
               System.out.printf(";%s", m);
           }
           totalLines++;
           System.out.println();
           System.out.println();
       }
    }

    @Test
    public void testGetListOfParameters(){
        List<String[]> procedData = TestFileUtils.getTestDataByFile("C:/Users/jluca/Desktop/dadosteste.csv");

        List<String> codes = new ArrayList<>();

        for (String[] s : procedData){
            codes = TestFileUtils.getParameterCodes(s);
            for (String code : codes){
                System.out.printf("%s ", code);
            }
            System.out.println();
        }

    }

    @Test
    public void getParameterResultsTest() throws ParseException {
        List<String[]> procedData = TestFileUtils.getTestDataByFile("C:/Users/jluca/Downloads/tests_BloodCovidMATCPMDISCCSV.csv");

        List<Double> codes;

        for (String[] s : procedData){
            codes = TestFileUtils.getParameterResults(s);
            for (Double code : codes){
                System.out.printf("%f ", code);
            }
            System.out.println();
        }

    }

    @Test
    public void testGetListOfTestFileDto(){
        List<TestFileDTO> procedData = TestFileUtils.getTestsDataToDto("C:/Users/jluca/Desktop/dadosteste.csv");

        List<String> codes = new ArrayList<>();

        for (TestFileDTO s : procedData){
                System.out.printf("%s ", procedData);
            System.out.println();
        }

    }

}