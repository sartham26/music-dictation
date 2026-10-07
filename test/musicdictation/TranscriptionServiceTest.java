package musicdictation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TranscriptionServiceTest {
	@Test
	void transcribeIsNotImplemented() {
		TranscriptionService service = new TranscriptionService();
		assertThrows(UnsupportedOperationException.class,
			() -> service.transcribe(new AudioFile()));
	}

	@Test
	void validateResultIsNotImplemented() {
		TranscriptionService service = new TranscriptionService();
		assertThrows(UnsupportedOperationException.class,
			() -> service.validateResult(new Score()));
	}

	@Test
	void usesMock() {
		TranscriptionService service = mock(TranscriptionService.class);
		AudioFile audio = new AudioFile();
		service.transcribe(audio);
		verify(service).transcribe(audio);
	}

	@Test
	void usesStub() {
		TranscriptionService service = new TranscriptionService() {
			@Override
			public void transcribe(AudioFile audio) {
			}
		};
		assertDoesNotThrow(() -> service.transcribe(new AudioFile()));
	}
}
