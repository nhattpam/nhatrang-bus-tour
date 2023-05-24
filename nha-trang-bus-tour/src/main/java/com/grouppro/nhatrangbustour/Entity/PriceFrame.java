package com.grouppro.nhatrangbustour.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceFrame implements Serializable {
    private Long PriceFrameID;
    private String PriceFrameName;
    private Long RouteID;
}
