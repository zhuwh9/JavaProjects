package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	public Stack<Location> trueWay = new Stack<Location>();
	boolean hasShown = false;//final message has been shown
	private int[] probability = {1,1,1,1};
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			Location currentLocation = this.getLocation();
			predictNextMove();
			ArrayList<Location> firstLocation = new ArrayList<Location>();
			firstLocation.add(currentLocation);
			trueWay.push(currentLocation);
			crossLocation.add(firstLocation);
		}
		if (stepCount == 341) {
			predictNextMove();
		}
		boolean willMove = canMove();
		if (isEnd == true) {
			setRightWay(trueWay);
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			stepCount++;
		} else {
			goBack();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int[] dir = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		for (int i = 0; i < 4; i++) {
			Location location = loc.getAdjacentLocation(dir[i]);
			if (gr.isValid(location)) {
				Actor actor = gr.get(location);
				// judge if it is the final position
				if ((actor instanceof Rock) && actor.getColor().equals(Color.RED)) {
					next = location;
					ArrayList<Location> tar = new ArrayList<Location>();
					tar.add(next);
					return tar;
				} else if (actor == null) {
					valid.add(location);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> validLocation = new ArrayList<Location>();
		Location nowLocation = this.getLocation();
		validLocation = getValid(nowLocation);
		if (validLocation.size() == 0) {
			return false;
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = this.getLocation();
		ArrayList<Location> chooseLocation = getValid(loc);
		int max = 0;
		int j = 0;
		int total = 0;
		int whichOne = 0;
		for (Location l : chooseLocation) {
			int direc = loc.getDirectionToward(l);
			if (probability[direc/90] > max) {
				max = probability[direc/90];
				j = (int)direc/90;
				// to store which one is the most possible location that has the largest direction probability
				whichOne = total;
			}
			total++;
		}
		if (chooseLocation.size() == 1) {
			next = chooseLocation.get(whichOne);
			probability[j]++;
		} else {
			int randomNumber = (int)(Math.random() * 10);
			if (randomNumber >= 0 && randomNumber < 7) {
				next = chooseLocation.get(whichOne);
				probability[j]++;
			} else {
				next = chooseLocation.get(randomNumber % chooseLocation.size());
				int direc = loc.getDirectionToward(next);
				j = direc / 90;
				probability[j]++;
			}
		}
		for (Location l : chooseLocation) {
			if (this.getDirection() == this.getLocation().getDirectionToward(l)) {
				next = l;
				int direc = loc.getDirectionToward(next);
				j = direc / 90;
				probability[j]++;
				break;
			}
		}
		if (gr.isValid(next)) {
			Actor actor = (Actor)gr.get(next);
			if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
				isEnd = true;
				setRightWay(trueWay);
			}
			moveTo(next);
			trueWay.push(next);
			int facing = loc.getDirectionToward(next);
			this.setDirection(facing);
			ArrayList<Location> temp = crossLocation.pop();
			temp.add(next);
			crossLocation.push(temp);
			ArrayList<Location> latest = new ArrayList<Location>();
			latest.add(next);
			crossLocation.push(latest);
		} else {
			removeSelfFromGrid();
		}
		System.out.println(stepCount);
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	public void goBack() {
		if (crossLocation.size() > 0) {
			crossLocation.pop();
			trueWay.pop();
			if (crossLocation.size() > 0) {
				Grid gr = getGrid();
				if (gr == null) {
					return;
				}
				ArrayList<Location> goBackLocations = crossLocation.peek();
				Location goBackLocation = goBackLocations.get(0);
				Location nowLocation = this.getLocation();
				int direc = nowLocation.getDirectionToward(goBackLocation);
				if (gr.isValid(goBackLocation)) {
					this.setDirection(direc);
					moveTo(goBackLocation);
					stepCount++;
				} else {
					removeSelfFromGrid();
				}
				if ((int)(direc/90) == 0) {
					probability[2]--;
				} else if ((int)(direc/90) == 1) {
					probability[3]--;
				} else if ((int)(direc/90) == 2) {
					probability[0]--;
				} else if ((int)(direc/90) == 3) {
					probability[1]--;
				}
				Flower flower = new Flower(getColor());
				flower.putSelfInGrid(gr, nowLocation);
			}
		}
	}
	
	public void predictNextMove() {
		Grid<Actor> gr = getGrid();
		ArrayList<Location> array = gr.getOccupiedLocations();
		
		for (Location a : array) {
			Actor actor = (Actor)gr.get(a);
			if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
				Location loc = this.getLocation();
				if (loc.getRow() < a.getRow()) {
					probability[2] = 6;
					probability[0] = 1;
				} else {
					probability[0] = 6;
					probability[2] = 1;
				}
				if (loc.getCol() < a.getCol()) {
					probability[1] = 6;
					probability[3] = 1;
				} else {
					probability[3] = 6;
					probability[1] = 1;
				}
				break;
			}
		}
	}
	
	public void setRightWay(Stack<Location> way) {
		for (Location w : way) {
			Grid gr = getGrid();
			Actor actor = (Actor)gr.get(w);
			actor.setColor(Color.GREEN);
		}
	}
}
