import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8080/mediastra/api/v1/employees';

  constructor(private http: HttpClient) { }

  getEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createEmployee(employee: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, employee);
  }

  updateEmployee(id: number, employee: Object): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
   getEmployeesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  
  getAllEmployeesReport(): Observable<Object> {
    return this.http.get(`${this.baseUrl}/report`);
  }
  createHelpingrequest(helpinghand: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}/addhelpinghand`, helpinghand);
  }
  uploadFile(file: File , id : number ) : Observable<any>{
	  let url = this.baseUrl + "/upload/" + id ;  
  
    const formdata: FormData = new FormData();  
    
    formdata.append('file', file);
	  return this.http.post(url, formdata);
  }
    getAllRequest(): Observable<any> {
    return this.http.get(`${this.baseUrl}/helpinghandrequestList`);
  }
  getVaccineIntake(city: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchCity/${city}`,{ responseType: 'blob' });
  }
  
   notTakenList(vaccineTaken: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchWithoutCity/${vaccineTaken}`,{ responseType: 'blob' });
  }
  
  searchAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchAll`,{ responseType: 'blob' });
  }
  
}