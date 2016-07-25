package car;
import java.awt.Point;

public class Main {

	public static void main(String[] args) {

		Car car1 = new Car("Volkswagon", "Rabbit", 5, Side.LEFT, 75, 5, FuelTankState.FULL, new Point(0, 0));
		Car car2 = new Car("Toyota", "Corolla", 4, Side.LEFT, 55, 3, FuelTankState.FULL, new Point(6, 0));

		// use (6, 0) as the starting point of car 2 for crash.
		// use (-2, 1), as the starting point of car 2 for no crash.

		// set the velocity of the cars.
		car1.setVelocity(new Point(2, 2));
		car2.setVelocity(new Point(-1, 2));

		boolean crashed = false;

		// keep moving the cars until they crash, or both run out of fuel.
		while ((!car1.isTankEmpty() || !car2.isTankEmpty()) && !crashed) {
			if (car1.getPosition().equals(car2.getPosition())) {
				crashed = true;
			} else {
				car1.move();
				car2.move();
			}
		}

		System.out.println(crashed
				? "The cars crashed at point (" + car1.getPosition().getX() + ", " + car1.getPosition().getY() + ")."
				: "The cars did not crash.");
	}
}
