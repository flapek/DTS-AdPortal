package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.*;

import java.util.Date;
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
    private String userLogin;
    private Date date;
    List<TagResponseDto> tags;

}
