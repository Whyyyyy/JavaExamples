package car;
import java.awt.Point;

public class Car {

	/**
	 * The number of tires for a {@code Car}.
	 * 
	 * This field is static since all {@code Car} objects will have the same
	 * number of tires.
	 */
	private static final int NUMBER_OF_TIRES = 4;

	/**
	 * The company that makes {@code this Car}.
	 */
	private final String COMPANY;

	/**
	 * The model of {@code this Car}.
	 */
	private final String MODEL;

	/**
	 * The number of seats in {@code this Car}.
	 */
	private int seats;

	/**
	 * The side which the steering wheel is on for {@code this Car}.
	 */
	private Side side;

	// engine

	/**
	 * The fuel capacity of {@code this Car} in liters.
	 */
	private final double FUEL_CAPACITY;

	/**
	 * The fuel efficiency of {@code this Car} in kilometers per liter.
	 */
	private final double FUEL_EFFICIENCY;

	/**
	 * The amount of fuel currently in the tank of {@code this Car} in liters.
	 */
	private double fuel;

	// motion
	/**
	 * The current position of {@code this Car} represented by a {@code Point}.
	 */
	private Point position;

	/**
	 * The current velocity of {@code this Car} in kilometers per hour. The
	 * position of the car will change by this amount each time {@code this Car}
	 * is moved.
	 */
	private Point velocity;

	/**
	 * The straight line distance, in kilometers, traveled per time, in hours,
	 * based on the velocity.
	 */
	private double minimumDistance;

	/**
	 * The maximum distance, in kilometers, that {@code this Car} can travel
	 * before running out of fuel.
	 */
	private double maximumDistance;

	/**
	 * Constructs a {@code new Car} using the specified paramters.
	 * 
	 * @param company
	 *            the company that makes {@code this Car}.
	 * @param model
	 *            the model of {@code this Car}.
	 * @param seats
	 *            the number of seats in {@code this Car}.
	 * @param fuelCapacity
	 *            the fuel capacity of {@code this Car} in liters.
	 * @param fuelEfficiency
	 *            the fuel efficiency of {@code this Car} in kilometers per
	 *            liter.
	 * @param fuelTankState
	 *            whether the tank is initially full or empty.
	 * @param initialPosition
	 *            the initial position of {@code this Car}.
	 */
	public Car(String company, String model, int seats, Side side, double fuelCapacity, double fuelEfficiency,
			FuelTankState fuelTankState, Point initialPosition) {
		this.COMPANY = company;
		this.MODEL = model;

		this.seats = seats;
		this.side = side;

		this.FUEL_CAPACITY = fuelCapacity;
		FUEL_EFFICIENCY = fuelEfficiency;

		if (fuelTankState == FuelTankState.FULL) {
			this.fuel = FUEL_CAPACITY;
		} else {
			this.fuel = 0;
		}

		this.position = initialPosition;

		// the initial velocity of a car will always be 0.
		this.velocity = new Point(0, 0);
		this.minimumDistance = 0;

		/*
		 * The fuel efficiency indicates the number of kilometers that can be
		 * covered for each liter of fuel.
		 * 
		 * The fuel indicates how many liters of fuel are currently in the tank
		 * of this car.
		 * 
		 * The maximum possible distance this car can travel is the product of
		 * the fuel remaining and the fuel efficiency.
		 */
		this.maximumDistance = fuel * FUEL_EFFICIENCY;

	}

	/**
	 * Changes the current position of the car based on the velocity, assuming
	 * there is enough fuel to do so.
	 */
	public void move() {

		// If the velocity of the car is 0, do not move anywhere.
		if (getMinimumDistance() > 0) {

			/*
			 * In order for the car to move, there must be enough fuel to move
			 * the required distance.
			 * 
			 * The fuel efficiency indicates the number of kilometers that can
			 * be covered for each liter of fuel.
			 * 
			 * The fuel indicates how many liters of fuel are currently in the
			 * tank of this car.
			 * 
			 * The maximum possible distance this car can travel is the product
			 * of the fuel remaining and the fuel efficiency.
			 */
			maximumDistance = fuel * FUEL_EFFICIENCY;

			// the car should only move if the maximum possible distance is
			// greater
			// than or equal to the minimum distance the car can travel.
			if (maximumDistance >= getMinimumDistance()) {

				position.setLocation(position.getX() + velocity.getX(), position.getY() + velocity.getY());

				/*
				 * The minimum distance indicates the minimum number of
				 * kilometers this car can travel based on the velocity.
				 * 
				 * The fuel indicates how many liters of fuel are currently in
				 * the tank of this car.
				 * 
				 */
				fuel -= getMinimumDistance() / FUEL_EFFICIENCY;
			} else {
				fuel = 0;
			}
		}
	}

	/**
	 * Sets the velocity of {@code this Car} to the specified velocity.
	 * 
	 * @param vel
	 *            the velocity set point.
	 */
	public void setVelocity(Point vel) {
		this.velocity = vel;

		// uses the Pythagorean theorem to determine the actual straight
		// distance traveled in kilometers per hour.
		this.minimumDistance = Math
				.sqrt(this.velocity.getX() * this.velocity.getX() + this.velocity.getY() * this.velocity.getY());
	}

	/**
	 * Gets the velocity of {@code this Car}.
	 * 
	 * @return the velocity.
	 */
	public Point getVelocity() {
		return this.velocity;
	}

	/**
	 * Gets the minimum straight line distance that {@code this Car} can travel
	 * in kilometers per hour.
	 * 
	 * @return the minimum distance that can be covered by {@code this Car}.
	 */
	public double getMinimumDistance() {
		return this.minimumDistance;
	}

	public String getCompany() {
		return COMPANY;
	}

	public String getModel() {
		return MODEL;
	}

	public int getSeats() {
		return seats;
	}

	public double getFuelCapacity() {
		return FUEL_CAPACITY;
	}

	public double getFuel() {
		return fuel;
	}

	public boolean isTankEmpty() {
		return fuel == 0;
	}

	public Side getSide() {
		return side;
	}

	public Point getPosition() {
		return position;
	}

	/**
	 * Refuels {@code this Car} with the specified amount of fuel in liters.
	 * Excess fuel is not added.
	 * 
	 * @param fuel
	 *            the amount of fuel to add to the tank in liters.
	 */
	public void refuel(double fuel) {
		if (this.fuel + fuel > FUEL_CAPACITY) {
			this.fuel = FUEL_CAPACITY;
		} else {
			this.fuel += fuel;
		}
	}

	/**
	 * Refuels {@code this Car} to until the tank is full.
	 */
	public void refuel() {
		this.fuel = FUEL_CAPACITY;
	}

	/**
	 * Gets the number of tires on a {@code Car}.
	 * 
	 * This method is static since all {@code Car} objects share this property.
	 * 
	 * @return the number of tires.
	 */
	public static int getNumberOfTires() {
		return NUMBER_OF_TIRES;
	}

	/**
	 * Outputs the company, mode, location, fuel and current position of
	 * {@code this Car}.
	 */
	public void getStats() {
		System.out.println(COMPANY + " " + MODEL);
		System.out.println("Fuel Remaining: " + fuel + " L");
		System.out.println("Position: (" + position.getX() + "," + position.getY() + ")");
		System.out.println();
	}
}
