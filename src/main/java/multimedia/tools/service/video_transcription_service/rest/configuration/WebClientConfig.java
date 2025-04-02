package multimedia.tools.service.video_transcription_service.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static multimedia.tools.service.video_transcription_service.rest.util.WebServiceConstant.PYTHON_FLASK_AUDIO_REST_API_BASE_PATH;

@Configuration
public class WebClientConfig
{

    @Bean
    public WebClient webClient(WebClient.Builder builder)
    {
        return builder.baseUrl(PYTHON_FLASK_AUDIO_REST_API_BASE_PATH).build();
    }

}
