import java.io.*;

public class FileStats {
    private int numLines;
    private int numWords;
    private int numChars;
    private boolean skipWhiteSpace;
    private File f;

    // **You will need to complete the FileStats class's constructor, so you can create FileStats objects**
    public FileStats(File f, boolean skipWhiteSpace) throws FileNotFoundException {
        if (!f.exists()) {
            throw new FileNotFoundException(String.format("File: %s does not exist.", f.getName()));
        }
        this.f = f;
        this.skipWhiteSpace = skipWhiteSpace;
        this.numLines = 0;
        this.numWords = 0;
        this.numChars = 0;
    }

    // **You will need to call this method!!!**
    // This method takes a line and counts the number of words in that line.
    private static int countWords(String line) {
        // If the line variable is null or an empty string, return 0 for the word count.
        if (line == null || line.isEmpty()) { return 0; }
        // Otherwise, use the split method to break the String apart into words
        // i.e. separate the words by whitespace (\\s+ == RegEx for whitespace)
        String[] words = line.split("\\s+");
        // Words is now an Array of Strings (i.e. the words)
        // Thus, the number of elements in the array is the word count, so just return it.
        return words.length;
    }

    // **You will likely want to call this method!!!**
    private static String removeSpaces(String line) {
        if (line == null || line.isEmpty()) { return ""; }
        return String.join("", line.split("\\s+"));
        // How does this method remove whitespace?
        // line.split("\\s+") breaks the line apart into a word Array (kinda like a list of Strings)
        // String.join("", ...) Joins the word Array back together separated by ""

        // In other words, if the line value was "a b c", **split** on whitespace turns this into an array
        // ["a", "b", "c"] eliminating the spaces, and **join** puts the string back together separated by
        // an empty string (i.e. ""). Thus the string becomes "abc" with no more whitespace.
    }

    // **You will need to implement this method.**
    // This method should take a line and count the number of characters in that line.
    private static int countChars(String line, boolean skipWhiteSpace) {
        if (skipWhiteSpace) {
            line = removeSpaces(line);
        }
        return line.length();
    }

    // An overloaded method for the read method you will be writing!
    public void read(File f) throws FileNotFoundException, IOException {
        if( !f.exists() ) {
            throw new FileNotFoundException(String.format("File: %s does not exist.", f.getName()));
        }
        this.f = f;
        this.read();
    }

    // **You will need to implement this method.**
    // This method should use the java.io.BufferedReader class to efficiently read the File object line-by-line
    public void read() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                numLines++;
                numWords += countWords(line);
                numChars += countChars(line, skipWhiteSpace);
            }
        }
    }

    public int getNumLines() {
        return numLines;
    }

    public int getNumWords() {
        return numWords;
    }

    public int getNumChars() {
        return numChars;
    }

    public String getFileName() {
        return this.f.getAbsolutePath();
    }
}
