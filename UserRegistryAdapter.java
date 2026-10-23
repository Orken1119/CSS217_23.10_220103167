record UserProfile(String studentId, String email, int accessLevel) {}

interface UserRegistry {
    UserProfile fetchUser();
}

class LegacyUserFeed {
    private final String rawRecord;

    public LegacyUserFeed() {
        this.rawRecord = "220103167#user@college.edu#4";
    }

    public LegacyUserFeed(String rawRecord) {
        this.rawRecord = rawRecord;
    }

    public String getRawRecord() {
        return rawRecord;
    }
}

public class UserRegistryAdapter implements UserRegistry {
    private final LegacyUserFeed legacyUserFeed;

    public UserRegistryAdapter(LegacyUserFeed legacyUserFeed) {
        this.legacyUserFeed = legacyUserFeed;
    }

    @Override
    public UserProfile fetchUser() {
        String raw = legacyUserFeed.getRawRecord();
        if (raw == null) {
            throw new IllegalArgumentException("Raw record cannot be null");
        }

        String[] parts = raw.split("#", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format. Expected STUDENT_ID#EMAIL#ACCESS_LEVEL, but received: " + raw);
        }

        String studentId = parts[0].trim();
        String email = parts[1].trim();

        if (studentId.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("studentId and email cannot be empty");
        }

        int accessLevel;
        try {
            accessLevel = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid accessLevel: " + parts[2], e);
        }

        return new UserProfile(studentId, email, accessLevel);
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        String rawRecord = studentId + "#user@college.edu#4";
        LegacyUserFeed feed = new LegacyUserFeed(rawRecord);
        UserRegistry adapter = new UserRegistryAdapter(feed);

        UserProfile profile = adapter.fetchUser();
        System.out.println(profile);
    }
}
