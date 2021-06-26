package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.repositories.TagRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Override
    public Tag addTag(String tagRequestDto, Offer offer) {
        Optional<Tag> tagDB = tagRepository.findByName(tagRequestDto);
        if(!tagDB.isPresent())
        {
            Tag newTag = new Tag();
            newTag.setName(tagRequestDto);
            tagRepository.save(newTag);
            return newTag;
        }
        return tagDB.get();
    }

    @Override
    public List<Tag> addTags(List<String> tagRequestDtoList, Offer offer) {
        List<Tag> tagList = new LinkedList<>();
        if(tagRequestDtoList!=null)
        {
            for (String tagRequestDto:
                    tagRequestDtoList) {
                tagList.add(addTag(tagRequestDto, offer));
            }
        }
        return tagList;
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        return null;
    }

    @Override
    public List<TagResponseDto> getOfferTags(Long offerId) {
        return null;
    }

    @Override
    public TagResponseDto getTag(Long tagId) {
        return null;
    }


    @Override
    public List<Offer> addTagOffer(Offer offer, Tag tag)
    {
        List<Offer> offers = tag.getOfferList();
        offers.add(offer);
        return offers;
    }
}
