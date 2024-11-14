package com.practice.redis.database.service;

import com.practice.redis.database.domain.request.CompanyRequest;
import com.practice.redis.database.domain.response.CompanyResponse;
import com.practice.redis.database.domain.response.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse editCompany(CompanyRequest companyRequest);
    List<CompanyResponse> getCompanies();
    CompanyResponse getCompanyById(UUID companyId);
    MessageResponse deleteCompanyById(UUID companyId);
}
