package multimedia.tools.service.video_transcription_service.rest.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FilePathUtil
{

    public static void saveFileLocalStorage(MultipartFile file) throws IOException
    {
        Path destination = getFileLocalStorage(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    private static Path getFileLocalStorage(String filename) throws IOException
    {
        Path storageDirectory = Paths.get(FilePathConstant.LOCAL_FILE_STORAGE)
                .normalize()
                .toAbsolutePath();

        if (!Files.exists(storageDirectory))
        {
            Files.createDirectories(storageDirectory);
        }

        String originalFilename = Objects.requireNonNull(filename);
        String newFilename = originalFilename;
        String extension = "";

        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex != -1)
        {
            newFilename = originalFilename.substring(0, dotIndex);
            extension = originalFilename.substring(dotIndex);
        }

        Path destination = storageDirectory.resolve(originalFilename);
        int count = 1;

        while (Files.exists(destination))
        {
            destination = storageDirectory.resolve(newFilename + "(" + count + ")" + extension);
            count++;
        }

        return destination;
    }

}
