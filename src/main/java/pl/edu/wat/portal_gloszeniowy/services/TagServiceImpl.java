package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.repositories.TagRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public List<String> getAllTags() {
        return StreamSupport.stream(tagRepository.findAll().spliterator(), false)
                .map(Tag::getName)
                .collect(Collectors.toList());
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

    @Override
    public void deleteTag(Tag tag) {
        if(tag.getOfferList().size()==1)
            tagRepository.delete(tag);
    }

    @Override
    public void addOfferToTag(Offer offer, List<Tag> tags) {
        List<Offer> offers;
        for (Tag tag:
             tags) {
            offers=tag.getOfferList();
            offers.add(offer);
            tag.setOfferList(offers);
            tagRepository.save(tag);
        }
    }
}
