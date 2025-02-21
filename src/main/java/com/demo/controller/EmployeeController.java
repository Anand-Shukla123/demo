package com.demo.controller;

import com.demo.payload.EmployeeDTO;
import com.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeDTO employeeDTO = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmp")
    public String deleteEmployee(@RequestParam Long id) {

        employeeService.deleteEmployee(id);
        return "deleted";
    }

    @PutMapping("/putEmp")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDTO dto) {
        EmployeeDTO employeeDTO = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PatchMapping("/patchEmp")
    public String patchEmployee(@RequestParam long id, @RequestBody EmployeeDTO dto) {
        employeeService.patchEmployee(id, dto);
        return "Patched";
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(
            @RequestParam(name = "pageN0", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "emailId") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "desc") String sortDir) {
        List<EmployeeDTO> dto = employeeService.getEmployees(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //    @GetMapping("employeeId/{empId}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long empId){
//       EmployeeDTO employeeDTO =employeeService.getEmployee(empId);
//        return new ResponseEntity<>(employeeDTO,HttpStatus.OK);
//    }
    @GetMapping("/empId/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long empId) {
        EmployeeDTO dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
