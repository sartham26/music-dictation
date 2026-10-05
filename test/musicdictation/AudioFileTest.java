package musicdictation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AudioFileTest {
	@Test
	void acceptsMp3AndWav() {
		assertDoesNotThrow(() -> new AudioFile("song.mp3", "mp3", 3, "song.mp3").validate());
		assertDoesNotThrow(() -> new AudioFile("song.wav", "wav", 3, "song.wav").validate());
	}

	@Test
	void rejectsOtherFormats() {
		AudioFile audio = new AudioFile("song.txt", "txt", 3, "song.txt");
		IllegalArgumentException error = assertThrows(IllegalArgumentException.class, audio::validate);
		assertEquals("Use MP3 or WAV.", error.getMessage());
	}

	@Test
	void rejectsInvalidLength() {
		for (double length : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY}) {
			AudioFile audio = new AudioFile("song.wav", "wav", length, "song.wav");
			assertThrows(IllegalArgumentException.class, audio::validate);
		}
	}
}
