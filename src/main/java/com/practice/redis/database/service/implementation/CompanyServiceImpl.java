package com.practice.redis.database.service.implementation;

import com.practice.redis.database.domain.request.CompanyRequest;
import com.practice.redis.database.domain.response.CompanyResponse;
import com.practice.redis.database.domain.response.MessageResponse;
import com.practice.redis.database.repository.CompanyRepository;
import com.practice.redis.database.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public CompanyResponse editCompany(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public List<CompanyResponse> getCompanies() {
        return null;
    }

    @Override
    public CompanyResponse getCompanyById(UUID companyId) {
        return null;
    }

    @Override
    public MessageResponse deleteCompanyById(UUID companyId) {
        return null;
    }
}
