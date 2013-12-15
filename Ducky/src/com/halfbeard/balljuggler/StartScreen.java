package com.halfbeard.balljuggler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Vasily on 12/14/13.
 */
public class StartScreen implements Screen
{
    public  int WIDTH;
    public  int HEIGHT;
    private SpriteBatch spriteBatch;
    private Texture menu;
    private Game myGame;

    public StartScreen(Game game)
    {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        myGame = game;
        spriteBatch = new SpriteBatch();
        menu = new Texture(Gdx.files.internal("titlescreen.png"));
    }

    @Override
    public void render(float v)
    {
        Gdx.gl.glClearColor(0, 50, 50, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(menu, 0, 0, WIDTH, HEIGHT);
        spriteBatch.end();

        if(Gdx.input.justTouched())
            myGame.setScreen(new GameScreen(myGame));
    }

    @Override
    public void resize(int i, int y)
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
