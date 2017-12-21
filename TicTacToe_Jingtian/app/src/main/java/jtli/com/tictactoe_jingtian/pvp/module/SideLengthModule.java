package jtli.com.tictactoe_jingtian.pvp.module;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jingtian(Tansent).
 *
 * external dependency for SIDE_LENGTH
 */

@Module
public class SideLengthModule {

    private int SIDE_LENGTH;

    public SideLengthModule(int SIDE_LENGTH) {
        this.SIDE_LENGTH = SIDE_LENGTH;
    }

    @Provides
    public int provideSideLength(){
        return SIDE_LENGTH;
    }
}
