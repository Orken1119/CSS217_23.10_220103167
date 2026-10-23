public class WatermarkDecorator extends TextMessageDecorator {
    private final String studentId;

    public WatermarkDecorator(TextMessage textMessage) {
        this(textMessage, "220103167");
    }

    public WatermarkDecorator(TextMessage textMessage, String studentId) {
        super(textMessage);
        this.studentId = studentId;
    }

    @Override
    public String getContent() {
        return super.getContent() + " [Author: " + studentId + "]";
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        TextMessage original = new SimpleTextMessage("Alert: 5 < 10 & 2 > 1");
        TextMessage decorated = new WatermarkDecorator(new HtmlEscapingDecorator(original), studentId);

        System.out.println(decorated.getContent());
    }
}
