public class student2 extends Player {

    // No of rounds to look at from the back
    int lookbackThreshold = 25;

    // Tolerance threshold for friendly and unfriendly
    double tol = 0.7;
    double tolnegate = 0.5;

    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

        // Start by cooperating
        if (n == 0) {

            return 0;
        }

        /* Lookback at recent rounds
         * The idea behind looking back at recent rounds is in case of a player
         * having the actions defect 5 times, coop 5 times, it will skew results
         * and result in a wrong judgement by the agent
         */
        int lookback = lookbackThreshold;

        // Prevent the lookback from going out of index
        // This is only for the first few rounds
        if (n - lookback < 0) lookback = n;

        // Furthest round from current
        int lookbackfurthest = n - lookback < 0 ? 0 : n - lookback;

        // Calculate the amt of defects and the amt of cooperations by both players
        int opp1coop = 0, opp2coop = 0, opp1def = 0, opp2def = 0;

        for (int i = lookbackfurthest; i < n; i++)
        {
            if (oppHistory1[i] == 0) opp1coop += 1;
            if (oppHistory1[i] == 1) opp1def += 1;
        }

        for (int i = lookbackfurthest; i < n; i++)
        {
            if (oppHistory2[i] == 0) opp2coop += 1;
            if (oppHistory2[i] == 1) opp2def += 1;
        }

        // "Friendliness" of opponent, based on coop and def ratio
        double opp1friendly = opp1coop/lookback; double opp2friendly = opp2coop/lookback;
        double opp1unfriendly = opp1def/lookback; double opp2unfriendly = opp2def/lookback;

        // If there's a good chance both opponents will cooperate based on recent history
        if (opp1friendly >= tol && opp2friendly >= tol)
        {
            // Cooperate
            return 0;
            // If there's a good chance both opponents will defect
        } else if (opp1unfriendly >= tolnegate || opp2friendly >= tolnegate) {
            // Defect
            return 1;
        }
        // If still undecided at this point, it's better to play it safe and just defect
        return 1;

    }
}