package test;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.LineString;

public class EchidistantPointsCalculator {
	
	public static List<Coordinate> findEchidistantPoints(LineString lineString, int resolution) {
		List<LineSegment> lineSegments = buildLineSegments(lineString);
		double lineStringLength = lineSegments.parallelStream().mapToDouble(l -> l.getLength()).sum();
		double stepSize = lineStringLength / resolution;
		List<Coordinate> coords = new ArrayList<>();
		double startingDistance = 0;
		for(LineSegment ls : lineSegments){
			SegmetData segmentData = processSegment(ls, stepSize, startingDistance);
			startingDistance = segmentData.getNextStartingDistance();
			coords.addAll(segmentData.getCoordinates());
		}
		return coords;
		
	}

	private static SegmetData processSegment(LineSegment segment, double stepSize,
			double currentDistance) {
		List<Coordinate> coords = new ArrayList<>();
		LineSegment remainingSegment = new LineSegment(segment);
		double remainingLength = remainingSegment.getLength();
		while(remainingLength >= currentDistance){
			Coordinate c = remainingSegment.pointAlong(currentDistance/remainingLength);
			coords.add(c);
			currentDistance = stepSize;
			remainingSegment = new LineSegment(c, remainingSegment.p1);
			remainingLength = remainingSegment.getLength();
		}
		return new SegmetData(coords, remainingLength);
	}

	private static List<LineSegment> buildLineSegments(LineString lineString) {
		Coordinate[] coords = lineString.getCoordinates();
		List<LineSegment> segments = new ArrayList<>();
		for(int i = 0; i < coords.length - 1; i++){
			segments.add(new LineSegment(coords[i], coords[i+1]));
		}
		return segments;
	}

}
