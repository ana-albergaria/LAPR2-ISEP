package app.domain.shared.utils;

import app.domain.shared.Constants;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TestFileUtils {

    public static List<String[]> getTestDataByFile(String filePath){
        File csvFile = new File(filePath);
        List<String[]> processedListData = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
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
        if(!arrayData[12].equals("NA")){
            for(int i = 13; i<17; i++) {
                parameterCodes.add(Constants.BASE_CSV_DATA[i]);
            }
        }
        if(!arrayData[17].equals("NA")){
            parameterCodes.add(Constants.BASE_CSV_DATA[18]);
        }
        if(!arrayData[19].equals("NA")){
            parameterCodes.add(Constants.BASE_CSV_DATA[20]);
        }
        return parameterCodes;
    }

    public static List<Double> getParameterResults(String [] arrayData) throws ParseException {
        List<String> parameterCodes = getParameterCodes(arrayData);
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        List<Double> parameterResults = new ArrayList<>();
        int indexOfCode;
        for(String code : parameterCodes){
            indexOfCode = Arrays.asList(Constants.BASE_CSV_DATA).indexOf(code);
            parameterResults.add(format.parse(arrayData[indexOfCode]).doubleValue());
        }
        return parameterResults;
    }

}
