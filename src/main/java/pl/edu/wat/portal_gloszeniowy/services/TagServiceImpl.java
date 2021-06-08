package pl.edu.wat.portal_gloszeniowy.services;

import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagResponseDto;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Override
    public void addTag(TagRequestDto tagRequestDto) {

    }

    @Override
    public void addTags(List<TagRequestDto> tagRequestDtoList) {

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
}
