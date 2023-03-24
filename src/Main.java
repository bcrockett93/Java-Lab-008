import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a file name to gather stats on (or 'Q' to quit): ");
            String fileName = scanner.nextLine();

            if (fileName.equalsIgnoreCase("Q")) {
                break;
            }

            String resourceFolderPath = "resources";
            Path filePath = Paths.get(resourceFolderPath, fileName);
            File file = filePath.toFile();

            System.out.print("Would you like to skip whitespace? (Y/N): ");
            String skipWhitespaceInput = scanner.nextLine();
            boolean skipWs = skipWhitespaceInput.equalsIgnoreCase("Y");

            try {
                FileStats fs = new FileStats(file, skipWs);
                fs.read();
                int numLines = fs.getNumLines();
                int numWords = fs.getNumWords();
                int numChars = fs.getNumChars();
                String filePathStr = fs.getFileName();
                System.out.printf("Stats: lines - %d, words - %d, chars - %d %s%n", numLines, numWords, numChars, filePathStr);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        scanner.close();
    }
}