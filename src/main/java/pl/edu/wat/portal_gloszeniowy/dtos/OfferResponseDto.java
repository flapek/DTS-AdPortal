package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferResponseDto {

    protected Long id;
    private String title;
    private float price;
    private String detais;
    private String photos;
    List<TagResponseDto> tags;

}
