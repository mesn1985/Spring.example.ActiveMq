package lightmaze.spring.examples.SpringActiveMq.ActiveMqExample;

public class MessageDTO {

    private String text;

    public MessageDTO() {}

    public MessageDTO(String message) {
        this.text = message;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Message{text=%s}",getText());
    }
}
