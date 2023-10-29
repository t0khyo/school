package com.t0khyo.school.module.period;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PeriodService {
    private final PeriodRepository periodRepository;

    public Period createPeriod(Period period) {
        period.setId(0L);

        return periodRepository.save(period);
    }

    public Period getPeriodById(Long periodId) {
        return periodRepository.findById(periodId)
                .orElseThrow(() -> new EntityNotFoundException("Period with ID " + periodId + " not found"));
    }

    public List<Period> getAllPeriods() {
        return periodRepository.findAll();
    }

    public Period updatePeriod(Period period) {
        return periodRepository.save(period);
    }

    public void deletePeriod(Long periodId) {
        Period period = periodRepository.findById(periodId)
                .orElseThrow(() -> new EntityNotFoundException("Period with ID " + periodId + " not found"));
        periodRepository.delete(period);
    }
}
