package pl.mikolo.model.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class City {
    public City(String name) {
        this.name = name;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("coord")
    @Expose
    @JoinColumn
    @ManyToOne
    private Coord coord;

}