public abstract class TextMessageDecorator implements TextMessage {
    protected final TextMessage textMessage;

    public TextMessageDecorator(TextMessage textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public String getContent() {
        return textMessage.getContent();
    }
}
