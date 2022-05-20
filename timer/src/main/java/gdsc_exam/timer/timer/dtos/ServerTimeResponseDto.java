package gdsc_exam.timer.timer.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ServerTimeResponseDto {
    private LocalDateTime now;
}
