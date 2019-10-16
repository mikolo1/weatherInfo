
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Snow {

    @SerializedName("3h")
    @Expose
    public Double _3h;

}
