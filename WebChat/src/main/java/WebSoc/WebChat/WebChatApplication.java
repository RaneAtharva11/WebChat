package WebSoc.WebChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class WebChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebChatApplication.class, args);
	}

}
