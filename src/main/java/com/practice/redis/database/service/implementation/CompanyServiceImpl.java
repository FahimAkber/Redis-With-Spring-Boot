package com.practice.redis.database.service.implementation;

import com.practice.redis.common.exception.ApplicationException;
import com.practice.redis.database.domain.entity.Company;
import com.practice.redis.database.domain.request.CompanyRequest;
import com.practice.redis.database.domain.response.CompanyResponse;
import com.practice.redis.database.domain.response.MessageResponse;
import com.practice.redis.database.repository.CompanyRepository;
import com.practice.redis.database.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Optional<Company> optionalCompany = companyRepository.findByName(companyRequest.getName());
        if(optionalCompany.isPresent()){
            throw new ApplicationException("Company already saved.", HttpStatus.ALREADY_REPORTED);
        }

        Company company = modelMapper.map(companyRequest, Company.class);
        company = companyRepository.save(company);

        return modelMapper.map(company, CompanyResponse.class);
    }

    @Override
    public CompanyResponse editCompany(CompanyRequest companyRequest) {
        Optional<Company> optionalCompany = companyRepository.findById(companyRequest.getId());
        if(optionalCompany.isEmpty()){
            throw new ApplicationException("Company not found.", HttpStatus.NOT_FOUND);
        }

        Company company = modelMapper.map(companyRequest, Company.class);
        company = companyRepository.save(company);

        return modelMapper.map(company, CompanyResponse.class);
    }

    @Override
    public List<CompanyResponse> getCompanies() {
        Iterable<Company> companies = companyRepository.findAll();
        if(!companies.iterator().hasNext()) {
            throw new ApplicationException("No company found", HttpStatus.NOT_FOUND);
        }

        List<CompanyResponse> companyResponses = new ArrayList<>();
        companies.forEach(company -> companyResponses.add(modelMapper.map(company, CompanyResponse.class)));

        return companyResponses;
    }

    @Override
    public CompanyResponse getCompanyById(UUID companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if(optionalCompany.isEmpty()){
            throw new ApplicationException("Company not found", HttpStatus.NOT_FOUND);
        }

        return modelMapper.map(optionalCompany.get(), CompanyResponse.class);
    }

    @Override
    public MessageResponse deleteCompanyById(UUID companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if(optionalCompany.isEmpty()){
            throw new ApplicationException("Company not found", HttpStatus.NOT_FOUND);
        }

        companyRepository.delete(optionalCompany.get());
        return new MessageResponse("Company deleted successfully");
    }
}
