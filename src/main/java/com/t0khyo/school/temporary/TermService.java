package com.t0khyo.school.temporary;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TermService {
    private final TermRepository termRepository;
    private final AcademicYearRepository academicYearRepository;

    public void createTerm(Term term) {
        term.setId(0L);
        termRepository.save(term);
    }

    public Term getTermById(Long termId) {
        return termRepository.findById(termId)
                .orElseThrow(() -> new EntityNotFoundException("Term with ID " + termId + " not found"));
    }

    public Page<Term> getAllTermsWithPagination(int pageNumber, int pageSize) {
        return termRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "termNumber"));
    }

    @Transactional
    public void updateTerm(Term term) {
        Term existingTerm = termRepository.findById(term.getId())
                .orElseThrow(() -> new EntityNotFoundException("Term with ID " + term.getId() + " not found"));

        if (term.getTermNumber() >= 0) {
            existingTerm.setTermNumber(term.getTermNumber());
        }

        if (term.getStartDate() != null) {
            existingTerm.setStartDate(term.getStartDate());
        }

        if (term.getEndDate() != null) {
            existingTerm.setEndDate(term.getEndDate());
        }

        if (term.getAcademicYear() != null) {
            AcademicYear academicYear = academicYearRepository.findById(term.getAcademicYear().getId())
                    .orElseThrow(() -> new EntityNotFoundException("AcademicYear with ID " + term.getAcademicYear().getId() + " not found"));
            existingTerm.setAcademicYear(academicYear);
        }

    }

    public void deleteTerm(long termId) {
        Term term = termRepository.findById(termId)
                .orElseThrow(() -> new EntityNotFoundException("Term with ID " + termId + " not found"));
        termRepository.delete(term);
    }
}
