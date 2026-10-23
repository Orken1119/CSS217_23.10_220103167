interface MediaPlayer {
    void play(String format, String file);
}

class AdvancedAudioPlayer {
    public void playMp4(String file) {
        System.out.println("Playing MP4 file: " + file);
    }

    public void playVlc(String file) {
        System.out.println("Playing VLC file: " + file);
    }
}

public class AudioAdapter implements MediaPlayer {
    private final AdvancedAudioPlayer advancedAudioPlayer;

    public AudioAdapter() {
        this.advancedAudioPlayer = new AdvancedAudioPlayer();
    }

    public AudioAdapter(AdvancedAudioPlayer advancedAudioPlayer) {
        this.advancedAudioPlayer = advancedAudioPlayer;
    }

    @Override
    public void play(String format, String file) {
        if ("mp4".equalsIgnoreCase(format)) {
            advancedAudioPlayer.playMp4(file);
        } else if ("vlc".equalsIgnoreCase(format)) {
            advancedAudioPlayer.playVlc(file);
        } else {
            throw new IllegalArgumentException("Unsupported media format: " + format);
        }
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        MediaPlayer player = new AudioAdapter();
        player.play("mp4", "test_" + studentId + ".mp4");
        player.play("vlc", "test_" + studentId + ".vlc");
    }
}
