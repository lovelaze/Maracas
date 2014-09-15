package com.Maracas.gaem;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    BitmapFont font;
    float accelX, accelY, accelZ;
    float vibratetimer;

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
		batch.end();
	}

    public void update()
    {
        vibratetimer -= Gdx.graphics.getDeltaTime();
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        accelZ = Gdx.input.getAccelerometerZ();

        if (accelY >= 9 && vibratetimer <= 0) {
            Gdx.input.vibrate(1000);
            vibratetimer = 1f;
        }
    }



}
