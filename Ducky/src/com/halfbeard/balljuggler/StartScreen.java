package com.halfbeard.balljuggler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Vasily on 12/14/13.
 */
public class StartScreen implements Screen
{
    private SpriteBatch spriteBatch;
    private Texture menu;
    private Game myGame;

    public StartScreen(Game game)
    {
        myGame = game;
        spriteBatch = new SpriteBatch();
        menu = new Texture(Gdx.files.internal("menu.gif"));
    }

    @Override
    public void render(float v)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(menu, 0, 0);
        spriteBatch.end();

        if(Gdx.input.justTouched())
            myGame.setScreen(new GameScreen(myGame));
    }

    @Override
    public void resize(int i, int i2)
    {

    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
