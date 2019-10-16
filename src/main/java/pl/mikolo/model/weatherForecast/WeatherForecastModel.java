
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherForecastModel {

    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public Integer message;
    @SerializedName("cnt")
    @Expose
    public Integer cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<pl.mikolo.model.weatherForecast.List> list = null;
    @SerializedName("city")
    @Expose
    public City city;

    private LocalDateTime uploadDateTime;

}
