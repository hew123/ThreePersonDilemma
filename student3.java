public class student3 extends Player {
    // Give max of 5 chances to signal willingness to coop
    int chancesGiven = 10;
    boolean coopNextRound = false; //will be set True after retaliating
    boolean checkNextRound = false; //will be set True after cooperating to signal willingness to coop

    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

        // Start by cooperating
        if (n == 0) {

            return 0;
        }
        //defect last few rounds
        if (n >95 ) {

            return 1;
        }

        // give chance after first time defect
        if (coopNextRound==true) {
            coopNextRound = false; //reset coopNextRound
            checkNextRound = true; // check if giving chance works to encourage opponents to cooperate
            return 0;
        }

        //Check if giving chance/signalling willingness to cooperate worked
        if (checkNextRound == true){
            if ((oppHistory1[n-1]==0)&& (oppHistory2[n-1]==0)) { // it worked
                checkNextRound=false;
                return 0;
            }
            else{ //at least one of them refuses to cooperate still
                //System.out.println("giving chance did not work");
                chancesGiven -= 1; //minus 1 chance given
                checkNextRound=false;
                return 1;
            }
        }

        //coopNextRound and checkNextRound is false

        //if at least one of them defect, defect if coopNextRound==false
        if (((oppHistory1[n-1]==1)|| (oppHistory2[n-1]==1))){
            if (chancesGiven>0) {
                coopNextRound=true;
            }

            return 1;
        }
        //if both coop, always coop
        if ((oppHistory1[n-1]==0)&& (oppHistory2[n-1]==0)) {
            return 0;
        }

        return 1;

    }
}
