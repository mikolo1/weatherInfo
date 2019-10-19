
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherForecastModel {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<pl.mikolo.model.weatherForecast.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    private LocalDateTime uploadDateTime;
}
