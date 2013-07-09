package com.machinemode.imagine_college.lab;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class DuckyLab implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Ducky ducky;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	
	@Override
	public void create() {		
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float viewWidth = (float)width * (10.0f/(float)height);
        float viewHeight = 10.0f;
           
        Gdx.app.log("Screen", width + " x " + height);
        world = BoxWorld.CreateBoxWorld(viewWidth, viewHeight);
        debugRenderer = new Box2DDebugRenderer();
        
        camera = new OrthographicCamera(viewWidth, viewHeight);
        camera.position.set(viewWidth/2, viewHeight/2, 0);  
        
        batch = new SpriteBatch();
   
        ducky = new Ducky(world, 0, 0);
	}

	@Override
	public void dispose() {
		batch.dispose();
		ducky.dispose();
	}

	@Override
	public void render() {		

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		ducky.render(batch);
		batch.end();
		
		if(Gdx.input.isTouched()) {
		    Vector3 touchPos = new Vector3();
		    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		    Gdx.app.log("Touch", "(x, y) = (" + touchPos.x + ", " + touchPos.y + ")");
		    camera.unproject(touchPos);
		    Gdx.app.log("Touch", "(x, y) = (" + touchPos.x + ", " + touchPos.y + ")");

		}
		

		debugRenderer.render(world, camera.combined);
		world.step(1/45f, 6, 2);
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
