package com.Maracas.gaem;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Logger;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    BitmapFont font;
    float accelX, accelY, accelZ;
    float vibratetimer;
    boolean shaked;

	@Override
	public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        //font = new BitmapFont();
        ////font.setColor(Color.BLUE);
        //font.setScale(5f);




        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/badaboom.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 120;
        font = generator.generateFont(parameter); // font size 12 pixels
        font.setColor(Color.PINK);
        generator.dispose();


        shaked = false;


        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }

	@Override
	public void render () {
        //update
        update();
        // -----

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(img, 0, 0);
        font.draw(batch, "X = " + accelX, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/1.5f);
        font.draw(batch, "Y = " + accelY, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2f);
        font.draw(batch, "Z = " + accelZ, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2.5f);

        //Gdx.app.debug("Vibrator", "X = " + accelX);
        ////Gdx.app.debug("Vibrator", "Y = " + accelY);
        //Gdx.app.debug("Vibrator", "Z = " + accelZ);

		batch.end();
	}

    public void update() {
        vibratetimer -= Gdx.graphics.getDeltaTime();
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        accelZ = Gdx.input.getAccelerometerZ();


        if (shaked == false && accelY >= 9) {
            shaked = true;
            Gdx.input.vibrate(250);
            Gdx.app.debug("COOLTAG", "MARACAS 1");
        }

        if (shaked == true && accelY <= -9) {
            shaked = false;
            Gdx.input.vibrate(250);
            Gdx.app.debug("COOLTAG", "MARACAS 2");
        }







         /*
        if (accelY >= 9 && vibratetimer <= 0) {
            Gdx.input.vibrate(1000);
            Gdx.app.debug("COOLTAG", "SHAKEY SHAKEY");
            vibratetimer = 1f;
        }
                   */

    }



}
