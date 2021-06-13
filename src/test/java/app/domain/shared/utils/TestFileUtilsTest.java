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
    TestFileUtils testFileUtils = new TestFileUtils();
    List<TestFileDTO> procedData = testFileUtils.getTestsDataToDto("tests_CovidMATCPCSV.csv");
    int totalLines=0;
        for (TestFileDTO s : procedData){
            System.out.println(s);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }


    /*@Test
    public void testGetListOfTestFileDto(){
        List<TestFileDTO> procedData = TestFileUtils.getTestsDataToDto("C:/Users/jluca/Desktop/dadosteste.csv");

        List<String> codes = new ArrayList<>();

        for (TestFileDTO s : procedData){
                System.out.printf("%s ", procedData);
            System.out.println();
        }

    }*/

}