package cracker.model;

import java.util.Arrays;
import java.util.List;

public class Path {
	private final List<Position> positions;

	public Path(Position... positions) {
		this.positions = Arrays.asList(positions);
	}

	public List<Position> getPositions() {
		return positions;
	}

	public Position get(int i) {
		return positions.get(i);
	}
}
