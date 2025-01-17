package com.charity_org.demo.Models.Model;

import com.charity_org.demo.Enums.ClothesSize;
import com.charity_org.demo.Models.Model.ClothesType;
import com.charity_org.demo.Enums.Season;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothesDonnation  extends DonationType {
    @ManyToOne
    @JoinColumn(name = "clothes_type_id")
    private ClothesType clothingType;
    ClothesSize size;
    int quantity;
    Season season;
}
