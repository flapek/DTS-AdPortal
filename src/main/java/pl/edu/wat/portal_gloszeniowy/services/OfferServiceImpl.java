package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.portal_gloszeniowy.dtos.FilterOptionsRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.OffersWithPagesCountResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.enums.SortType;
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

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final TagService tagService;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final CommentRepository commentRepository;
    private final static int PAGES_PER_SITE = 9;


//    @Override
//    public OffersWithPagesCountResponseDto getAllOffers(Integer pageNumber) {
//        List<OfferResponseDto> offers = getAllOffersSorted(pageNumber, SortType.SORT_BY_TITLE_DESC);
//        return new OffersWithPagesCountResponseDto(offers, offerRepository.count());
//    }
//
//    @Override
//    public OffersWithPagesCountResponseDto getUserOffers(String userName, Integer pageNumber) {
//        TagMapper tagMapper = new TagMapper();
//        Collection<List<Tag>> tags = Collections.singleton(tagRepository.findAll());
//        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
//        List<OfferResponseDto> offers = offerRepository.findByUserAndTagListIn(user, tags, PageRequest.of(pageNumber, PAGES_PER_SITE)).stream()
//                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
//                        offer.getDetails(), offer.getPhotos(), offer.getUser().getUsername(), offer.getDate(),
//                        tagMapper.toTagResponseDtoList(offer.getTagList())))
//                .collect(Collectors.toList());
//        return new OffersWithPagesCountResponseDto(offers, offerRepository.count());
//    }

    @Override
    public OffersWithPagesCountResponseDto getFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto) {

        List<OfferResponseDto> offers = new LinkedList<>();
        SortType sortType = filterOptionsRequestDto.getSort() != null ? SortType.valueOf(filterOptionsRequestDto.getSort()) : SortType.SORT_BY_DATE_DESC;
        if (filterOptionsRequestDto.getTags() == null) {
            offers = getAllOffersSorted(filterOptionsRequestDto.getPageNumber(), sortType);
        } else {
            offers = getFilteredOffersSorted(filterOptionsRequestDto.getTags(), filterOptionsRequestDto.getPageNumber(), sortType);
            // TODO zmienic na filtrowanie zapytaniem po przejsciu na PostgreSQL
//            offers = getFilteredOffersSorted(Collections.singleton(tagRepository.findByNameIn(Arrays.asList(filterOptionsRequestDto.getTags()))), filterOptionsRequestDto.getPageNumber(), sortType);
        }
        return new OffersWithPagesCountResponseDto(offers, offerRepository.count()/PAGES_PER_SITE); // FIXME brana jest zawsze liczba wszystkich ofert
    }

    @Override
    public OffersWithPagesCountResponseDto getUserFilteredOffers(FilterOptionsRequestDto filterOptionsRequestDto, String userName) {
        List<OfferResponseDto> offers = new LinkedList<>();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        SortType sortType = filterOptionsRequestDto.getSort() != null ? SortType.valueOf(filterOptionsRequestDto.getSort()) : SortType.SORT_BY_DATE_DESC;
        if (filterOptionsRequestDto.getTags() == null) {
            offers = getUserOffersSorted(filterOptionsRequestDto.getPageNumber(), sortType, user);
        } else {
            offers = getUserFilteredOffersSorted(filterOptionsRequestDto.getTags(), filterOptionsRequestDto.getPageNumber(), sortType, user);
//            offers = getUserFilteredOffersSorted(Collections.singleton(tagRepository.findAll()), filterOptionsRequestDto.getPageNumber(), sortType, user);
        }
        return new OffersWithPagesCountResponseDto(offers, offerRepository.count()/PAGES_PER_SITE); // FIXME brana jest zawsze liczba wszystkich ofert
    }

    @Override
    public List<OfferResponseDto> getAllOffersSorted(Integer pageNumber, SortType sortType) {
        TagMapper tagMapper = new TagMapper();
        return offerRepository.findAll(createPageable(pageNumber, sortType)).stream()
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetails(), offer.getPhotos(), offer.getUser().getUsername(), offer.getDate(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getFilteredOffersSorted(Collection<List<Tag>> tags, Integer pageNumber, SortType sortType) {
        TagMapper tagMapper = new TagMapper();
        return offerRepository.findByTagListIn(tags, createPageable(pageNumber, sortType)).stream()
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetails(), offer.getPhotos(), offer.getUser().getUsername(), offer.getDate(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getFilteredOffersSorted(String[] tags, Integer pageNumber, SortType sortType) {
        TagMapper tagMapper = new TagMapper();
        List<OfferResponseDto> responseDtos = new LinkedList<>();
        responseDtos = getAllOffersSorted(pageNumber, sortType);
        ;
        List<OfferResponseDto> matchOffers = responseDtos;
        for (OfferResponseDto offerResponse :
                responseDtos) {
            for (String stringTag :
                    tags) {
                matchOffers.removeIf(t -> !tagMapper.toStringTagList(t.getTags()).contains(stringTag));
            }
        }
        return matchOffers;
    }

    @Override
    public List<OfferResponseDto> getUserOffersSorted(Integer pageNumber, SortType sortType, User user) {
        TagMapper tagMapper = new TagMapper();
        return offerRepository.findByUser(user, createPageable(pageNumber, sortType)).stream()
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetails(), offer.getPhotos(), offer.getUser().getUsername(), offer.getDate(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getUserFilteredOffersSorted(Collection<List<Tag>> tags, Integer pageNumber, SortType sortType, User user) {
        TagMapper tagMapper = new TagMapper();
        return offerRepository.findByUserAndTagListIn(user, tags, createPageable(pageNumber, sortType)).stream()
                .map(offer -> new OfferResponseDto(offer.getId(), offer.getTitle(), offer.getPrice(),
                        offer.getDetails(), offer.getPhotos(), offer.getUser().getUsername(), offer.getDate(),
                        tagMapper.toTagResponseDtoList(offer.getTagList())))
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferResponseDto> getUserFilteredOffersSorted(String[] tags, Integer pageNumber, SortType sortType, User user) {
        TagMapper tagMapper = new TagMapper();
        List<OfferResponseDto> responseDtos = new LinkedList<>();
        responseDtos = getUserOffersSorted(pageNumber, sortType, user);
        ;
        List<OfferResponseDto> matchOffers = responseDtos;
        for (OfferResponseDto offerResponse :
                responseDtos) {
            for (String stringTag :
                    tags) {
                matchOffers.removeIf(t -> !tagMapper.toStringTagList(t.getTags()).contains(stringTag));
            }
        }
        return matchOffers;
    }


    @Override
    public OfferResponseDto getOfferDto(Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        TagMapper tagMapper = new TagMapper();
        if (offerDB.isPresent()) {
            Offer offer = offerDB.get();
            return new OfferResponseDto(offer.getId(),
                    offer.getTitle(),
                    offer.getPrice(),
                    offer.getDetails(),
                    offer.getPhotos(),
                    offer.getUser().getUsername(), offer.getDate(),
                    tagMapper.toTagResponseDtoList(offer.getTagList()));
        } else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public Offer getOffer(Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if (offerDB.isPresent()) {
            return offerDB.get();
        } else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public void addOffer(MultipartFile file, String title, float price, String details, List<String> tags, String userName) {
        Offer offer = new Offer();
        if (file != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            TagMapper tagMapper = new TagMapper();
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                offer.setPhotos(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        offer.setTitle(title);
        offer.setPrice(price);
        offer.setDetails(details);
        offer.setTagList(new LinkedList<Tag>());
        offer.setTagList(tagService.addTags(tags, offer));
        offer.setUser(userRepository.findByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with username: " + userName)));
        offer.setDate(new Date());
        offerRepository.save(offer);
        userDetailsService.addOfferToUser(userName, offer);
        tagService.addOfferToTag(offer, offer.getTagList());

    }

    @Override
    public void addImage(MultipartFile file) {
        Offer offer = new Offer();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            offer.setPhotos(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        offerRepository.save(offer);
    }

    @Override
    public void updateOffer(MultipartFile file, String title, float price, String details, List<String> tags, Long offerId) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        if (offerDB.isPresent()) {
            Offer offer = offerDB.get();
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            TagMapper tagMapper = new TagMapper();
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                offer.setPhotos(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            offer.setTitle(title);
            offer.setPrice(price);
            offer.setDetails(details);
            offer.setTagList(new LinkedList<Tag>());
            offer.setTagList(tagService.addTags(tags, offer));
            offerRepository.save(offer);
        } else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public PageRequest createPageable(int pageNumber, SortType sortType) {
        switch (sortType) {
            case SORT_BY_PRICE_DESC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("price").descending());
            case SORT_BY_PRICE_ASC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("price").ascending());
            case SORT_BY_TITLE_DESC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("title").descending());
            case SORT_BY_TITLE_ASC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("title").ascending());
            case SORT_BY_DATE_ASC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("date").ascending());
            case SORT_BY_DATE_DESC:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("date").descending());
            default:
                return PageRequest.of(pageNumber, PAGES_PER_SITE, Sort.by("date").descending());
        }
    }

    @Override
    public void deleteOffer(Long offerId, String userName) {
        Optional<Offer> offerDB = offerRepository.findById(offerId);
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
        if (offerDB.isPresent()) {
            Offer offer = offerDB.get();
            for (Comment comment :
                    offer.getComments()) {
                commentRepository.delete(comment);
            }

            for (Tag tag :
                    offer.getTagList()) {
                tagService.deleteTag(tag);
            }
            List<Offer> oofers = user.getOffers();
            oofers.remove(offer);
            user.setOffers(oofers);
            userRepository.save(user);
            offer.setTagList(null);
            offerRepository.delete(offer);
        } else throw new IllegalArgumentException("Bad id");
    }

    @Override
    public void deleteComment(Long offerId, Long commentId) {
        Optional<Offer> offer = offerRepository.findById(offerId);
        offer.ifPresent(value -> value.getComments().removeIf(comment -> Objects.equals(comment.getId(), commentId)));
        offerRepository.save(offer.get());
    }
}
