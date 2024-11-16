package com.charity_org.demo.Models;

import com.charity_org.demo.Enums.Season;
import com.charity_org.demo.Enums.ClothesSize;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothesDonnation  extends DonationType {
String clothingType;
ClothesSize size;
int quantity;
Season season;

}
