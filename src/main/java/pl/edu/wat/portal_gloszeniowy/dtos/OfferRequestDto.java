package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OfferRequestDto {
    private String title;
    private float price;
    private String detais;
    private String photos;
    List<TagRequestDto> tags;
}
