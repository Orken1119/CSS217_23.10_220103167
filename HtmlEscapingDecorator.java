public class HtmlEscapingDecorator extends TextMessageDecorator {
    public HtmlEscapingDecorator(TextMessage textMessage) {
        super(textMessage);
    }

    @Override
    public String getContent() {
        String content = super.getContent();
        if (content == null) {
            return null;
        }
        return content.replace("&", "&amp;")
                      .replace("<", "&lt;")
                      .replace(">", "&gt;");
    }
}
