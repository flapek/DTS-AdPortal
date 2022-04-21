package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.wat.portal_gloszeniowy.dtos.enums.SortType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterOptionsRequestDto {
    private String[] tags;
    private String sort;
    private int pageNumber;
}
