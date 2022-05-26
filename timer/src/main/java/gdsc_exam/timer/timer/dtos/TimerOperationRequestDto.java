package gdsc_exam.timer.timer.dtos;

import gdsc_exam.timer.timer.enums.TimerState;
import lombok.Getter;

@Getter
public class TimerOperationRequestDto {
    private TimerState timerState;

    private Integer startTime;

    private Integer totalTime;

    private long userId;
}
