package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.repository.PriceFrameRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceFrameService implements IPriceFrameService {
    private final PriceFrameRepository priceFrameRepository;

    @Override
    public List<PriceFrame> getPriceFrames() {
        return priceFrameRepository.findAll();
    }

    @Override
    public List<PriceFrame> getPriceFramesbyRoute(Route route) {
        return priceFrameRepository.findAllByRoute(route);
    }

    @Override
    public Long savePriceFrame(PriceFrame priceFrame) {
        priceFrameRepository.save(priceFrame);
        if (priceFrame!=null){
            return priceFrame.getPriceFrameId();
        }
        return null;
    }

    @Override
    public PriceFrame getPriceFrameById(Long pfid) {
        return priceFrameRepository.getReferenceById(pfid);
    }
}
