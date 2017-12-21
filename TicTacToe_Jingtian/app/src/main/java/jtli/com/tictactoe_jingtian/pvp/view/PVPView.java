package jtli.com.tictactoe_jingtian.pvp.view;

/**
 * Created by Jingtian(Tansent).
 */

public interface PVPView {
    /**
     * disable a specific position that is selected
     * @param position last move
     */
    void disableItem(int position);

    /**
     *
     * @param playerImage 'O' for player1, 'X' for player2
     * @param position last move
     */
    void setMove(int playerImage, int position);

    /**
     * Send a message to handler to display the winner or a draw game
     * @param player 1 for player1, -1 for player2, 0 for draw game
     */
    void sendEndGameMsg(int player);

    /**
     * disable the board after a game is finished
     */
    void disableBoard();

    /**
     * enable the board when the game is started / before playing again
     */
    void enableBoard();

    /**
     * clear all images resources on the GridView before playing again
     */
    void clearGridView();

    /**
     * clear the winner/draw_game notification by making them invisible
     */
    void clearNotification();
}
