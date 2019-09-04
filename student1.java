public class student1 extends Player{
    //Uses T4T strategy against both opponents as a whole,
    // and reevaluates overall strategy every 10 turns.
    int defectCount1 = 0;
    int defectCount2 = 0;
    double defectRate1 = 0.00;
    double defectRate2 = 0.00;
    static final double defectLimit = 0.25; //maximum tolerated rate of defection

    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
        if (n==0) return 0; //cooperate by default
        defectCount1 += oppHistory1[n-1]; //keep track of the number of defects by opponent 1
        defectCount2 += oppHistory2[n-1]; //keep track of the number of defects by opponent 2
        defectRate1 = defectCount1/oppHistory1.length; //calculate the rate of defection of opponent 1
        defectRate2 = defectCount2/oppHistory2.length; //calculate the rate of defection of opponent 2
        if (n%10==0){ //every 10 turns, base our action on overall performance of either opponent
            //if either opponent defects more than 25% of the time, defect
            if ((defectRate1>=defectLimit)|(defectRate2>=defectLimit)) return 1;
        }
        //cooperate if both opponents cooperated previously
        if ((oppHistory1[n-1]+oppHistory2[n-1])==0) return 0;
            //random if both opponents had different actions
        else if ((oppHistory1[n-1]+oppHistory2[n-1])==1) return (Math.random() < 0.5) ? 0:1;
            //defect if both players defected
        else return 1;
    }
}