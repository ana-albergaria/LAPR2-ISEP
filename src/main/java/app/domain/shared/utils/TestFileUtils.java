package app.domain.shared.utils;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestFileUtils {

    public static List<String> dataLabels = new ArrayList<>();

    public static List<String> getDataLabels() {
        return dataLabels;
    }

    public static List<String[]> getTestDataByFile(String filePath){
        File csvFile = new File(filePath);
        List<String[]> processedListData = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line = bufferedReader.readLine();
            dataLabels = Arrays.asList(line.split(";"));
            line = bufferedReader.readLine();
            while(line != null){
                String [] attributes = line.split(";");
                processedListData.add(attributes);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processedListData;
    }

    public static List<String> getParameterCodes(String [] arrayData){
        List<String> parameterCodes = new ArrayList<>();
        int indexOfTestType = dataLabels.indexOf("TestType") + 1;
        int indexOfFirstDate = dataLabels.indexOf("Test_Reg_DateHour");
        for (int i=indexOfTestType; i < indexOfFirstDate; i++){
            if(!arrayData[i].equals("NA") && !dataLabels.get(i).equals("Category")){
                parameterCodes.add(dataLabels.get(i));
            }
        }
        return parameterCodes;
    }

    public static List<Double> getParameterResults(String [] arrayData) throws ParseException {
        List<String> parameterCodes = getParameterCodes(arrayData);
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        List<Double> parameterResults = new ArrayList<>();
        int indexOfCode;
        for(String code : parameterCodes){
            indexOfCode = dataLabels.indexOf(code);
            parameterResults.add(format.parse(arrayData[indexOfCode]).doubleValue());
        }
        return parameterResults;
    }

}
