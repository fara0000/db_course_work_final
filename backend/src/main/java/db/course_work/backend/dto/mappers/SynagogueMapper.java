package db.course_work.backend.dto.mappers;

import db.course_work.backend.dto.response.AttributeDto;
import db.course_work.backend.dto.response.PremiseDto;
import db.course_work.backend.dto.response.SynagogueDto;
import db.course_work.backend.dto.response.SynagogueList;
import db.course_work.backend.entities.Premise;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.entities.SynagogueAttribute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SynagogueMapper {
    private AttributeDto convertSynagogueAttributeToDto(SynagogueAttribute attribute) {
        return AttributeDto.builder()
                .name(attribute.getAttribute().getName())
                .description(attribute.getDescription())
                .build();
    }

    protected PremiseDto convertPremiseToDto(Premise premise) {
        List<AttributeDto> attributeDtos = premise.getAttributes().stream().map(this::convertSynagogueAttributeToDto).collect(Collectors.toList());
        return PremiseDto.builder()
                .id(premise.getId())
                .name(premise.getName())
                .synagogue(premise.getSynagogue())
                .attributes(attributeDtos)
                .build();
    }

    public SynagogueDto convertSynagogueToDto(Synagogue synagogue) {
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

    public SynagogueList convertSynagoguesToDto(List<Synagogue> synagogues) {
        List<SynagogueDto> dtos = synagogues.stream().map(this::convertSynagogueToDto).collect(Collectors.toList());
        return new SynagogueList(dtos);
    }
}
