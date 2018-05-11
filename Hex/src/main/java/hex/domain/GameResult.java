/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hex.domain;

/**
 * Object for temporarily storing a Hex game result.
 * 
 * @author akir
 */
public class GameResult {
    private Player red;
    private Player blue;
    private Player winner;
    private boolean isWin = false;

    public GameResult(Player red, Player blue) {
        this.red = red;
        this.blue = blue;
    }

    /**
     * Sets the winner of this game result.
     *
     * @param winner Player that won the game
     */
    public void setWinner(Player winner) {
        this.isWin = true;
        this.winner = winner;
    }

    /**
     * Checks this GameResult is a winning situation.
     *
     * @return true if game was won
     */
    public boolean isWin() {
        return isWin;
    }

    public Player getRedPlayer() {
        return red;
    }

    public Player getBluePlayer() {
        return blue;
    }

    /**
     * Gets the winner of this game result.
     *
     * @return Player object representing the winner
     */
    public Player getWinner() {
        return winner;
    }
}
