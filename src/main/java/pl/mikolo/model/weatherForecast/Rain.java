
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Rain {

    @SerializedName("3h")
    @Expose
    private Double _3h;

}
