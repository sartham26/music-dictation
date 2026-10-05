package musicdictation;

public class AudioFile {

	private String fileName;
	private String format;
	private double durationMinutes;
	private String originalPath;

	public AudioFile(String fileName, String format, double durationMinutes, String originalPath) {
		this.fileName = fileName;
		this.format = format;
		this.durationMinutes = durationMinutes;
		this.originalPath = originalPath;
	}

	public double getDurationMinutes() {
		return durationMinutes;
	}

	public void validate() {
		if (format == null || !(format.equalsIgnoreCase("mp3") || format.equalsIgnoreCase("wav"))) {
			throw new IllegalArgumentException("Use MP3 or WAV.");
		}
		if (!Double.isFinite(durationMinutes) || durationMinutes <= 0) {
			throw new IllegalArgumentException("The recording length must be positive.");
		}
	}

	public void play() {
		throw new UnsupportedOperationException();
	}

	public void pause() {
		throw new UnsupportedOperationException();
	}

	public void restart() {
		throw new UnsupportedOperationException();
	}

}
