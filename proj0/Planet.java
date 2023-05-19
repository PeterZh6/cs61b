public class Planet{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
    private double dx, dy;
    private final static double G = 6.67e-11; /** Gravitational Constatn */
    public Planet(){
        xxPos = 0; yyPos = 0; xxVel = 0; yyVel = 0; mass = 0;
    }
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }


    /** public double calcDistance(Planet p2){
        dx = -(xxPos - p2.xxPos);
        dy = -(yyPos - p2.yyPos);
        double dx2 = dx * dx, dy2 = dy * dy;
        return Math.sqrt(dx2 + dy2);
    } */
    public double calcForceExertedBy(Planet p2){
        return G*mass*(p2.mass)/(calcDistance(p2)*calcDistance(p2));
    }
    public double calcForceExertedByX(Planet p2){
        return calcForceExertedBy(p2) * dx / calcDistance(p2);
    }
    public double calcForceExertedByY(Planet p2){
        return calcForceExertedBy(p2) * dy / calcDistance(p2);
    }
    private boolean equals(Planet target){
        if (target.xxPos == xxPos && target.yyPos == yyPos && target.mass == mass){
            return true;
        }
        return false;
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double sumNetForceX=0;
        for (Planet eachPlanet : allPlanets){
            if (equals(eachPlanet)){
                continue;
            }
            sumNetForceX += calcForceExertedByX(eachPlanet);
        }
        return sumNetForceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double sumNetForceY=0;
        for (Planet eachPlanet : allPlanets){
            if (equals(eachPlanet)){
                continue;
            }
            sumNetForceY += calcForceExertedByY(eachPlanet);
        }
        return sumNetForceY;
    }
    public void update(double dt, double fX, double fY){
        xxVel += dt * fX / mass;
        yyVel += dt * fY / mass;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}