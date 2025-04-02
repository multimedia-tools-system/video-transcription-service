package multimedia.tools.service.video_transcription_service.grpc.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AudioClientGrpcService
{

    private final TranscribeSenderGrpc.TranscribeSenderBlockingStub transcribeSenderBlockingStub;

    public AudioClientGrpcService()
    {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        this.transcribeSenderBlockingStub = TranscribeSenderGrpc.newBlockingStub(managedChannel);
    }

    public String sendTranscribe(String model, String fileStoragePath, String operation)
    {
        AudioTranscribeService.RequestTranscribe requestTranscribe = AudioTranscribeService.RequestTranscribe
                .newBuilder()
                .setModel(model)
                .setFileStoragePath(fileStoragePath)
                .setOperation(operation)
                .build();

        AudioTranscribeService.ResponseTranscribe responseTranscribe = transcribeSenderBlockingStub.sendTranscribe(requestTranscribe);

        return responseTranscribe.getTranscribe();
    }

}
