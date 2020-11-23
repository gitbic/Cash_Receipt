package clevertec;

public interface DataIO {
    String read(String filePath);

    void write(String filePath, String text);
}
