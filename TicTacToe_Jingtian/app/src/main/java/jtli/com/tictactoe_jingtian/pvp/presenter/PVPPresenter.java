package jtli.com.tictactoe_jingtian.pvp.presenter;

import android.os.Message;

import jtli.com.tictactoe_jingtian.pvp.view.PVPView;

/**
 * Created by Jingtian(Tansent).
 */

public interface PVPPresenter {

    /**
     * pass the view to the presenter
     * @param view view for tictactoe
     */
    void setView(PVPView view);

    /**
     * check the result after each step
     * @param posOnBoard 2d position on the board, such as {0,0}
     * @param position position in the array
     */
    void judgeMove(int[] posOnBoard, int position);

    /**
     * if there is a winner, or draw game, corresponding message should be sent
     * @param maxMove the maximum step a board can hold
     */
    void gameEnd( int maxMove);

    /**
     *  clear all the components and reset variables
     */
    void resetGame();
}
