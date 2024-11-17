package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.ClothesType;
import com.charity_org.demo.Enums.Season;
import com.charity_org.demo.Enums.ClothesSize;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClothesDonnation  extends DonationType {
ClothesType clothingType;
ClothesSize size;
int quantity;
Season season;

}
