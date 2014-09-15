package com.Maracas.gaem;

import com.badlogic.gdx.Gdx;

/**
 * Created by fredrik on 2014-09-16.
 * and sx
 */
public class ShakeItNow {

    private class ShakeState {
       private int oldstate;
       private int state;

        public ShakeState() {
            state = oldstate = 0;
        }

        public void reset() {
            state = 0;
        }

        public void setState(int state) {
            this.oldstate = this.state;
            this.state = state;
        }
    }

    private ShakeState stateX;
    private ShakeState stateY;
    private ShakeState stateZ;

    private float accelX, accelY, accelZ;

    public ShakeItNow() {
        stateX = new ShakeState();
        stateY = new ShakeState();
        stateZ = new ShakeState();

        accelX = accelY = accelZ = 0;
    }

    public void update() {
        check(stateX, Gdx.input.getAccelerometerX());
        check(stateY, Gdx.input.getAccelerometerY());
        check(stateZ, Gdx.input.getAccelerometerZ());
    }

    private void check(ShakeState shake, float accel) {
        if(accel >= 9.5) {
            if(shake.state != 1) {
                resetOthers(shake);
                shake.setState(1);
                if (shake.oldstate != 0) {
                    shake();
                }
            }
        }
        else if(accel <= -9.5) {
            if(shake.state != 2) {
                resetOthers(shake);
                shake.setState(2);
                if (shake.oldstate != 0) {
                    shake();
                }
            }
        }
    }
    
    private void resetOthers(ShakeState state) {
        if (!state.equals(stateX)) {
            stateX.reset();
        }
        if (!state.equals(stateY)) {
            stateY.reset();
        }
        if (!state.equals(stateZ)) {
            stateZ.reset();
        }
    }


    private void shake() {
        Gdx.input.vibrate(100);
    }
}