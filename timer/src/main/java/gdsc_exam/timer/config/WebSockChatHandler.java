package gdsc_exam.timer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdsc_exam.timer.timer.dtos.TimerOperationRequestDto;
import gdsc_exam.timer.websocket.TimerRoom;
import gdsc_exam.timer.websocket.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final TimerService timerService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TimerOperationRequestDto timerOperation = objectMapper.readValue(message.getPayload(), TimerOperationRequestDto.class);
        int userId = timerOperation.getUserId();
        TimerRoom room = timerService.findRoomById(timerOperation.getUserId());

        if (room == null) {
            timerService.createRoom(userId);
        }

        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^");
        session.sendMessage(textMessage);
    }

}
