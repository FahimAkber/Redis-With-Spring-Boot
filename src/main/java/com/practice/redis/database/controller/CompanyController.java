package com.practice.redis.database.controller;

import com.practice.redis.database.domain.request.CompanyRequest;
import com.practice.redis.database.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("company/")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("save")
    public ResponseEntity<Object> saveCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        return new ResponseEntity<>(companyService.saveCompany(companyRequest), HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<Object> editCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        return new ResponseEntity<>(companyService.editCompany(companyRequest), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<Object> getCompanies(){
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{companyId}")
    public ResponseEntity<Object> getCompanyById(@PathVariable("companyId") UUID companyId) {
        return new ResponseEntity(companyService.getCompanyById(companyId), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id/{companyId}")
    public ResponseEntity<Object> deleteCompanyById(@PathVariable("companyId") UUID companyId) {
        return new ResponseEntity<>(companyService.deleteCompanyById(companyId), HttpStatus.OK);
    }
}
