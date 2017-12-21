package jtli.com.tictactoe_jingtian.root;

import android.app.Application;

import jtli.com.tictactoe_jingtian.pvp.module.SideLengthModule;

/**
 * Created by Jingtian(Tansent).
 */

public class App extends Application {

    private ApplicationComponent component;
    //If we want, we can change this number to any integer to create board with different size dynamically
    public static int SIDE_LENGTH = 3;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .sideLengthModule(new SideLengthModule(SIDE_LENGTH))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
