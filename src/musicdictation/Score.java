package musicdictation;

import java.util.List;

public class Score {

	private String scoreId;
	private List<Part> parts;

	public Score(List<Part> parts) {
		this.parts = List.copyOf(parts);
	}

	public int getPartCount() {
		return parts.size();
	}

	public void showFullScore() {
		throw new UnsupportedOperationException();
	}

	public void showPart(Part part) {
		throw new UnsupportedOperationException();
	}

}
