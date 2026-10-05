package musicdictation;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TranscriptionServiceTest {
	@Test
	void sendsAudioToModelUsingMock() {
		ProvidedAIModel model = mock(ProvidedAIModel.class);
		AudioFile audio = audio(3);
		Score expected = score(1);
		when(model.detectNotes(audio)).thenReturn(expected);
		assertSame(expected, new TranscriptionService(model).transcribe(audio));
		verify(model).detectNotes(audio);
		verifyNoMoreInteractions(model);
	}

	@Test
	void acceptsExactlyTenMinutesUsingStub() {
		Score expected = score(5);
		TranscriptionService service = new TranscriptionService(new AIModelStub(expected));
		assertSame(expected, service.transcribe(audio(10)));
	}

	@Test
	void rejectsOverTenMinutesBeforeCallingModel() {
		ProvidedAIModel model = mock(ProvidedAIModel.class);
		TranscriptionService service = new TranscriptionService(model);
		IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
			() -> service.transcribe(audio(10.01)));
		assertEquals("Use a recording of 10 minutes or less.", error.getMessage());
		verifyNoInteractions(model);
	}

	@Test
	void rejectsBadFormatBeforeCallingModel() {
		ProvidedAIModel model = mock(ProvidedAIModel.class);
		TranscriptionService service = new TranscriptionService(model);
		AudioFile audio = new AudioFile("song.txt", "txt", 3, "song.txt");
		assertThrows(IllegalArgumentException.class, () -> service.transcribe(audio));
		verifyNoInteractions(model);
	}

	@Test
	void rejectsNoMusicUsingStub() {
		TranscriptionService service = new TranscriptionService(new AIModelStub(score(0)));
		IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
			() -> service.transcribe(audio(3)));
		assertEquals("No music was found.", error.getMessage());
	}

	@Test
	void rejectsMoreThanFivePartsUsingStub() {
		TranscriptionService service = new TranscriptionService(new AIModelStub(score(6)));
		IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
			() -> service.transcribe(audio(3)));
		assertEquals("Use a recording with five parts or fewer.", error.getMessage());
	}

	@Test
	void rejectsMissingModelResultUsingStub() {
		TranscriptionService service = new TranscriptionService(new AIModelStub(null));
		IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
			() -> service.transcribe(audio(3)));
		assertEquals("The AI model did not return a score.", error.getMessage());
	}

	@Test
	void reportsModelFailureUsingMock() {
		ProvidedAIModel model = mock(ProvidedAIModel.class);
		AudioFile audio = audio(3);
		RuntimeException failure = new RuntimeException("Model failed");
		when(model.detectNotes(audio)).thenThrow(failure);
		IllegalStateException error = assertThrows(IllegalStateException.class,
			() -> new TranscriptionService(model).transcribe(audio));
		assertEquals("The AI model could not transcribe the recording.", error.getMessage());
		assertSame(failure, error.getCause());
	}

	private static AudioFile audio(double minutes) {
		return new AudioFile("song.wav", "wav", minutes, "song.wav");
	}

	private static Score score(int count) {
		return new Score(Collections.nCopies(count, new Part()));
	}

	private static class AIModelStub extends ProvidedAIModel {
		private final Score result;

		AIModelStub(Score result) {
			this.result = result;
		}

		@Override
		public Score detectNotes(AudioFile audio) {
			return result;
		}
	}
}
