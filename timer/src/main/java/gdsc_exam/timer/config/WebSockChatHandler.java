package gdsc_exam.timer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdsc_exam.timer.timer.dtos.TimerOperationRequestDto;
import gdsc_exam.timer.websocket.TimerRoom;
import gdsc_exam.timer.websocket.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static gdsc_exam.timer.timer.enums.TimerState.*;


@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final TimerService timerService;

    private Map<Long, TimerRoom> timerRooms = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TimerOperationRequestDto timerOperation = objectMapper.readValue(message.getPayload(), TimerOperationRequestDto.class);

        if (timerOperation.getTimerState() == ENTER) {
            long userId = timerOperation.getUserId();
            TimerRoom room = timerRooms.get(userId);

            if (room != null) {
                room.getSessions().add(session);
                Integer startTime = room.getStartTime();

                if (startTime != null) {
                    TextMessage textMessage = new TextMessage(room.getStartTime().toString());
                    session.sendMessage(textMessage);
                    return;
                }

                TextMessage textMessage = new TextMessage("타이머에 입장합니다.");
                session.sendMessage(textMessage);
                return;
            }

            timerRooms.put(userId, timerService.createRoom(userId));
            TimerRoom newRoom = timerRooms.get(userId);
            newRoom.getSessions().add(session);
            TextMessage textMessage = new TextMessage("타이머를 생성합니다");
            session.sendMessage(textMessage);
            return;
        }

        if (timerOperation.getTimerState() == START) {
            TimerRoom timerRoom = timerRooms.get(timerOperation.getUserId());
            for (WebSocketSession timerRoomSession : timerRoom.getSessions()) {
                System.out.println(timerRoom.getSessions().size());
                timerRoomSession.sendMessage(new TextMessage(timerOperation.getStartTime().toString()));
            }
        }
    }
}