package com.terrain360.terrain360.services.implementation;


import com.terrain360.terrain360.entities.Quota;
import com.terrain360.terrain360.repositories.QuotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotaService {
    private final QuotaRepository quotaRepository;

    public QuotaService(QuotaRepository quotaRepository) {
        this.quotaRepository = quotaRepository;
    }

    public List<Quota> getAllQuotas() {
        return quotaRepository.findAll();
    }

    public Optional<Quota> getQuotaById(Long id) {
        return quotaRepository.findById(id);
    }

    public Quota saveQuota(Quota quota) {
        return quotaRepository.save(quota);
    }

    public Quota updateQuota(Long id, Quota updated) {
        return quotaRepository.findById(id)
                .map(existing -> {
                    existing.setObjectif(updated.getObjectif());
                    existing.setObjectif(updated.getObjectif());
                    return quotaRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Quota not found"));
    }

    public void deleteQuota(Long id) {
        quotaRepository.deleteById(id);
    }
}
