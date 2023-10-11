import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker checker = new HttpStatusChecker();
    public void downloadStatusImage(int code) {
        try {
            URL url = new URL(checker.getStatusImage(code));
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream("image.jpg");
            byte[] buf = new byte[2048];
            int length;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0 , length);
            }
            System.out.println("Image uploaded");
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
