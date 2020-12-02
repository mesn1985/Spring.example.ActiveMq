package lightmaze.spring.examples.SpringActiveMq.ActiveMqExample;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class RecievingJmsComponent {


    @JmsListener(destination = "IncomingMessageBox", containerFactory = "listenerFactory")
    public void receiveMessage(MessageDTO message){
        System.out.println(
                "Message Recieved {\n"+
                        message+
                        "\n}"
        );
    }

}
