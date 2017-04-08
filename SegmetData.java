package test;

import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;

public class SegmetData {

	private final List<Coordinate> coordinates;
	private final double nextStartingDistance;

	public SegmetData(List<Coordinate> coordinates, double nextStartingDistance) {
		super();
		this.coordinates = coordinates;
		this.nextStartingDistance = nextStartingDistance;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public double getNextStartingDistance() {
		return nextStartingDistance;
	}

	@Override
	public String toString() {
		return "SegmetData [coordinates=" + coordinates + ", nextStartingDistance=" + nextStartingDistance + "]";
	}
	
	

}
