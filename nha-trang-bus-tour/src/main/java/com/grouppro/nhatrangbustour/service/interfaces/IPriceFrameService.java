package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;

import java.util.List;

public interface IPriceFrameService {
    List<PriceFrame> getPriceFrames();
    List<PriceFrame> getPriceFramesbyRoute(Route route);
    Long savePriceFrame(PriceFrame priceFrame);
    PriceFrame getPriceFrameById(Long pfid);
}
