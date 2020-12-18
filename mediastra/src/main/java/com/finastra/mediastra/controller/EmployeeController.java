package com.finastra.mediastra.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.finastra.mediastra.entity.Employee;
import com.finastra.mediastra.exception.ResourceNotFoundException;
import com.finastra.mediastra.repository.EmployeeRepository;
import com.finastra.mediastra.repository.GeneratePdfReport;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Async
	@GetMapping("/employees/{id}")
	public DeferredResult<ResponseEntity<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		final DeferredResult<ResponseEntity<Employee>> deferredResult = new DeferredResult<ResponseEntity<Employee>>();
		deferredResult.onTimeout(new Runnable() {

			@Override
			public void run() {
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT));

			}
		});

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		deferredResult.setResult(ResponseEntity.ok().body(employee));

		return deferredResult;
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/employees/report")
	public int[] getAllEmployeesReport() {
		int[] vaccineCount = new int[2];
		int isIntakeCount = 0;
		int notIntakeCount = 0;
		for (Employee employees : employeeRepository.findAll()) {
			String value = employees.getIsIntake();
			System.out.println("Value printed... " + value);
			if (value.equals("1")) {
				isIntakeCount++;
				System.out.println("Value is intake printed... " + isIntakeCount);
			} else {
				notIntakeCount++;
				System.out.println("Value not intake printed... " + notIntakeCount);
			}
		}
		vaccineCount[0] = isIntakeCount;
		vaccineCount[1] = notIntakeCount;

		return vaccineCount;
	}

	@GetMapping("/employees/searchCity/{city}")
	public ResponseEntity<InputStreamResource> getVaccineIntake(@PathVariable(value = "city") String city) {
		List<Employee> emp = null;
		ByteArrayInputStream bis = null;
		if ("All".equals(city)) {
			emp = (List<Employee>) employeeRepository.findAll();
			bis = GeneratePdfReport.citiesReport(emp);
		} else {
			emp = (List<Employee>) employeeRepository.getbycity(city);

			bis = GeneratePdfReport.citiesReport(emp);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@GetMapping("/employees/searchWithoutCity/{istaken}")
	public ResponseEntity<InputStreamResource> notTakenList(@PathVariable(value = "istaken") String isTaken) {
		List<Employee> employees = (List<Employee>) employeeRepository.notTakenList(isTaken);

		ByteArrayInputStream bis = GeneratePdfReport.citiesReport(employees);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		// headers.add("Access-Control-Allow-Origin", "*");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}