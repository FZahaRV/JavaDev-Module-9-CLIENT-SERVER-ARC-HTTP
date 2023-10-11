import java.util.Scanner;
public class HttpImageStatusCli {
    private final HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
    private final HttpStatusChecker checker = new HttpStatusChecker();
    private boolean isValid(String in) {
        if(in.isEmpty()) {
            return false;
        }
        for (char c : in.toCharArray()) {
            if (Character.isLetter(c)) {
                return false;
            }
        }
        try {
            checker.getStatusImage(Integer.parseInt(in));
        } catch (RuntimeException e) {
            System.err.println("There is not image for HTTP status code (" + in + ")");
            return false;
        }
        return true;
    }
    public void askStatus() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter HTTP status code:");
        String code = in.nextLine();
        while (!isValid(code)) {
            System.out.println("Please enter valid number:");
            code = in.nextLine();
        }
        downloader.downloadStatusImage(Integer.parseInt(code));
        in.close();
    }
}
