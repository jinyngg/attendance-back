package com.toy4.domain.department;

import com.toy4.domain.department.service.DepartmentService;
import com.toy4.global.response.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/init")
    public ResponseEntity<?> insertAllDepartments() {
        CommonResponse<?> response = departmentService.insertAllDepartments();
        return ResponseEntity.ok(response);
    }
}
