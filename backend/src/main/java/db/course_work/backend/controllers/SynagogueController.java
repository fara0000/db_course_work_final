package db.course_work.backend.controllers;

import db.course_work.backend.dto.mappers.MemberMapper;
import db.course_work.backend.dto.mappers.SynagogueMapper;
import db.course_work.backend.dto.response.*;
import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.services.SynagogueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/synagogues")
public class SynagogueController {
    private final SynagogueService synagogueService;
    private final SynagogueMapper synagogueMapper;
    private final MemberMapper memberMapper;

    public SynagogueController(SynagogueService synagogueService, SynagogueMapper synagogueMapper, MemberMapper memberMapper) {
        this.synagogueService = synagogueService;
        this.synagogueMapper = synagogueMapper;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/my")
    public SynagogueDto getSynagogue() {
        Optional<Synagogue> synagogueOptional = synagogueService.getMemberSynagogue(1);
        if (synagogueOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error");
        return synagogueMapper.convertSynagogueToDto(synagogueOptional.get());
    }

    @GetMapping("/my/members")
    public MemberList getSynagogueMembers() {
        Set<Member> members = synagogueService.getSynagogueMembers(1);
        return memberMapper.convertMembersToDto(new ArrayList<>(members));
    }

    @GetMapping("")
    public SynagogueList getAllSynagogues () {
        return synagogueMapper.convertSynagoguesToDto(synagogueService.getAllSynagogues());
    }
}
