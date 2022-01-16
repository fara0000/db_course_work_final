package db.course_work.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MeetingList {
    private List<MeetingDto> meetings;
}
