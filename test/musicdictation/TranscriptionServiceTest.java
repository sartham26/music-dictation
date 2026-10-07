package musicdictation;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TranscriptionServiceTest {
	@Test
	void transcribesUsingMock() {
		ProvidedAIModel model = mock(ProvidedAIModel.class);
		AudioFile audio = new AudioFile("song.wav", "wav", 3, "song.wav");
		Score expected = new Score(List.of(new Part()));
		when(model.detectNotes(audio)).thenReturn(expected);
		assertSame(expected, new TranscriptionService(model).transcribe(audio));
		verify(model).detectNotes(audio);
	}

	@Test
	void acceptsTenMinutesUsingStub() {
		Score expected = new Score(List.of(new Part()));
		ProvidedAIModel model = new ProvidedAIModel() {
			@Override
			public Score detectNotes(AudioFile audio) {
				return expected;
			}
		};
		AudioFile audio = new AudioFile("song.wav", "wav", 10, "song.wav");
		assertSame(expected, new TranscriptionService(model).transcribe(audio));
	}

	@Test
	void rejectsLongRecording() {
		TranscriptionService service = new TranscriptionService(new ProvidedAIModel());
		AudioFile audio = new AudioFile("song.wav", "wav", 11, "song.wav");
		assertThrows(IllegalArgumentException.class, () -> service.transcribe(audio));
	}

	@Test
	void rejectsWrongFormat() {
		TranscriptionService service = new TranscriptionService(new ProvidedAIModel());
		AudioFile audio = new AudioFile("song.txt", "txt", 3, "song.txt");
		assertThrows(IllegalArgumentException.class, () -> service.transcribe(audio));
	}
}
