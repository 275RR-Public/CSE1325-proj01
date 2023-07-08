// credit: https://github.com/ajdillhoff/CSE1325-Java/blob/main/interfaces/RollInitiativeExample/InitiativeUtility.java

package util;

import java.util.Arrays;

import core.Player;

public class InitiativeUtility {
    private Player[] players;
    private Integer[] indices;

    public InitiativeUtility(Player[] players) {
        this.players = players;
    }

    public Integer[] rollInitiative() {
        // Roll initiative for each player
        Integer[] rolls = new Integer[players.length];
        for (int i = 0; i < players.length; i++) {
            rolls[i] = players[i].rollInitiative();
        }

        ArrayIndexComparator c = new ArrayIndexComparator(rolls);
        this.indices = c.createIndexArray();
        Arrays.sort(this.indices, c);

        System.out.println("Rolling d20 + Dex modifier...");
        for (int i = 0; i < players.length; i++) {
            System.out.printf("\t%s (rolled %d)\n", players[indices[i]].getName(), rolls[indices[i]]);
        }

        // Check for duplicates
        resolveDuplicates(rolls, c, 0, this.indices.length - 1);

        return indices;
    }

    private void printArray(Integer[] rolls, int startIdx, int endIdx) {
        for (int i = startIdx; i <= endIdx; i++) {
            System.out.printf("\t%s (rolled %d)\n", players[indices[i]].getName(), rolls[indices[i]]);
        }
    }

    private int resolveDuplicates(Integer[] rolls, ArrayIndexComparator c, int startIdx, int endIdx) {
        // Check duplicates
        for (int i = startIdx, j = i; i < endIdx; i++, j++) {
            while (j < endIdx && rolls[indices[i]].compareTo(rolls[indices[j + 1]]) == 0) {
                j++;
            }

            if (i != j) {
                System.out.println("Duplicates.");
                System.out.println();

                System.out.printf("Rerolling...\n");
                for (int k = i; k <= j; k++) {
                    rolls[indices[k]] = players[indices[k]].rollInitiative();
                }
                Arrays.sort(indices, i, j + 1, c);

                printArray(rolls, i, j);

                i = resolveDuplicates(rolls, c, i, j);
            }
        }

        return endIdx;
    }
}
