package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {
    protected Long id;
    protected String title;
    protected float price;
    List<TagResponseDto> tags;

}
