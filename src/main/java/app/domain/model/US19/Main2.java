
package app.domain.model.US19;

import app.controller.App;
import app.controller.CreateTestController;
import app.controller.ImportTestController;
import app.controller.ShowAllTestsController;
import app.domain.model.*;
import app.domain.shared.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main2 {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException, ParseException {
        /*List<Test> testList = new ArrayList<>();
        List<ParameterCategory> pcsCovid = new ArrayList<>();
        ParameterCategory pc1 = new ParameterCategory("CODE3","covid");
        pcsCovid.add(pc1);
        Date d1 = new Date();
        Parameter parameter = new Parameter("IgGAN", "name", "descrip",pc1);
        List<Parameter> listParameter = new ArrayList<>();
        listParameter.add(parameter);
        TestType t1 = new TestType("covid","descr","swab",pcsCovid, Constants.COVID_EXTERNAL_ADAPTER);
        Client c1 = new Client("1234567890123456","1234567890",d1,"1234567890","carlos@gmail.com","Carlos","12345678901");
        Test test1 = new Test("123456789012",c1,t1,listParameter);
        test1.addTestResult("IgGAN",1.5,"mg");
        Calendar hoje = Calendar.getInstance();
        hoje.set(Calendar.YEAR, 2021);
        hoje.set(Calendar.MONTH, 4);    // janeiro é representado por 0
        hoje.set(Calendar.DAY_OF_MONTH, 30);
        Date d2 = hoje.getTime();
        test1.setDateOfChemicalAnalysis(d2);
        testList.add(test1);
        System.out.println(test1);
        System.out.println(d2);

        List <List<String>>  tableOfValues = getTestsWithResultsDataForTableOfValues(15,d1, testList);
        System.out.println(tableOfValues);

         */
/*
        StringBuilder text = new StringBuilder();
        text.append(String.format("%-20s%-20s%n", "Date", "Number of OBSERVED positive cases"));
        for (int i = 0; i < tableOfValues.get(0).size(); i++) {
            text.append(String.format("%-35s%-35s%n", tableOfValues.get(0).get(i), tableOfValues.get(1).get(i)));
        }

        System.out.println(text.toString());

 */


        ImportTestController ctrl = new ImportTestController();
        ctrl.importTestsFromFile("C:/Users/Ana Albergaria/Desktop/tests_CovidMATCPCSV.csv");

        Calendar hoje = Calendar.getInstance();
        hoje.set(Calendar.YEAR, 2021);
        hoje.set(Calendar.MONTH, 4);    // janeiro é representado por 0
        hoje.set(Calendar.DAY_OF_MONTH, 29);

        Date d1 = hoje.getTime();
        List<Test> testList = App.getInstance().getCompany().getTestStore().getTests();

        List <List<String>>  tableOfValues = getTestsWithResultsDataForTableOfValues(15,d1, testList);
        System.out.println(tableOfValues);

        StringBuilder text = new StringBuilder();
        text.append(String.format("%-20s%-20s%n", "Date", "Number of OBSERVED positive cases"));
        for (int i = 0; i < tableOfValues.get(0).size(); i++) {
            text.append(String.format("%-35s%-35s%n", tableOfValues.get(0).get(i), tableOfValues.get(1).get(i)));
        }

        System.out.println(text.toString());





    }
    public static List < List<String> > getTestsWithResultsDataForTableOfValues(int numberOfObservations,
                                                                                Date currentDate,
                                                                                List<Test> testList) throws ParseException {
        List< List<String> > tableOfValues = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<String> observedPositives = new ArrayList<>();

        addDatesColumnToTableOfValues(numberOfObservations, currentDate, dates);
        addObservedPositivesToTableOfValues(numberOfObservations, dates, observedPositives, testList);

        tableOfValues.add(dates);
        tableOfValues.add(observedPositives);







        return tableOfValues;

    }

    public static void addObservedPositivesToTableOfValues(int numberOfObservations,
                                                           List<String> dates,
                                                           List<String> observedPositives,
                                                           List<Test> testList) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int[] observedPositivesInt = new int[numberOfObservations];
        int indexDate = 0;

        for (Test test : testList) {
            if(test.hasPositiveResultForCovid()) {
                for (int i = 0; i < dates.size(); i++) {
                    Date dateToBeCompared = sdf.parse(dates.get(i));
                    if(checkIfDatesAreEqual(test.getDateOfChemicalAnalysis(), dateToBeCompared)) {
                        indexDate = i;
                        observedPositivesInt[indexDate]++;
                    }
                }
            }
        }
        convertIntegerListToString(observedPositives, observedPositivesInt);
    }

    public static void convertIntegerListToString(List<String> observedPositives, int[] observedPositivesInt) {
        for (int i = 0; i < observedPositivesInt.length; i++) {
            observedPositives.add(String.valueOf(observedPositivesInt[i]));
        }


    }


    public static void addDatesColumnToTableOfValues(int numberOfObservations,
                                                     Date currentDate,
                                                     List<String> dates) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        for (int i = 0; i < numberOfObservations; i++) {
            dates.add(sdf.format(currentDate));
            cal.add(Calendar.DAY_OF_MONTH,-1);
            currentDate = cal.getTime();
        }
    }

    public static boolean checkIfDatesAreEqual(Date date, Date otherDate) {
        Calendar cal = Calendar.getInstance();
        Calendar otherCal = Calendar.getInstance();
        cal.setTime(date);
        otherCal.setTime(otherDate);
        return cal.get(Calendar.DAY_OF_MONTH) == otherCal.get(Calendar.DAY_OF_MONTH) &&
                cal.get(Calendar.MONTH) == otherCal.get(Calendar.MONTH) &&
                cal.get(Calendar.YEAR) == otherCal.get(Calendar.YEAR);
    }

}



