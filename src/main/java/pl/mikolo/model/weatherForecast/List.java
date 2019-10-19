
package pl.mikolo.model.weatherForecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class List {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("snow")
    @Expose
    private Snow snow;
    @SerializedName("rain")
    @Expose
    private Rain rain;

}
