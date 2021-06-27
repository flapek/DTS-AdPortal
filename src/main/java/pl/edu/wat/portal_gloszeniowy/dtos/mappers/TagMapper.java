package pl.edu.wat.portal_gloszeniowy.dtos.mappers;

import lombok.AllArgsConstructor;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.repositories.TagRepository;
import pl.edu.wat.portal_gloszeniowy.services.TagService;

import java.util.LinkedList;
import java.util.List;

public class TagMapper {

    public TagResponseDto toTagResponseDto(Tag tag)
    {
        return new TagResponseDto(tag.getId(), tag.getName());
    }

    public List<TagResponseDto> toTagResponseDtoList(List<Tag> tagList)
    {
        List<TagResponseDto> tagResponseDtoList = new LinkedList<>();
        for (Tag tag:
             tagList) {
            tagResponseDtoList.add(toTagResponseDto(tag));
        }
        return tagResponseDtoList;
    }

    public List<String> toStringTagList(List<TagResponseDto> tagResponseDtoList)
    {
        List<String> stringList = new LinkedList<>();
        for (TagResponseDto tagresponse:
              tagResponseDtoList) {
            stringList.add(tagresponse.getName());
        }
        return stringList;
    }

}
