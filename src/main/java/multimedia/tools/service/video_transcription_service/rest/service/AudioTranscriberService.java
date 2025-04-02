package multimedia.tools.service.video_transcription_service.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static multimedia.tools.service.video_transcription_service.rest.util.WebServiceConstant.*;

@Service
@RequiredArgsConstructor
public class AudioTranscriberService {

    private final WebClient webClient;

    public Mono<String> fetchTranscription(String model, String fileStoragePath, String operation)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_ENDPOINT)
                        .queryParam(PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_MODEL, model)
                        .queryParam(PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_FILE_STORAGE_PATH, fileStoragePath)
                        .queryParam(PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_FILE_STORAGE_OPERATION, operation)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("Received: " + response))
                .doOnError(error -> System.err.println("Error: " + error.getMessage()));
    }

}
