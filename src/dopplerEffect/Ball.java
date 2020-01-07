package dopplerEffect;

/**
 * Ball class - makes it easier to operate in AnimationPanel class.
 * @author Pawe³ Kowaleczko
 *
 */

public class Ball {
	private double radius, recoveryProbability, infectionProbability;
	private int x, y, state;

	public Ball() {
	}
	
	public Ball(double Radius, int X, int Y, int State, double RecoveryProbability, double InfectionProbability) {
		radius = Radius;
		x = X;
		y = Y;
		state=State;
		setRecoveryProbability(RecoveryProbability);
		setInfectionProbability(InfectionProbability);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getState() {
		return state;
	}

	public void setState(int State) {
		this.state = State;
	}

	public double getInfectionProbability() {
		return infectionProbability;
	}

	public void setInfectionProbability(double infectionProbability) {
		this.infectionProbability = infectionProbability;
	}

	public double getRecoveryProbability() {
		return recoveryProbability;
	}

	public void setRecoveryProbability(double recoveryProbability) {
		this.recoveryProbability = recoveryProbability;
	}
}
