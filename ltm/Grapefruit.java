package ltm;
import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;
import java.awt.Color;
import java.util.Random;
import robocode.MoveCompleteCondition;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Grapefruit - a robot by Logan Moore
 */
public class Grapefruit extends AdvancedRobot
{
	boolean movingForward;
	private Random rand = new Random();

	/**
	 * run: Grapefruit's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setBodyColor(new Color(255, 255, 255));
		setGunColor(new Color(255, 150, 0));
		setRadarColor(new Color(0, 100, 100));
		setBulletColor(new Color(255, 255, 255));
		setScanColor(new Color(255, 200, 200));

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			this.setAhead(40000);
			this.movingForward = true;
			waitFor(new MoveCompleteCondition(this));
			if ( this.getX() < 10 || this.getX() > 500 || this.getY() < 10 || this.getY() > 500)
			{
				this.moveToCenter();
			}
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		double bulletBearing = e.getBearing();
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		double wallBearing = e.getBearing();
		double rand = (this.rand.nextInt(101) - 50);

		if (wallBearing > 0)
		{
			this.setTurnLeft(rand);
			waitFor(new TurnCompleteCondition(this));
		}
		else
		{
			this.setTurnRight(rand);
			waitFor(new TurnCompleteCondition(this));
		}
		this.reverse();
	}	
	
	/**
	 * Reverse the robot's direction.
	 */
	public void reverse() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}
}
