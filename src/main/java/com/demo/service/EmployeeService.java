package com.demo.service;

import com.demo.entity.Employee;
import com.demo.exception.ResourceNotFound;
import com.demo.payload.EmployeeDTO;
import com.demo.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    //    public Employee addEmployee(Employee employee) {
//
//           Employee emp1=employeeRepository.save(employee);
//           return emp1;
//    }
    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        Employee employee = mapToEntity(dto);
        Employee emp1 = employeeRepository.save(employee);
        return mapToDto(emp1);


    }


    public void deleteEmployee(long id) {

        employeeRepository.deleteById(id);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmp = employeeRepository.save(employee);
        return mapToDto(updateEmp);
    }

    public void patchEmployee(long id, EmployeeDTO dto) {
        Optional<Employee> opEmp = employeeRepository.findById(id);
        Employee employee = opEmp.get();
        employee.setName(dto.getName());
        employeeRepository.save(employee);

    }

//    public List<EmployeeDTO> getEmployees(int pageSize, int pageNo,String sortBy,String sortDir) {
//                    Sort sort=sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//           Pageable page= PageRequest.of(pageNo, pageSize, sort);
//        Page<Employee> all = employeeRepository.findAll(page);
//                      List<Employee> employees=all.getContent();
////        stream and function functional interface
//       List<EmployeeDTO> dto =employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
//       return dto;
//    }

    public List<EmployeeDTO> getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDTO> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }


    EmployeeDTO mapToDto(Employee employee) {
        EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);
//        EmployeeDTO dto = new EmployeeDTO();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
        return dto;
    }

    Employee mapToEntity(EmployeeDTO dto) {
        Employee emp = modelMapper.map(dto, Employee.class);

//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }

    public EmployeeDTO getEmployeeById(Long empId) {
//        Optional<Employee >  opEmp =employeeRepository.findById(empId);
        Employee employee = employeeRepository.findById(empId).orElseThrow(
//supplier functional interface
                () -> new ResourceNotFound("Record not found with :" + empId));
        EmployeeDTO dto = mapToDto(employee);
        return dto;

    }


}




