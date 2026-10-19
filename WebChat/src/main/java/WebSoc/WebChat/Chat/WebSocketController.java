package WebSoc.WebChat.Chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping("/chat.sendmessage")
@SendTo("/topic/public")

public class WebSocketController {
    public ChatMessage Sendmessage(
        @Payload ChatMessage chatmessage
    ){
        return chatmessage;

    }

    @MessageMapping("/chat.adduser")
    @SendTo("/topic/public")
    public ChatMessage Adduser(
        @Payload ChatMessage chatmessage,
        SimpMessageHeaderAccessor headeracc
    ){
        headeracc.getSessionAttributes().put("username", chatmessage.getSender());
        return chatmessage;
    }
}
