package gdsc_exam.timer.websocket;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class TimerRoom {

    //USER-PK
    private int userId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public static TimerRoom create(int userId) {
        TimerRoom timerRoom = new TimerRoom();
        timerRoom.userId = userId;
        return timerRoom;
}

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, TimerService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, TimerService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
