
package pl.mikolo.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

}
