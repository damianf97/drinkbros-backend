package ar.com.damian.drinkbros_backend.model.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
public class PageableData {
    private int page;
    private int size;
    private Long offset;

    public PageableData(Pageable pageable) {
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.offset = pageable.getOffset();
    }
}