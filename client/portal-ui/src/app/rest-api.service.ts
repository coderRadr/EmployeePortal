import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  constructor(private httpClient: HttpClient) { }

  getAllEmployeeDtls(): Observable<any> {
    return this.httpClient.get('/api/getEmployeeDtls');
  }

}
