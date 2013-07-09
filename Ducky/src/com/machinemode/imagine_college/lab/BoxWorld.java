package com.machinemode.imagine_college.lab;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BoxWorld
{
    public static World CreateBoxWorld(float width, float height) {
        
        World world;

        Vector2 gravity = new Vector2(0.0f, -10.0f);
        world = new World(gravity, true);
        world.setContactListener(new ContactListener(){

            @Override
            public void beginContact(Contact contact)
            {
                Body a = contact.getFixtureA().getBody();
                Body b = contact.getFixtureB().getBody();
                
                if(a.getUserData() != null) {
                    Ducky duckyA = (Ducky)a.getUserData();
                    duckyA.squeak();
                }
                
                if(b.getUserData() != null) {
                    Ducky duckyB = (Ducky)b.getUserData();
                    duckyB.squeak();
                }
            }

            @Override
            public void endContact(Contact contact)
            {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold)
            {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse)
            {
                // TODO Auto-generated method stub
                
            }
            
        });  
        
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, -height/2)); 
        Body ground = world.createBody(groundBodyDef);
        
        BodyDef ceilingBodyDef = new BodyDef();
        ceilingBodyDef.position.set(new Vector2(0, height/2)); 
        Body ceiling = world.createBody(ceilingBodyDef);
        
        BodyDef leftBodyDef = new BodyDef();
        leftBodyDef.position.set(new Vector2(-width/2, 0)); 
        Body leftwall = world.createBody(leftBodyDef);
        
        BodyDef rightBodyDef = new BodyDef();
        rightBodyDef.position.set(new Vector2(width/2, 0)); 
        Body rightwall = world.createBody(rightBodyDef);
        
        PolygonShape horzShape = new PolygonShape();
        horzShape.setAsBox(width/2, 0.01f);
        ground.createFixture(horzShape, 0.0f);
        ceiling.createFixture(horzShape, 0.0f);
        horzShape.dispose();
        
        PolygonShape vertShape = new PolygonShape();
        vertShape.setAsBox((float) 0.01, height/2);
        leftwall.createFixture(vertShape, 0.0f);
        rightwall.createFixture(vertShape, 0.0f);
        vertShape.dispose();
        
        return world;
    }
}
