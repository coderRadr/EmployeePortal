import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProfileModel } from './home/employeeProfile';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  constructor(private httpClient: HttpClient) { }

  getAllEmployeeDtls(): Observable<any> {
    return this.httpClient.get('/api/getEmployeeDtls');
  }

  saveProfile(record: ProfileModel): Observable<any> {
    let requestUrl = `/api/updateEmployeeDtls/${record.empId}`;
    return this.httpClient.put(requestUrl, record);
  }

}
