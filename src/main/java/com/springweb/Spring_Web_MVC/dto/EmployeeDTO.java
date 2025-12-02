package com.springweb.Spring_Web_MVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springweb.Spring_Web_MVC.annotations.EmployeeRoleValidationAnnotation;
import jakarta.validation.constraints.*;
import lombok.*;
import org.aspectj.bridge.IMessage;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in the range: [3, 10]")
    private String name;

    @NotBlank(message = "Email of the employee cannot be blank")
    @Email(message = "Email should be a valid email")
   private String email;

    @NotNull(message = "Age of the emplooyee cannot be blank")
    @Max(value = 80, message = "Age of Employee cannot be greater than 90")
    @Min(value = 18, message = "Age of Employee cannot be less than 18")
   private Integer age;

    @NotBlank(message = "Role of the Employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can either be USER or ADMIN")
    @EmployeeRoleValidationAnnotation
    private String role;

    @NotNull(message = "Salary of Employee should be not null")
    @Positive(message = "Salary of Employee should be positive")
    @Digits(integer = 6, fraction = 2, message = " The salary can be in the form XXXXX.YY")
    @DecimalMax(value = "10000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
   private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
   @JsonProperty("isActive")
   private Boolean isActive;



}

