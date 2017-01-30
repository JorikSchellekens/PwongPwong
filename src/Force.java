import processing.core.PVector;

/**
 * Created by Jorik on 26/01/2017.
 */
public class Force{
  PVector force;

  Force(float x, float y) {
    force = new PVector(x, y);
  }

  public void setForce(float x, float y) {
    force = new PVector(x, y);
  }

  public float getForceX() {return force.x;}
  public float getForceY() {return force.x;}

  void apply(PhysicsObject p) {
    p.velocity.add(force);
  }
}
