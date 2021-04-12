package util;

public class Vector {
    public double x;
    public double y;
    public double theta;

    public Vector(double x1, double y1){
        x = x1;
        y = y1;
        theta = Math.atan2(y, x);
    }

    public Vector(double angle, double len, angle unit) {
        if (unit.equals(Vector.angle.DEGREES)) {
            angle *= Math.PI/180;
        }
        this.x = len * Math.cos(angle);
        this.y = len * Math.sin(angle);
    }

    public Vector(double angle, double nx, double ny, angle unit) {
        double len = Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2));
        double extraAng = Math.atan2(ny, nx);
        Vector thisVec = new Vector(extraAng, len, Vector.angle.RADIANS);
        thisVec = thisVec.getRotatedVec(angle, unit);
        this.x = thisVec.getX();
        this.y = thisVec.getY();
    }

    public Vector getRotatedVec(double angle, angle type) {
        double ang = 0;
        if(type.equals(Vector.angle.DEGREES)) {
            ang = theta + Math.toRadians(angle);
        }else{
            ang = theta + angle;
        }
        double radius = Math.sqrt(x * x + y * y);
        return new Vector(Math.cos(ang) * radius, Math.sin(ang) * radius);
    }
    public void rotate(double angle, angle type){
        Vector rot = getRotatedVec(angle,type);
        this.x = rot.x;
        this.y = rot.y;
        this.theta = rot.theta;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getLen() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getAngle(angle type) {
        if (type == angle.RADIANS) {
            return Math.atan2(y,x);
        } else {
            return Math.atan2(y,x) * 180/Math.PI;
        }
    }

    public void setXY(double x1, double y1){
        x = x1;
        y = y1;
        theta = Math.atan2(y, x);
    }

    public String toString() {
        return "x: " + this.getX() + ", y: " + this.getY() + ", angle: " + this.getAngle(Vector.angle.DEGREES) + ", length: " + this.getLen();
    }


    public enum angle {
        DEGREES,
        RADIANS
    }

}
