package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.TagRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.mappers.TagMapper;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.repositories.OfferRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final TagService tagService;

    @Override
    public List<OfferResponseDto> getAllOffers() {
        TagMapper tagMapper = new TagMapper();
        return StreamSupport.stream(offerRepository.findAll().spliterator(), false)
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetais(), offer.getPhotos(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getTagOffers(TagRequestDto tagRequestDto) {
        return null;
    }

    @Override
    public OfferResponseDto getOfferDto(Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        TagMapper tagMapper = new TagMapper();
        if(offerDB.isPresent())
        {
            Offer offer = offerDB.get();
            return new OfferResponseDto(offer.getId(),
                    offer.getTitle(),
                    offer.getPrice(),
                    offer.getDetais(),
                    offer.getPhotos(),
                    tagMapper.toTagResponseDtoList(offer.getTagList()));
        }
        else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public Offer getOffer(Long offerId)
    {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if(offerDB.isPresent())
        {
            return offerDB.get();
        }
        else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public void addOffer(MultipartFile file, String title, float price, String details, List<String> tags) {
        Offer offer = new Offer();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        TagMapper tagMapper = new TagMapper();
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            offer.setPhotos(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        offer.setTitle(title);
        offer.setPrice(price);
        offer.setDetais(details);
        offer.setTagList(new LinkedList<Tag>());
        offer.setTagList(tagService.addTags(tags, offer));
        offerRepository.save(offer);
    }

    @Override
    public void addImage(MultipartFile file)
    {
        Offer offer = new Offer();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            offer.setPhotos(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        offerRepository.save(offer);
    }

    @Override
    public void updateOffer(OfferRequestDto offerRequestDto, Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if(offerDB.isPresent())
        {
            Offer offer = offerDB.get();
            offer.setTitle(offerRequestDto.getTitle());
            offer.setPrice(offerRequestDto.getPrice());
            offer.setDetais(offerRequestDto.getDetais());
            offerRepository.save(offer);
        }
        else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public void deleteOffer(Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if(offerDB.isPresent())
        {
            offerRepository.delete(offerDB.get());
        }
        else throw new IllegalArgumentException("Bad id");
    }
}
