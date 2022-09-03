
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVFileStream {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\Users\\Admin\\IdeaProjects\\Group Assesment\\src\\resources","items.csv");

        Files.lines(path)
                .skip(1)
                . map( line -> {
                    String[] fields = line.split(",");
                    return new Items(fields[0],fields[1],Float.parseFloat(fields[2]),fields[3]);
                }).forEach(System.out::println);
    }
}
