public class HEW_GUOWEI_Player extends Player{

    //this strategy is a combination of Tit-for-Tat and TolerantPlayer
    //with the enhancement that i will play nasty as long as either opponents
    //were nasty
    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

        int opponent1Coop = 0;      //opp1 history of being nice
        int opponent2Coop = 0;      //opp2 history of being nice
        int opponent1Defect = 0;    //opp1 history of being nasty
        int opponent2Defect = 0;    //opp2 history of being nasty
        double threshold = 0.5;     //tolerance threshold

        for (int i = 0; i < n; i++) {
            if (oppHistory1[i] == 0)
                opponent1Coop = opponent1Coop + 1;
            else
                opponent1Defect = opponent1Defect + 1;
        }
        for (int i = 0; i < n; i++) {
            if (oppHistory2[i] == 0)
                opponent2Coop = opponent2Coop + 1;
            else
                opponent2Defect = opponent2Defect + 1;
        }

        if (n == 0) return 0; //start by being nice

        // if either opp defects more than 50% of the time, i defect
        // at the same time, punish opp's last round nasty behaviour if any
        else if (opponent1Defect > threshold * (opponent1Coop + opponent1Defect)
                || opponent2Defect > threshold * (opponent2Coop + opponent2Defect)
                || oppHistory1[n-1]==1
                || oppHistory2[n-1]==1)
            return 1;

        else return 0;

    }
}
