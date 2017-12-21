package jtli.com.tictactoe_jingtian.pvp.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.tictactoe_jingtian.pvp.model.TicTacToe;
import jtli.com.tictactoe_jingtian.pvp.presenter.PVPPresenter;
import jtli.com.tictactoe_jingtian.pvp.presenter.PVPPresenterImpl;

/**
 * Created by Jingtian(Tansent).
 */

@Module(includes = SideLengthModule.class)
public class PVPModule {

    //to provide a TicTacToe instance
    @Singleton
    @Provides
    public TicTacToe provideTicTacToe(int SIDE_LENGTH){
        return new TicTacToe(SIDE_LENGTH);
    }

    //to provide a PVPPresenter instance
    @Singleton
    @Provides
    public PVPPresenter providePVPPresenter(TicTacToe ticTacToe){
        return new PVPPresenterImpl(ticTacToe);
    }

}
