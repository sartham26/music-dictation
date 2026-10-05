package MusicDictation;

public class Composition {

	private String compositionId;
	private String name;
	private java.time.LocalDateTime savedAt;

	public void save(UserAccount account) {
		throw new UnsupportedOperationException();
	}

	public void switchView(Part part) {
		throw new UnsupportedOperationException();
	}

	public void editNote(Note note, String pitch, String length) {
		throw new UnsupportedOperationException();
	}

	public void delete() {
		throw new UnsupportedOperationException();
	}

}