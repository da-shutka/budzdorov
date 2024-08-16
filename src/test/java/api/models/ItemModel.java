package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ItemModel {

    String sku, qty;
    @JsonProperty("added_at")
    String addedAt;
}
