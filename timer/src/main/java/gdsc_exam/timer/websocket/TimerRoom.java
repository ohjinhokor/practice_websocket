package gdsc_exam.timer.websocket;

import lombok.Getter;

@Getter
public class TimerRoom {

    //USER-PK
    private String userId;


    public static TimerRoom create(String userId) {
        TimerRoom timerRoom = new TimerRoom();
        timerRoom.userId = userId;
        return timerRoom;
    }
}
