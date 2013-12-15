package com.halfbeard.balljuggler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Vasily on 12/14/13.
 */
public class GameScreen implements Screen
{
    private Game myGame;
    private DuckyLab gameBody;

    public GameScreen(Game game)
    {
        myGame = game;
        gameBody = new DuckyLab();
        gameBody.create();
    }

    @Override
    public void render(float v)
    {
        gameBody.getScore();
        gameBody.render();

        if(gameBody.isGameOver())
        {
            myGame.setScreen(new GameOverScreen(myGame, gameBody.getScore()));
        }
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
