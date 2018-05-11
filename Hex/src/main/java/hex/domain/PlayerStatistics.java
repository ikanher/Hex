/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author akir
 */
public class PlayerStatistics {
    private Player player;
    private Map<Player, Integer> numberOfWins = new HashMap<>();
    private List<Player> playersWon = new ArrayList<>();

    public PlayerStatistics(Player player) {
        this.player = player;
    }

    public void addWin(Player opponent, int count) {
        playersWon.add(opponent);
        numberOfWins.put(opponent, count);
    }

    public List<Player> getPlayersWon() {
        return playersWon;
    }

    public int winCount(Player p) {
        return numberOfWins.get(p);
    }

    public void print() {
        System.out.println("PRINTING");
        numberOfWins.keySet()
                .stream()
                .forEach(key -> System.out.println("key: " + key + ", val: " + numberOfWins.get(key)));
    }
}
