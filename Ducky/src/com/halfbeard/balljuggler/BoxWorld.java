package com.halfbeard.balljuggler;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.halfbeard.balljuggler.Ducky;


public class BoxWorld
{    
    private static World world;
    
    /**
     * Static contact listener.  Called every time a collision occurs.
     */
    private static ContactListener contactListener = new ContactListener()
    {
        /**
         * This event is called when two fixtures begin to overlap or start to collide. 
         * Usually we would check here if the body is to be removed or not.
         */
        @Override
        public void beginContact(Contact contact)
        {
            Body a = contact.getFixtureA().getBody();
            Body b = contact.getFixtureB().getBody();
            
            if(a.getUserData() != null) 
            {
                Ducky duckyA = (Ducky)a.getUserData();
                duckyA.onCollision();
            }
            
            if(b.getUserData() != null) 
            {
                Ducky duckyB = (Ducky)b.getUserData();
                duckyB.onCollision();
            }
        }

        /**
         * This event is called when the overlap between the two fixtures ends. 
         * This can be called when we delete a body/fixture as the contact would stop existing for that body/fixture.
         */
        @Override
        public void endContact(Contact contact)
        {
            // TODO Auto-generated method stub
        }

        /**
         * This is called after the collision is detected but before the response has been calculated. 
         * This is good if we don’t want the object to behave in normal way for 
         * e.g. maybe tunnel through a wall from one direction and not from another.
         */
        @Override
        public void preSolve(Contact contact, Manifold oldManifold)
        {
            contact.setEnabled(true);
        }

        /**
         * In this event we get the collision impulse results.  
         * But mostly if we would like to change the collision response we would do it in PreSolve.
         */
        @Override
        public void postSolve(Contact contact, ContactImpulse impulse)
        {
            // TODO Auto-generated method stub                
        }
    };
    
    
    /**
     * Create a "world" for the physics to act on
     * @param width Width of the world in units as defined by OrthographicCamera()
     * @param height Height of the world in units as defined by OrthographicCamera()
     * @param gravity Gravity vector (-9.8f is typical)
     * @param upperBound If true, apply a physical boundary to the upper part of the world (ceiling)
     * @param lowerBound If true, apply a physical boundary to the lower part of the world (floor)
     * @param leftBound If true, apply a physical boundary to the left part of the world (left wall)
     * @param rightBound If true, apply a physical boundary to the right part of the world (right wall)
     * @param ramp If true, create a rectangle somewhere in the world
     * @return A World object
     */
    public static World createBoxWorld( float width, 
                                        float height, 
                                        Vector2 gravity, 
                                        boolean upperBound, 
                                        boolean lowerBound, 
                                        boolean leftBound, 
                                        boolean rightBound,
                                        boolean ramp ) 
    {       
        world = new World(gravity, true);
        World.setVelocityThreshold(0.0f);      
        world.setContactListener(contactListener);  
        
        PolygonShape horzShape = new PolygonShape();
        horzShape.setAsBox(width/2, 0.01f);
        
        PolygonShape vertShape = new PolygonShape();
        vertShape.setAsBox((float) 0.01, height/2);
        
        if(lowerBound)
        {
            BodyDef groundBodyDef = new BodyDef();
            groundBodyDef.position.set(new Vector2(0, -height/2)); 
            world.createBody(groundBodyDef).createFixture(horzShape, 0.0f);
        }
        
        if(upperBound)
        {
            BodyDef ceilingBodyDef = new BodyDef();
            ceilingBodyDef.position.set(new Vector2(0, height/2)); 
            world.createBody(ceilingBodyDef).createFixture(horzShape, 0.0f);
        }
        
        if(leftBound)
        {
            BodyDef leftBodyDef = new BodyDef();
            leftBodyDef.position.set(new Vector2(-width/2, 0)); 
            world.createBody(leftBodyDef).createFixture(vertShape, 0.0f);
        }
        
        if(rightBound)
        {
            BodyDef rightBodyDef = new BodyDef();
            rightBodyDef.position.set(new Vector2(width/2, 0)); 
            world.createBody(rightBodyDef).createFixture(vertShape, 0.0f);
        }
        
        if(ramp)
        {
            Random randomValue = new Random();
            
            float hx = width/4;
            float hy = height/4;
            Vector2 center = new Vector2(0, 0);
            float angle = 6.283f;
            
            PolygonShape rampShape = new PolygonShape();
            rampShape.setAsBox(hx, hy);
            
            BodyDef rampBodyDef = new BodyDef();
            rampBodyDef.position.set(center);
            rampBodyDef.angle = angle;
            world.createBody(rampBodyDef).createFixture(rampShape, 0.0f);
            
            rampShape.dispose();
        }
        
        horzShape.dispose();
        vertShape.dispose();        
        return world;
    }
}
