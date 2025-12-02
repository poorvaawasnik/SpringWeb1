package com.springweb.Spring_Web_MVC.controllers;


import com.springweb.Spring_Web_MVC.dto.EmployeeDTO;
import com.springweb.Spring_Web_MVC.repository.ResourceNotFound;
import com.springweb.Spring_Web_MVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    //  @GetMapping(path = "/getSecretMessage")
    // public String getMySuperSecretMessage(){
    //   return "Secret message: asdfal@#DASD";
    // }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return  employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFound("Employee Not Found with id: "+id));

    }
    @ExceptionHandler
    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {

        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(
            @RequestBody EmployeeDTO employeeDTO,
            @PathVariable("employeeId") Long employeeID) {

        return  ResponseEntity.ok(employeeService.updateEmployeeById(employeeID, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeId}")
    public  ResponseEntity<EmployeeDTO> updatePartialEmployeeById(
            @RequestBody Map<String, Object>  updates,
            @PathVariable Long employeeId) {

        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}