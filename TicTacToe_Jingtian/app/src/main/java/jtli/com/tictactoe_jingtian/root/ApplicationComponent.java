package jtli.com.tictactoe_jingtian.root;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.tictactoe_jingtian.pvp.MainActivity;
import jtli.com.tictactoe_jingtian.pvp.module.PVPModule;

/**
 * Created by Jingtian(Tansent).
 */

@Singleton
@Component(modules = {ApplicationModule.class, PVPModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);

}
