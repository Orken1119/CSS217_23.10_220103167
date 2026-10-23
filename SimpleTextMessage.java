public class SimpleTextMessage implements TextMessage {
    private final String content;

    public SimpleTextMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        TextMessage original = new SimpleTextMessage("Alert: 5 < 10 & 2 > 1");
        TextMessage decorated = new WatermarkDecorator(new HtmlEscapingDecorator(original), studentId);

        System.out.println(decorated.getContent());
    }
}
