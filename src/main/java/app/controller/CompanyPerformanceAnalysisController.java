package app.controller;

import app.domain.model.Company;
import app.domain.model.CompanyPerformance;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller class for analysing the company performance
 *
 * @author Marta Ribeiro 1201592
 */
public class CompanyPerformanceAnalysisController {

    /**
     * The company associated with the Controller.
     */
    private Company company;

    /**
     * Builds an empty constructor for having the actual instance of the company when instantiated.
     */
    public CompanyPerformanceAnalysisController(){
        this(App.getInstance().getCompany());
    }

    /**
     * Builds a Company Performance Analysis Controller's instance receiving the company.
     *
     * @param company company associated to the Controller.
     */
    public CompanyPerformanceAnalysisController(Company company){
        this.company = company;
        this.companyPerformance = null;
    }

    /**
     * The company performance.
     */
    private CompanyPerformance companyPerformance;


    /**
     -     * Creates an instance of company performance type.
     -     * @param beginningDate the first moment of study
     -     * @param endingDate the last moment of study
     -     * @param chosenAlg the chosen algorithm
     -     * @return true is the company performance is successfully created
     -     * otherwise return false.
     -     * @throws ClassNotFoundException
     -     * @throws IllegalAccessException
     -     * @throws InstantiationException
     -     */
    public boolean createCompanyPerformance(Date beginningDate, Date endingDate, String chosenAlg) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            this.companyPerformance = company.createCompanyPerformance(beginningDate,endingDate,chosenAlg);
            return (companyPerformance != null);
    }
    /**
     * Creates a CompanyPerformance object with the data requested to the Laboratory Coordinator.
     *
     * @param beginningDay the first moment of study
     * @param endingDay the last moment of study
     * @param chosenAlgorithm the chosen algorithm
     */
    public void setCompanyPerformance(Date beginningDay, Date endingDay, String chosenAlgorithm) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.companyPerformance = new CompanyPerformance(beginningDay, endingDay, chosenAlgorithm, company);
    }

    /**
     * Gets the number of clients for an interval
     * @return the number of clients for an interval
     */
    public int getClientsInfoPerInterval() {
        companyPerformance.setCompany(this.company);
        return companyPerformance.getClientsNum();
    }

    /**
     * Gets the number of processed tests for an interval
     * @return the number of processed tests for an interval
     */
    public int getNumTestsProcessedInterval(){
        companyPerformance.setCompany(this.company);
        return companyPerformance.getProcessTestsNum();
    }

    //FIRST THE LB SELECTS DAY OR INTERVAL
    //SHOW ERROR MESSAGES!!! (CAN'T CHOOSE PRESENT OR FUTURE)
    //12 WORKING HOURS PER DAY
    //WORKING DAY IS FROM 8:00 TO 20:00 (endingDay will end at 19:59:59)
    //SUNDAY IS NOT A WORKING DAY

    /**
     * Gets an ArrayList with the tests info for the days of the interval
     * @return ArrayList with the tests info for the days of the interval
     */
    public ArrayList<int[]> getTestInfoPerDay(){
        companyPerformance.setCompany(this.company);
        return companyPerformance.getTestInfoDay();
    }

    /**
     * Gets an ArrayList with the tests info for the weeks of the interval
     * @return ArrayList with the tests info for the weeks of the interval
     */
    public ArrayList<int[]> getTestInfoPerWeek(){ //WEEK: FROM MONDAY TO SATURDAY (NO WORK AT SUNDAY)
        companyPerformance.setCompany(this.company);
        return companyPerformance.getTestInfoWeek();
    }

    /**
     * Gets an ArrayList with the tests info for the months of the interval
     * @return ArrayList with the tests info for the months of the interval
     */
    public ArrayList<int[]> getTestInfoPerMonth(){ //MONTH: FROM 1 TO END OF MONTH
        companyPerformance.setCompany(this.company);
        return companyPerformance.getTestInfoMonth();
    }

    /**
     * Gets an ArrayList with the tests info for the years of the interval
     * @return ArrayList with the tests info for the years of the interval
     */
    public ArrayList<int[]> getTestInfoPerYear(){ //YEAR: FROM JAN 1 TO DEC 31
        companyPerformance.setCompany(this.company);
        return companyPerformance.getTestInfoYear();
    }

    /**
     * Finds the beginning and the ending dates of the contiguous subsequence with maximum sum of an interval, through the chosen algorithm
     * @return the beginning and the ending dates of the contiguous subsequence with maximum sum
     */
    public Date[] findWorstSubIntWithChosenAlgorithm() {
        companyPerformance.setCompany(this.company);
        return companyPerformance.getWorstSubInt();
    }

}