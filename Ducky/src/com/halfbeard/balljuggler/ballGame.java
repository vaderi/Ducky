package com.halfbeard.balljuggler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Created by Vasily on 12/14/13.
 */
public class ballGame extends Game
{
    @Override
    public void create()
    {
        setScreen(new StartScreen(this));
    }
}
