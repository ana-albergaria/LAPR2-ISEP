package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Report {

    public static int REPORTTEXT_MAX_WORDS = 400;

    private String reportText;

    private Report report;

    public Report(String reportText){
        checkReportTextRules(reportText);
        this.reportText = reportText;
    }

    private int countWords(String reportText){
        if((reportText == null) || (reportText.isEmpty()))
            return 0;
        //Save words on an array:
        String[] words = reportText.split("\\s+"); //"\\s+" will find one or more spaces and split the String accordingly
        return words.length;
    }

    private void checkReportTextRules(String reportText){
        if (StringUtils.isBlank(reportText))
            throw new IllegalArgumentException("Report cannot be blank.");
        if (countWords(reportText)>REPORTTEXT_MAX_WORDS)
            throw new IllegalArgumentException("Report must have up to 400 words.");
    }


}
