package db.course_work.backend.controllers;

import db.course_work.backend.dto.*;
import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Premise;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.entities.SynagogueAttribute;
import db.course_work.backend.services.SynagogueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/synagogue")
public class SynagogueController {
    private final SynagogueService synagogueService;

    private AttributeDto convertSynagogueAttributeToDto(SynagogueAttribute attribute) {
        return AttributeDto.builder()
                .name(attribute.getAttribute().getName())
                .description(attribute.getDescription())
                .build();
    }

    private PremiseDto convertPremiseToDto(Premise premise) {
        List<AttributeDto> attributeDtos = premise.getAttributes().stream().map(this::convertSynagogueAttributeToDto).collect(Collectors.toList());
        return PremiseDto.builder()
                .name(premise.getName())
                .synagogue(premise.getSynagogue())
                .attributes(attributeDtos)
                .build();
    }

    private SynagogueDto convertSynagogueToDto(Synagogue synagogue) {
        List<PremiseDto> premiseDtos = synagogue.getPremises().stream().map(this::convertPremiseToDto).collect(Collectors.toList());
        return SynagogueDto.builder()
                .id(synagogue.getId())
                .name(synagogue.getName())
                .size(synagogue.getSize())
                .architectureStyle(synagogue.getArchitectureStyle())
                .tradition(synagogue.getTradition())
                .premises(premiseDtos)
                .library(convertPremiseToDto(synagogue.getLibrary()))
                .build();
    }

    private MemberDto convertMemberToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .role(member.getRole())
                .synagogue(member.getSynagogue())
                .events(member.getEvents())
                .build();
    }

    private MemberList convertMembersToDto(List<Member> members) {
        return new MemberList(members.stream().map(this::convertMemberToDto).collect(Collectors.toList()));
    }


    public SynagogueController(SynagogueService synagogueService) {
        this.synagogueService = synagogueService;
    }

    @GetMapping("/my")
    public SynagogueDto getSynagogue() {
        Optional<Synagogue> synagogueOptional = synagogueService.getMemberSynagogue(1);
        if (synagogueOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error");
        return convertSynagogueToDto(synagogueOptional.get());
    }

    @GetMapping("/my/members")
    public MemberList getSynagogueMembers() {
        List<Member> members = synagogueService.getSynagogueMembers(1);
        return convertMembersToDto(members);
    }
}
