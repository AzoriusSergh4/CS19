package es.urjc.jjve.spaceinvaders.controllers;

public class JoystickController {

    public int shipMovement(float x, float y) {
        float epsilon = 0.01f;
        if (Math.abs(x - 0) > epsilon && (Math.abs(y - 0) > epsilon)) {
            final double angle = Math.atan2(y, x);
            final double halfPI = Math.PI / 2;
            final double quarterPI = Math.PI / 4;
            final double eighthPI = Math.PI / 8;
            if (angle >= -eighthPI && angle < eighthPI) {
                return 2;
            } else if (angle >= eighthPI && angle < (quarterPI + eighthPI)) {
                return 8;
            } else if (angle >= (quarterPI + eighthPI) && angle < (halfPI + eighthPI)) {
                return 4;
            } else if (angle >= (halfPI + eighthPI) && angle < (Math.PI - eighthPI)) {
                return 7;
            } else if (angle >= (Math.PI - eighthPI) && angle <= Math.PI) {
                return 1;
            } else if (angle >= -Math.PI && angle < (-Math.PI + eighthPI)) {
                return 1;
            } else if (angle >= (-Math.PI + eighthPI) && angle < (-halfPI - eighthPI)) {
                return 5;
            } else if (angle >= (-halfPI - eighthPI) && angle < (-halfPI + eighthPI)) {
                return 3;
            } else if (angle >= (-halfPI + eighthPI) && angle < (-eighthPI)) {
                return 6;
            } else {
                return 0;
            }
        } else {
            return 0;
        }

    }
}
