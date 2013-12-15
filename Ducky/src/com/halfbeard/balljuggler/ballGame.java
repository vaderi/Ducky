package com.halfbeard.balljuggler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Vasily on 12/14/13.
 */
public class ballGame extends Game
{
    public static int WIDTH;
    public static int HEIGHT;

    @Override
    public void create()
    {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        Texture.setEnforcePotImages(false);

        setScreen(new StartScreen(this));
    }

    public static int getWIDTH()
    {
        return WIDTH;
    }

    public static int getHEIGHT()
    {
        return HEIGHT;
    }
}
