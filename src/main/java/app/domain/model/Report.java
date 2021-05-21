package app.domain.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a Report through:
 * a report text.
 *
 * @author Marta Ribeiro 1201592
 */

public class Report {

    /**
     * Reference value for checking reportText words count.
     */
    public static int REPORTTEXT_MAX_WORDS = 400;

    /**
     * The report text.
     */
    private String reportText;

    /**
     * Build a report instance receiving:
     * the report text.
     *
     * @param reportText the report text.
     */
    public Report(String reportText){
        checkReportTextRules(reportText);
        this.reportText = reportText;
    }

    /**
     * Counts the number of words in a text.
     *
     * @param reportText the report text.
     * @return the number os words in a text.
     */
    private int countWords(String reportText){
        if((reportText == null) || (reportText.isEmpty()))
            return 0;
        //Save words on an array:
        String[] words = reportText.split("\\s+"); //"\\s+" will find one or more spaces and split the String accordingly
        return words.length;
    }

    /**
     * Checks if the report text is valid:
     * - it isn't blank;
     * - it has a maximum of 400 words.
     *
     * @param reportText the report text.
     */
    private void checkReportTextRules(String reportText){
        if (StringUtils.isBlank(reportText))
            throw new IllegalArgumentException("Report cannot be blank.");
        if (countWords(reportText)>REPORTTEXT_MAX_WORDS)
            throw new IllegalArgumentException("Report must have up to 400 words.");
    }

    @Override
    public String toString(){
        return ">> REPORT <<" +
                "%n> Report text: " + reportText;
    }

}
