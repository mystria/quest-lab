package com.mys.labs.webflux.jpa.controller;

import com.mys.labs.webflux.jpa.delegator.TransactionalDelegator;
import com.mys.labs.webflux.jpa.model.Employee;
import com.mys.labs.webflux.jpa.service.HumanResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HumanResourceController {

    private final HumanResourceService humanResourceService;

    private final TransactionalDelegator transactionalDelegator;

    @GetMapping("/{companyId}/employees")
    public ResponseEntity<List<Employee>> listEmployees(@PathVariable Long companyId) {

        List<Employee> employees = humanResourceService.listEmployees(companyId);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/{companyId}/employees")
    public ResponseEntity<Long> createEmployee(
            @PathVariable Long companyId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email) {

        Long employeeId = humanResourceService.createEmployee(companyId, name, email);
        return ResponseEntity.ok(employeeId);
    }

    @GetMapping("/test1")
    public Mono<Long> test1() {

        // 서비스의 메서드는 각각의 transaction 으로 묶여 있으므로, 3번째에서 실패하더라도 1번째와 2번째는 정상적으로 처리된다.
        return Mono.fromSupplier(() -> {
            Long companyId = humanResourceService.createCompany("Apple Inc.");
            humanResourceService.createEmployee(companyId, "Steve Jobs", "jobs@apple.com");
            humanResourceService.createEmployee(1L, "Tim Cook", "cook@apple.com");
            humanResourceService.createEmployee(companyId, "Steve Jobs", "jobs@apple.com"); // Unique index or primary key violation
            return 0L;
        }).subscribeOn(Schedulers.boundedElastic())
                .doOnError(e -> System.out.println("error caught!, " + e.getMessage()));
    }

    @GetMapping("/test2")
    public Mono<Long> test2() {

        // 트랜잭션 메서드는 전체 서비스 메서드를 transaction 으로 묶어 처리하므로, 3번째에서 실패하면 1번째와 2번째도 모두 롤백된다.
        return Mono.fromSupplier(transactionalDelegator::transactionalActions)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnError(e -> System.out.println("error caught!, " + e.getMessage()));
    }

    @GetMapping("/test3")
    public Mono<Long> test3() {

        // 트랜잭션 메서드는 전체 서비스 메서드를 transaction 으로 묶어 처리
        return Mono.fromSupplier(transactionalDelegator::transactionalActionsWorking).subscribeOn(Schedulers.boundedElastic())
                .doOnError(e -> System.out.println("error caught!, " + e.getMessage()));
    }
}
