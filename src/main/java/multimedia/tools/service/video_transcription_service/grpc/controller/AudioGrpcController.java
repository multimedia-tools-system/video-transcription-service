package multimedia.tools.service.video_transcription_service.grpc.controller;

import lombok.RequiredArgsConstructor;
import multimedia.tools.service.video_transcription_service.grpc.protobuf.AudioClientGrpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AudioGrpcController
{

    private final AudioClientGrpcService audioClientGrpcService;

    @GetMapping("/grpc/transcribe")
    public String sendTranscribe(@RequestParam("model") String model,
                                 @RequestParam("fileStoragePath") String fileStoragePath,
                                 @RequestParam("operation") String operation)
    {
        return audioClientGrpcService.sendTranscribe(model, fileStoragePath, operation);
    }

}
