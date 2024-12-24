package com.jitu.lead_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitu.lead_management.entity.Itinerary;
import com.jitu.lead_management.exception.ItineraryNotFoundException;
import com.jitu.lead_management.model.ItineraryModificationModel;
import com.jitu.lead_management.model.ItineraryViewModel;
import com.jitu.lead_management.repository.ItineraryRepository;
import com.jitu.lead_management.utils.ItineraryUtils;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public void createItinerary(ItineraryModificationModel itineraryModel) {
        Itinerary itinerary = new Itinerary(itineraryModel);
        itineraryRepository.save(itinerary);
    }

    @Override
    public ItineraryViewModel findById(String itineraryId) {
        int intItineraryId = ItineraryUtils.resolveItineraryId(itineraryId);
        Optional<Itinerary> itinerary = itineraryRepository.findById(intItineraryId);
        if (itinerary.isPresent()) {
            return new ItineraryViewModel(itinerary.get());
        }
        throw new ItineraryNotFoundException("Error: Itinerary not found");
    }

    @Override
    public List<ItineraryViewModel> findAll() {
        List<Itinerary> itineraryList = itineraryRepository.findAll();
        return itineraryList.stream().map(ItineraryViewModel::new).collect(Collectors.toList());
    }

    @Override
    public void update(String itineraryId, ItineraryModificationModel itineraryModel) {
        int intItineraryId = ItineraryUtils.resolveItineraryId(itineraryId);

        Itinerary itinerary = itineraryRepository.findById(intItineraryId)
                .orElseThrow(() -> new ItineraryNotFoundException("Error: Itinerary not found"));

        itinerary = ItineraryUtils.mapItineraryUpdate(itinerary, itineraryModel);

        itineraryRepository.save(itinerary);
    }

    @Override
    public void deleteByIds(List<String> itineraryIds) {
        List<Integer> intItineraryIds = itineraryIds.stream().map(ItineraryUtils::resolveItineraryId)
                .collect(Collectors.toList());
        itineraryRepository.deleteByItineraryIdIn(intItineraryIds);
    }

    @Override
    public void deleteById(String itineraryId) {
        int intItineraryId = ItineraryUtils.resolveItineraryId(itineraryId);
        itineraryRepository.deleteById(intItineraryId);
    }

    @Override
    public void deleteAll() {
        itineraryRepository.deleteAll();
    }

}
