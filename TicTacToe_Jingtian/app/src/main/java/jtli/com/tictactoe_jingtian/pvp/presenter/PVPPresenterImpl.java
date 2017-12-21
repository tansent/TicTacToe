package jtli.com.tictactoe_jingtian.pvp.presenter;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import jtli.com.tictactoe_jingtian.R;
import jtli.com.tictactoe_jingtian.pvp.model.TicTacToe;
import jtli.com.tictactoe_jingtian.pvp.view.PVPView;

/**
 * Created by Jingtian(Tansent).
 */

public class PVPPresenterImpl implements PVPPresenter {

    protected static final int PLAYER_1 = 1;
    protected static final int PLAYER_2 = -1;
    protected static final int DRAW_GAME = 0;
    int turn = 1;
    int count = 0;
    boolean finished;
    int result = 0;
    TicTacToe ticTacToe;
    PVPView view;

    public PVPPresenterImpl(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }

    @Override
    public void setView(PVPView view) {
        this.view = view;
    }

    @Override
    public void judgeMove(int[] posOnBoard,int position) {
        if (turn == 1){
            result = ticTacToe.move(posOnBoard[0],posOnBoard[1], PLAYER_1);
            turn = -1;

            view.disableItem(position);
            view.setMove(R.drawable.tic_tac_toe_o, position);
        }else{
            result = ticTacToe.move(posOnBoard[0],posOnBoard[1], PLAYER_2);
            turn = 1;

            view.disableItem(position);
            view.setMove(R.drawable.tic_tac_toe_x, position);
        }
        count++;
    }

    @Override
    public void gameEnd(int maxMove) {
        if(result == PLAYER_1){
            view.sendEndGameMsg(PLAYER_1);
            finished= true;
        }else if(result == PLAYER_2){
            view.sendEndGameMsg(PLAYER_2);
            finished= true;
        }else if(result == 0 && count == maxMove){
            view.sendEndGameMsg(DRAW_GAME);
            finished= true;
        }

        if (finished){
            view.disableBoard();
        }
    }

    @Override
    public void resetGame() {
        view.enableBoard();
        view.clearGridView();

        finished = false;
        count = 0;
        view.clearNotification();
//      turn *= -1; //same player goes first
        ticTacToe.reset();
    }

}
