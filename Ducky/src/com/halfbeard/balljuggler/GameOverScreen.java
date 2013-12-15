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
public class GameOverScreen implements Screen
{
    private SpriteBatch spriteBatch;
    private Texture gameOver;
    private Game myGame;
    private int finalScore;
    public  int WIDTH;
    public  int HEIGHT;

    public GameOverScreen(Game game, int score)
    {
        myGame = game;
        finalScore = score;
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
    }

    @Override
    public void render(float v)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(gameOver, 0, 0, WIDTH, HEIGHT);
        spriteBatch.end();

        if(Gdx.input.justTouched())
            myGame.setScreen(new StartScreen(myGame));
    }

    @Override
    public void resize(int i, int i2)
    {

    }

    @Override
    public void show()
    {
        spriteBatch = new SpriteBatch();
        gameOver = new Texture(Gdx.files.internal("gameover.png"));
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause() {

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
