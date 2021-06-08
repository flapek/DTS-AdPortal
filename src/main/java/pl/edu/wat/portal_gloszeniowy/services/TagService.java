package pl.edu.wat.portal_gloszeniowy.services;

import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;

import java.util.List;

public interface TagService {

    void addTag(TagRequestDto tagRequestDto);
    void addTags(List<TagRequestDto> tagRequestDtoList);

    List<TagResponseDto> getAllTags();
    List<TagResponseDto> getOfferTags(Long offerId);
    TagResponseDto getTag(Long tagId);
}
