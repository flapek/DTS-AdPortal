package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OffersWithPagesCountResponseDto {
    private List<OfferResponseDto> offers;
    private Integer pagesCount;
}
