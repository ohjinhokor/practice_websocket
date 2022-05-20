package gdsc_exam.timer.timer;

import gdsc_exam.timer.timer.dtos.ServerTimeResponseDto;
import gdsc_exam.timer.timer.dtos.TimerStateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timer")
public class TimerController {
    private final TimerService timerService;

    // public TimerStateRequestDto timerState(@RequestBody TimerStateRequestDto req) {
    //     timerService.
    // }

    @GetMapping("/server_time")
    public ServerTimeResponseDto now() {
        return ServerTimeResponseDto.builder().now(LocalDateTime.now()).build();
    }
}
