package gdsc_exam.timer.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TimerService {

    private final ObjectMapper objectMapper;
    private Map<Integer, TimerRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<TimerRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public TimerRoom findRoomById(int roomId) {
        return chatRooms.get(roomId);
    }

    public TimerRoom createRoom(long userId) {
        TimerRoom chatRoom = TimerRoom.builder()
            .userId(userId)
            .build();
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}