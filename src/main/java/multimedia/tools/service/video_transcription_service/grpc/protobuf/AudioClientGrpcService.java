package multimedia.tools.service.video_transcription_service.grpc.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AudioClientGrpcService
{

    private final TranscribeSenderGrpc.TranscribeSenderStub asyncStub;

    public AudioClientGrpcService()
    {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        this.asyncStub = TranscribeSenderGrpc.newStub(managedChannel);
    }

    public String sendTranscribe(String model, String fileStoragePath, String operation)
    {
        String result = "";

        AudioTranscribeService.RequestTranscribe requestTranscribe = AudioTranscribeService.RequestTranscribe
                .newBuilder()
                .setModel(model)
                .setFileStoragePath(fileStoragePath)
                .setOperation(operation)
                .build();

        CompletableFuture<List<AudioTranscribeService.ResponseTranscribe>> future = new CompletableFuture<>();
        List<AudioTranscribeService.ResponseTranscribe> progressList = new ArrayList<>();

        asyncStub.sendTranscribe(requestTranscribe, new StreamObserver<>() {
            @Override
            public void onNext(AudioTranscribeService.ResponseTranscribe responseTranscribe)
            {
                System.out.println("Status: " + responseTranscribe.getStatus()
                        + "; Progress: " + responseTranscribe.getProgress()
                        + "; Transcribe: " + responseTranscribe.getTranscribe());
                progressList.add(responseTranscribe);
            }

            @Override
            public void onError(Throwable throwable)
            {
                System.err.println("Error: " + throwable.getMessage());
                future.completeExceptionally(throwable);
            }

            @Override
            public void onCompleted()
            {
                System.out.println("Parsing completed!");
                future.complete(progressList);
            }
        });

        try
        {
            List<AudioTranscribeService.ResponseTranscribe> finalResults = future.get();
            System.out.println("Final progress list: " + finalResults);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

}
