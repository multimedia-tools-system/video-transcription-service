package multimedia.tools.service.video_transcription_service.grpc.protobuf;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class AudioServerGrpcService extends TranscribeSenderGrpc.TranscribeSenderImplBase
{

    @Override
    public void sendTranscribe(AudioTranscribeService.RequestTranscribe requestTranscribe,
                               StreamObserver<AudioTranscribeService.ResponseTranscribe> responseTranscribeStreamObserver)
    {
        AudioTranscribeService.ResponseTranscribe response = AudioTranscribeService.ResponseTranscribe
                .newBuilder()
                .setTranscribe(requestTranscribe.getModel() + " " + requestTranscribe.getFileStoragePath() + " " + requestTranscribe.getOperation())
                .build();

        responseTranscribeStreamObserver.onNext(response);
        responseTranscribeStreamObserver.onCompleted();
    }

}
