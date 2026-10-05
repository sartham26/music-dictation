package musicdictation;

public class Note {

	private String noteId;
	private String pitch;
	private String length;
	private String position;
	private String lyric;

	public void changePitch(String pitch) {
		throw new UnsupportedOperationException();
	}

	public void changeLength(String length) {
		throw new UnsupportedOperationException();
	}

	public void move(String position) {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}