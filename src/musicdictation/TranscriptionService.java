package musicdictation;

public class TranscriptionService {

	private int maxLengthMinutes = 10;
	private int maxParts = 5;
	private int windowMinutes = 60;
	private int allowanceMinutes = 25;
	private ProvidedAIModel model;

	public TranscriptionService(ProvidedAIModel model) {
		this.model = java.util.Objects.requireNonNull(model);
	}

	public void checkAllowance(UserAccount account, AudioFile audio) {
		throw new UnsupportedOperationException();
	}

	public void filterNoise(AudioFile audio) {
		throw new UnsupportedOperationException();
	}

	public Score transcribe(AudioFile audio) {
		audio.validate();
		if (audio.getDurationMinutes() > maxLengthMinutes) {
			throw new IllegalArgumentException("Use a recording of 10 minutes or less.");
		}
		Score score;
		try {
			score = model.detectNotes(audio);
		} catch (RuntimeException error) {
			throw new IllegalStateException("The AI model could not transcribe the recording.", error);
		}
		validateResult(score);
		return score;
	}

	public void validateResult(Score score) {
		if (score == null) {
			throw new IllegalArgumentException("The AI model did not return a score.");
		}
		if (score.getPartCount() == 0) {
			throw new IllegalArgumentException("No music was found.");
		}
		if (score.getPartCount() > maxParts) {
			throw new IllegalArgumentException("Use a recording with five parts or fewer.");
		}
	}

}
