package app.domain.model.US19;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

public class CriticalValues {


        public static void main(String[] args) {



            // Table number 7 from MATCP - t-Student
            TDistribution td= new TDistribution(2);
            double alphaTD =0.25;
            if(alphaTD> 0.5) {
                double critTD = td.inverseCumulativeProbability(alphaTD);
                System.out.println("t-student critical value: " + critTD);
            }
            else {
                double critTD = td.inverseCumulativeProbability(1 - alphaTD);
                System.out.println("t-student critical value: " + critTD);
            }

            // Table number 8 from MATCP - Fisher–Snedecor distribution
            FDistribution fd= new FDistribution(3,4);
            double alphaFD= 0.01;
            double critFD= fd.inverseCumulativeProbability(1- alphaFD);
            System.out.println("Fisher–Snedecor critical value:" + critFD);
        }

}


