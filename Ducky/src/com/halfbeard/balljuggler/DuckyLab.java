package com.halfbeard.balljuggler;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class DuckyLab extends Game
{
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Ducky ducky;
    private List<Ducky> duckyList;
    private World world;
    private Vector2 gravity = new Vector2(0f, -3f);
    private Box2DDebugRenderer debugRenderer;

    public int score;
    public boolean gameOver;

    @Override
    public void create()
    {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float viewWidth = (float)width * (10.0f / (float)height);
        float viewHeight = 10.0f;

        score = 0;
        gameOver = false;

        Gdx.app.log("Screen", width + " x " + height);
        world = BoxWorld.createBoxWorld(viewWidth, viewHeight, gravity, true, false, true, true, false);
        debugRenderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(viewWidth, viewHeight);
        camera.position.set(viewWidth / 2, viewHeight / 2, 0);

        batch = new SpriteBatch();

        ducky = new Ducky(world, 0, 5);
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        ducky.dispose();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        float height = Gdx.graphics.getHeight();

        if( ducky.body.getPosition().y < -10 )
        {
            gameOver = true;
        }
        else
        {
            ducky.render(batch);
            score +=1;
        }
        
        batch.end();

        if(Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            Gdx.app.log("Screen", "(x, y) = (" + touchPos.x + ", " + touchPos.y + ")");
            camera.unproject(touchPos);

            if(     Math.sqrt(
                    Math.pow( ( touchPos.x - ducky.body.getPosition().x), 2 ) +
                    Math.pow( ( touchPos.y - ducky.body.getPosition().y), 2 )) <
                    ( ducky.radius) )
            {
                ducky.bounce( touchPos );
                Gdx.app.log("Duck", "(x, y) = (" + ducky.body.getPosition().x + ", " + ducky.body.getPosition().y + ")");
            }

            Gdx.app.log("World", "(x, y) = (" + touchPos.x + ", " + touchPos.y + ")");
            
        }

        //debugRenderer.render(world, camera.combined);
        world.step(1 / 45f, 6, 2);
    }

    @Override
    public void resize(int width, int height)
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

    public int getScore()
    {
        return score;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

}
