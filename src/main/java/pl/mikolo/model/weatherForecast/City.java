
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class City {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

}
