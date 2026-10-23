import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64ReaderAdapter extends Reader {
    private final Reader underlyingReader;

    public Base64ReaderAdapter(InputStream inputStream) {
        InputStream decodedStream = Base64.getDecoder().wrap(inputStream);
        this.underlyingReader = new InputStreamReader(decodedStream, StandardCharsets.UTF_8);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return underlyingReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        underlyingReader.close();
    }

    public static void main(String[] args) throws IOException {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        byte[] encodedBytes = Base64.getEncoder().encode(studentId.getBytes(StandardCharsets.UTF_8));
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedBytes);

        BufferedReader reader = new BufferedReader(new Base64ReaderAdapter(bais));
        String decodedString = reader.readLine();
        reader.close();

        System.out.println("Decoded string: " + decodedString);
        boolean isMatch = studentId.equals(decodedString);
        System.out.println("Assert decoded string == Student ID: " + isMatch);

        assert isMatch : "Decoded string does not match student ID";
    }
}
