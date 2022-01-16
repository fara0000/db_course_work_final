package db.course_work.backend.dto.mappers;

import db.course_work.backend.dto.response.MemberDto;
import db.course_work.backend.entities.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public MemberDto convertMemberToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .role(member.getRole())
                .synagogue(member.getSynagogue())
                .events(member.getEvents())
                .build();
    }
}
