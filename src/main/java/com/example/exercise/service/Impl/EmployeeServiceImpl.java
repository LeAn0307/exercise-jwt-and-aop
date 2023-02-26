package com.example.exercise.service.Impl;

import com.example.exercise.dto.EmployeeDto;
import com.example.exercise.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;




@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public EmployeeDto getEmployeeDto(EmployeeDto employeeDto) {
        LOGGER.info(employeeDto.toString());
        return employeeDto;
    }
}