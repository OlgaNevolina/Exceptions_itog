import java.io.*;

public class FileManager {

    public String readFile(String fileName) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File not found or cannot be read.");
        }
        return content.toString();
    }

    public void writeFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Error writing to file.");
        }
    }

    public void copyFile(String sourceFile, String destinationFile) throws IOException {
        try (InputStream input = new FileInputStream(sourceFile);
             OutputStream output = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new IOException("Error copying file.");
        }
    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();

        try {
            String content = fileManager.readFile("input.txt");
            System.out.println("Content read from file: " + content);

            fileManager.writeFile("output.txt", content);
            System.out.println("Content written to file.");

            fileManager.copyFile("input.txt", "copy.txt");
            System.out.println("File copied successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}