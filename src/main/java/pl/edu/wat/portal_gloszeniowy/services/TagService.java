package pl.edu.wat.portal_gloszeniowy.services;

import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;

import java.util.List;

public interface TagService {

    Tag addTag(String tagRequestDto, Offer offer);
    List<Tag> addTags(List<String> tagRequestDtoList, Offer offer);

    List<String> getAllTags();
    List<TagResponseDto> getOfferTags(Long offerId);
    TagResponseDto getTag(Long tagId);

    List<Offer> addTagOffer(Offer offer, Tag tag);

    void deleteTag(Tag tag);

    void addOfferToTag(Offer offer, List<Tag> tags);
}
