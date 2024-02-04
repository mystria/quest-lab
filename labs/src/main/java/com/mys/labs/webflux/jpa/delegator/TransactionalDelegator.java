package com.mys.labs.webflux.jpa.delegator;

import com.mys.labs.webflux.jpa.service.HumanResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionalDelegator {

    private final HumanResourceService humanResourceService;

    public Long transactionalActions() {

        Long companyId = humanResourceService.createCompany("Apple Inc.");
        humanResourceService.createEmployee(companyId, "Steve Jobs", "jobs@apple.com");
        humanResourceService.createEmployee(1L, "Tim Cook", "cook@apple.com");
        humanResourceService.createEmployee(companyId, "Steve Jobs", "jobs@apple.com");
        return 0L;
    }

    public Long transactionalActionsWorking() {

        Long companyId = humanResourceService.createCompany("Apple Inc.");
        humanResourceService.createEmployee(companyId, "Steve Jobs", "jobs@apple.com");
        humanResourceService.createEmployee(1L, "Tim Cook", "cook@apple.com");
        humanResourceService.createEmployee(companyId, "Steve Wozniak", "wozniak@apple.com");
        return 0L;
    }
}
