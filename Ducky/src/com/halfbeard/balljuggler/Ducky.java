package com.halfbeard.balljuggler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Ducky
{
    private Texture texture = new Texture(Gdx.files.internal("duck_open.png"));
    private Sound squeakSound = Gdx.audio.newSound(Gdx.files.internal("squeak.wav"));
    public Body body;
    public Float radius;
    private Sprite sprite;
    public double xboost;
    public double yboost;

    public Ducky(World world, float x, float y)
    {
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        TextureRegion region = new TextureRegion(texture, 0, 0, 128, 128);

        sprite = new Sprite(region);
        sprite.setBounds(0, 0, 1.5f, 1.5f);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);

        radius = sprite.getWidth() / 2;

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 1.0f;
        body.createFixture(fixtureDef);

        circle.dispose();
        body.setUserData(this);
    }

    public void onCollision()
    {
    }

    public void bounce( Vector3 push )
    {
        xboost = Math.random() * 10;
        xboost -= 5;
        yboost = Math.random() * 10;
        body.setLinearVelocity( (float)xboost, (float)yboost );
    }
    
    public void squeak()
    {
        squeakSound.play(1.0f);
    }

    public void dispose()
    {
        texture.dispose();
        squeakSound.dispose();
    }

    public void render(SpriteBatch spriteBatch)
    {
        sprite.setPosition(body.getPosition().x - sprite.getOriginX(), body.getPosition().y - sprite.getOriginY());
        sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        sprite.draw(spriteBatch);
    }
}
