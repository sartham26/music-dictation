package musicdictation;

public class TranscriptionService {

	private int maxLengthMinutes = 10;
	private int maxParts = 5;
	private int windowMinutes = 60;
	private int allowanceMinutes = 25;

	public void checkAllowance(UserAccount account, AudioFile audio) {
		throw new UnsupportedOperationException();
	}

	public void filterNoise(AudioFile audio) {
		throw new UnsupportedOperationException();
	}

	public void transcribe(AudioFile audio) {
		throw new UnsupportedOperationException();
	}

	public void validateResult(Score score) {
		throw new UnsupportedOperationException();
	}

}