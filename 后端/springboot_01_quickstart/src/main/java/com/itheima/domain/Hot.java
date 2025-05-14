package com.itheima.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hot {
    private int cityId;
    private String cityName;
    private String cityHot;
    private String cityScore;
}
