package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OfferRequestDto {
    protected String title;
    protected float price;
    List<TagRequestDto> tags;
}
