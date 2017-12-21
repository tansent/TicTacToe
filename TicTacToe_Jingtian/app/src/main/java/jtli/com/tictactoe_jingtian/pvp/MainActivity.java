package jtli.com.tictactoe_jingtian.pvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import jtli.com.tictactoe_jingtian.R;
import jtli.com.tictactoe_jingtian.pvp.adapter.BoardAdapter;
import jtli.com.tictactoe_jingtian.pvp.model.TicTacToe;
import jtli.com.tictactoe_jingtian.pvp.presenter.PVPPresenter;
import jtli.com.tictactoe_jingtian.pvp.view.PVPView;
import jtli.com.tictactoe_jingtian.root.App;

public class MainActivity extends AppCompatActivity implements PVPView{

    //ButterKnife
    @BindView(R.id.list_board)
    GridView list_board;
    @BindView(R.id.tv_winner)
    TextView tv_winner;
    @BindView(R.id.bt_again)
    Button bt_again;

    //Dagger2
    @Inject
    PVPPresenter presenter;
    @Inject
    TicTacToe ticTacToe;

    //we can define and dynamically set side length of the board
    protected static int SIDE_LENGTH = App.SIDE_LENGTH;

    //define a 2-dimensional arrays just for imitate the board positions
    private static int[][] positions = new int[SIDE_LENGTH*SIDE_LENGTH][2];

    protected static final int PLAYER_1 = 1;
    protected static final int PLAYER_2 = -1;
    protected static final int DRAW_GAME = 0;

    //Message and Handler to set views depending on the winning condition
    Message msg = Message.obtain();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PLAYER_1:
                    tv_winner.setText("PLAYER_1 WINS");
                    tv_winner.setVisibility(View.VISIBLE);
                    bt_again.setVisibility(View.VISIBLE);
                    break;
                case PLAYER_2:
                    tv_winner.setText("PLAYER_2 WINS");
                    tv_winner.setVisibility(View.VISIBLE);
                    bt_again.setVisibility(View.VISIBLE);
                    break;
                case DRAW_GAME:
                    tv_winner.setText("DRAW GAME");
                    tv_winner.setVisibility(View.VISIBLE);
                    bt_again.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inject dependency tree to this activity (Dagger2)
        ((App) getApplication()).getComponent().inject(this);
        //use ButterKnife to simplify the layout setup
        ButterKnife.bind(this);

        //initialize the board positions. For side_length ==3, the code below is equavelent to:
//    private static int[][] positions =
//                    {{0,0},{0,1},{0,2},
//                    {1,0},{1,1},{1,2},
//                    {2,0},{2,1},{2,2}};
        for(int i=0;i<SIDE_LENGTH * SIDE_LENGTH;i++){
            positions[i] = new int[]{i/SIDE_LENGTH, i%SIDE_LENGTH};
        }

        list_board.setNumColumns(SIDE_LENGTH); //set board size
        final BoardAdapter mAdapter = new BoardAdapter(this, positions);
        list_board.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @OnItemClick(R.id.list_board)
    public void onItemClicked(int position) {
        //get the position on board, such as {0,0}, the first position
        int[] posOnBoard = positions[position];
        //do nothing if a position is already clicked
        if(!list_board.getChildAt(position).isEnabled()) return;
        //check the result after each step
        presenter.judgeMove( posOnBoard, position);
        //if there is a winner, or draw game, corresponding message should be sent
        presenter.gameEnd( positions.length); // param: the maximum step a board can hold
    }

    @OnClick(R.id.bt_again)
    public void onViewClicked() {
        // clear all the components and reset variables
        presenter.resetGame();
    }


    /**
     * Remove resource to prevent potential Memory Leak
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
        msg = null;
    }

    @Override
    public void disableItem(int position) {
        list_board.getChildAt(position).setEnabled(false);
    }

    @Override
    public void setMove(int playerImage, int position) {
        ImageView item = (ImageView)((ViewGroup)list_board.getChildAt(position)).getChildAt(0);
        item.setImageResource(playerImage);
    }

    @Override
    public void sendEndGameMsg(int player) {
        msg.what = player;
        handler.sendMessage(msg);
    }

    @Override
    public void disableBoard() {
        list_board.setEnabled(false);
    }

    @Override
    public void enableBoard() {
        list_board.setEnabled(true);

    }

    @Override
    public void clearGridView() {
        for(int i=0;i<positions.length;i++){
            list_board.getChildAt(i).setEnabled(true);
            ImageView item = (ImageView)((ViewGroup)list_board.getChildAt(i)).getChildAt(0);
            item.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    public void clearNotification() {
        tv_winner.setText("");
        tv_winner.setVisibility(View.INVISIBLE);
        bt_again.setVisibility(View.INVISIBLE);
    }
}
