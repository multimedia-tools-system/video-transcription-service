package multimedia.tools.service.video_transcription_service.util;

public class WebServiceConstant
{

    private WebServiceConstant() {}

    public static final String PYTHON_FLASK_AUDIO_REST_API_BASE_PATH = "http://localhost:5000";

    public static final String PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_ENDPOINT = "/transcribe/audio";

    public static final String PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_MODEL = "model";

    public static final String PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_FILE_STORAGE_PATH = "fileStoragePath";

    public static final String PYTHON_FLASK_AUDIO_REST_API_AUDIO_TRANSCRIBE_PARAM_FILE_STORAGE_OPERATION = "operation";

}
