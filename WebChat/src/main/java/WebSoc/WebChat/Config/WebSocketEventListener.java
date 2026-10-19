package WebSoc.WebChat.Config;

import WebSoc.WebChat.Chat.ChatMessage;
import WebSoc.WebChat.Chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void HandleWebSocDisconnectUser(
          SessionDisconnectEvent event
    ){
       StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
       String Username = accessor.getSessionAttributes().get("username").toString();
       if(Username != null){
           log.info("User disconnected:{} ",Username);
           var chatmessage= ChatMessage.builder()
                            .type(MessageType.LEAVE)
                   .sender(Username)
                   .build();
           messagingTemplate.convertAndSend("/topic/public"+Username,chatmessage);
       }
    }
}
