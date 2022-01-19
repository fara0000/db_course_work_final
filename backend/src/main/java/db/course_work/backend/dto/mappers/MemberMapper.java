package db.course_work.backend.dto.mappers;

import db.course_work.backend.dto.response.MemberDto;
import db.course_work.backend.dto.response.MemberList;
import db.course_work.backend.entities.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper {
    private final SynagogueMapper synagogueMapper;

    public MemberMapper(SynagogueMapper synagogueMapper) {
        this.synagogueMapper = synagogueMapper;
    }

    public MemberDto convertMemberToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .role(member.getRole())
                .login(member.getLogin())
                .synagogue(synagogueMapper.convertSynagogueToDto(member.getSynagogue()))
                .build();
    }

    public MemberList convertMembersToDto(List<Member> members) {
        return new MemberList(members.stream().map(this::convertMemberToDto).collect(Collectors.toList()));
    }
}
