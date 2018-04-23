/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.logic;

import hex.domain.Board;
import hex.domain.Player;

/**
 *
 * @author akir
 */
public interface GameEndChecker {
    public boolean isWin(Board b, Player p);
}
