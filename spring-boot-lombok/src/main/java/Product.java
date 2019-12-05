import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Product {

    @NonNull
    private String name;
    private String description;
    private String type;
    private String category;
    private Double price;

}
