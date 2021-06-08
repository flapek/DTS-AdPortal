package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TagResponseDto {
    private Long id;
    private String name;
}
