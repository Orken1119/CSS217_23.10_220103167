import java.io.IOException;
import java.sql.SQLException;

interface ConfigService {
    String readKey(String key);
}

class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

class FlakyConfig {
    public String getRaw(String k) throws IOException, SQLException {
        if ("error.io".equals(k)) {
            throw new IOException("Simulated I/O failure for key: " + k);
        }
        if ("error.sql".equals(k)) {
            throw new SQLException("Simulated SQL failure for key: " + k);
        }
        return "value_for_" + k;
    }
}

public class SafeConfigAdapter implements ConfigService {
    private final FlakyConfig flakyConfig;

    public SafeConfigAdapter(FlakyConfig flakyConfig) {
        this.flakyConfig = flakyConfig;
    }

    @Override
    public String readKey(String key) {
        try {
            return flakyConfig.getRaw(key);
        } catch (IOException | SQLException e) {
            throw new ConfigNotFoundException("Configuration read error for key: " + key, e);
        }
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        FlakyConfig flakyConfig = new FlakyConfig();
        ConfigService service = new SafeConfigAdapter(flakyConfig);

        String workingValue = service.readKey("app.timeout");
        System.out.println("Working key: " + workingValue);

        try {
            service.readKey("error.io");
        } catch (ConfigNotFoundException e) {
            System.out.println("Caught exception: " + e.getMessage());
            System.out.println("Underlying cause: " + e.getCause().getClass().getName());
        }
    }
}
