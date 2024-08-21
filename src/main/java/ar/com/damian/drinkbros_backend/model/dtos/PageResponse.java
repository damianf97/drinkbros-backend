package ar.com.damian.drinkbros_backend.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> {
    private Long total;
    private Boolean hasPrevious;
    private Boolean hasNext;
    private PageableData pageable;
    private List<T> results;

    public PageResponse(Page<?> result, List<T> results) {
        this.results = results;
        this.total = result.getTotalElements();
        this.hasPrevious = result.hasPrevious();
        this.hasNext = result.hasNext();
        this.pageable = new PageableData(result.getPageable());
    }
}