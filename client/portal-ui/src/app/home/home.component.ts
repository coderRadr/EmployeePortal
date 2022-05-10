import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { RestApiService } from '../rest-api.service';
import { ProfileModel } from './employeeProfile';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [MessageService],
})
export class HomeComponent implements OnInit {
  @ViewChild('dt') dt: Table | undefined;
  employeeProfiles: ProfileModel[] = [];
  employeeProfiles2: ProfileModel[] = [];
  clonedRecords: { [id: number]: ProfileModel } = {};
  loading: boolean = true;
  constructor(
    private restApi: RestApiService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.getAllEmployees();
  }

  private getAllEmployees() {
    this.restApi.getAllEmployeeDtls().subscribe(
      (res: ProfileModel[]) => {
        this.employeeProfiles = res;
        this.employeeProfiles2 = res;
        this.loading = false;
      },
      (err) => {
        console.error(err);
        this.loading = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Error getting records from service',
        });
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

  onRowEditInit(record: ProfileModel) {
    if (record != undefined) {
      this.clonedRecords[record.empId] = { ...record };
    }
  }

  onRowEditSave(record: ProfileModel) {
    this.restApi.saveProfile(record).subscribe(
      (res) => {
        this.getAllEmployees();
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Record is updated',
        });
      },
      (err) => {
        console.error(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Record update failed',
        });
      }
    );
  }

  onRowEditCancel(record: ProfileModel, index: number) {
    this.employeeProfiles2[index] = this.clonedRecords[record.empId];
    delete this.clonedRecords[record.empId];
  }
}
