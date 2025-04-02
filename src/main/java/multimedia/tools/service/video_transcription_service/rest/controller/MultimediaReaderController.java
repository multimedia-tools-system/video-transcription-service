package multimedia.tools.service.video_transcription_service.rest.controller;

import lombok.RequiredArgsConstructor;
import multimedia.tools.service.video_transcription_service.rest.service.AudioTranscriberService;
import multimedia.tools.service.video_transcription_service.rest.util.FilePathUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/multimedia")
@RequiredArgsConstructor
public class MultimediaReaderController
{

    private final AudioTranscriberService audioTranscriberService;

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping(value = "/audio/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
    {
        try
        {
            if (file.isEmpty())
            {
                throw new RuntimeException("Empty file");
            }

            FilePathUtil.saveFileLocalStorage(file);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Store exception");
        }

        return ResponseEntity.ok().body("File uploaded: " + file.getOriginalFilename());
    }

    @GetMapping(value = "/audio/transcribe")
    public Mono<String> fetchAudioTranscribe(
            @RequestParam("model") String model,
            @RequestParam("fileStoragePath") String fileStoragePath,
            @RequestParam("operation") String operation)
    {
        return audioTranscriberService.fetchTranscription(model, fileStoragePath, operation);
    }

}
