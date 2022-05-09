import { Component, OnInit, ViewChild } from '@angular/core';
import { RestApiService } from '../rest-api.service';
import { ProfileModel } from './employeeProfile';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [MessageService],
})
export class HomeComponent implements OnInit {
  @ViewChild('dt') dt: Table | undefined;
  employeeProfiles: ProfileModel[] = [];
  loading: boolean = true;
  constructor(private restApi: RestApiService) {}

  ngOnInit(): void {
    this.restApi.getAllEmployeeDtls().subscribe(
      (res: ProfileModel[]) => {
        this.employeeProfiles = res;
        this.loading = false;
      },
      (err) => {
        console.error(err);
        this.loading = false;
      }
    );
  }

  applyFilterGlobal($event: Event, stringVal: any) {
    if (this.dt != undefined)
      this.dt.filterGlobal(
        ($event.target as HTMLInputElement).value,
        stringVal
      );
  }
}
