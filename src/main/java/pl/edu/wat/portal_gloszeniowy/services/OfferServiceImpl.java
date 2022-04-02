package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Optionals;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.*;
import pl.edu.wat.portal_gloszeniowy.dtos.mappers.TagMapper;
import pl.edu.wat.portal_gloszeniowy.entities.Comment;
import pl.edu.wat.portal_gloszeniowy.entities.Offer;
import pl.edu.wat.portal_gloszeniowy.entities.Tag;
import pl.edu.wat.portal_gloszeniowy.models.User;
import pl.edu.wat.portal_gloszeniowy.repositories.CommentRepository;
import pl.edu.wat.portal_gloszeniowy.repositories.OfferRepository;
import pl.edu.wat.portal_gloszeniowy.repositories.TagRepository;
import pl.edu.wat.portal_gloszeniowy.repositories.UserRepository;
import pl.edu.wat.portal_gloszeniowy.security.services.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final TagService tagService;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final CommentRepository commentRepository;



    @Override
    public OffersWithPagesCountResponseDto getAllOffers(Integer pageNumber) {
        TagMapper tagMapper = new TagMapper();
        List<OfferResponseDto> offers =  StreamSupport.stream(offerRepository.findAll(PageRequest.of(pageNumber, 9)).spliterator(), false)
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetais(), offer.getPhotos(), offer.getUser().getUsername(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
        int pagesCount = offers.size();
        return new OffersWithPagesCountResponseDto(offers, pagesCount/9);
    }

    @Override
    public OffersWithPagesCountResponseDto getUserOffers(String userName) {
        TagMapper tagMapper = new TagMapper();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        List<OfferResponseDto> offers = StreamSupport.stream(offerRepository.findByUser(user).spliterator(), false)
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetais(), offer.getPhotos(), offer.getUser().getUsername(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
        int pagesCount = offers.size();
        return new OffersWithPagesCountResponseDto(offers, pagesCount/9);
    }

    @Override
    public OffersWithPagesCountResponseDto getFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto, String userName) {
//        TagMapper tagMapper = new TagMapper();
//        List<OfferResponseDto> responseDtos = new LinkedList<>();
//        if(userName=="")
//            responseDtos= getAllOffers();
//        else
//            responseDtos= getUserOffers(userName);
//        List<OfferResponseDto> matchOffers =responseDtos;
//        for (OfferResponseDto offerResponse:
//             responseDtos) {
//            for (String stringTag:
//                 filterOptionsRequestDto.getTags()) {
//                matchOffers.removeIf(t -> !tagMapper.toStringTagList(t.getTags()).contains(stringTag));
//            }
//        }
//
//        System.out.println(matchOffers);
//        return matchOffers;
        return new OffersWithPagesCountResponseDto();
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
                    offer.getUser().getUsername(),
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
    public void addOffer(MultipartFile file, String title, float price, String details, List<String> tags, String userName) {
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
        offer.setUser(userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName)));
        offerRepository.save(offer);
        userDetailsService.addOfferToUser(userName, offer);
//        tagService.addOfferToTag(offer, offer.getTagList());

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
    public void updateOffer(MultipartFile file, String title, float price, String details, List<String> tags, Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if(offerDB.isPresent())
        {
            Offer offer = offerDB.get();
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
        else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public void deleteOffer(Long offerId, String userName) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        if(offerDB.isPresent())
        {
            Offer offer = offerDB.get();
            for (Comment comment:
                    offer.getComments()) {
                commentRepository.delete(comment);
            }

            for (Tag tag:
                 offer.getTagList()) {
                tagService.deleteTag(tag);
            }
            List<Offer> oofers = user.getOffers();
            oofers.remove(offer);
            user.setOffers(oofers);
            userRepository.save(user);
            offer.setTagList(null);
            offerRepository.delete(offer);
        }
        else throw new IllegalArgumentException("Bad id");
    }
}
