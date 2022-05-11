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
  openNewDialogue: boolean = false;
  empId: number = 0;
  firstName: string = '';
  nameRegex: RegExp = new RegExp('^[a-zA-Z\\s]{3,}$');
  emailRegex: RegExp = new RegExp(
    '^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$'
  );
  areasRegex: RegExp = new RegExp("^[\\w\\s',']{3,}$");
  experienceRegex: RegExp = new RegExp("^[\\d'.']{1,4}$");
  lastName: string = '';
  email: string = '';
  areaOfExpertise: string = '';
  yearsOfExperience: number = 0;
  firstNameInvalid: boolean = false;
  lastNameInvalid: boolean = false;
  emailInvalid: boolean = false;
  areasInvalid: boolean = false;
  experienceInvalid: boolean = false;
  submitDisabled: boolean = true;

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

  onAddNew() {
    this.openNewDialogue = true;
    this.empId = this.employeeProfiles.length + 1;
  }

  validateInput(event: string, field: string) {
    switch (field) {
      case 'firstName': {
        this.firstNameInvalid = !this.nameRegex.test(event);
        break;
      }
      case 'lastName': {
        this.lastNameInvalid = !this.nameRegex.test(event);
        break;
      }
      case 'email': {
        this.emailInvalid = !this.emailRegex.test(event);
        break;
      }
      case 'areas': {
        this.areasInvalid = !this.areasRegex.test(event);
        break;
      }
      case 'experience': {
        this.experienceInvalid = !this.experienceRegex.test(event);
      }
    }
    const inputsInvalid =
      this.experienceInvalid ||
      this.areasInvalid ||
      this.emailInvalid ||
      this.firstNameInvalid ||
      this.lastNameInvalid;
    const inputsNotPresent =
      this.firstName.length === 0 ||
      this.lastName.length === 0 ||
      this.email.length === 0 ||
      this.areaOfExpertise.length === 0 ||
      this.yearsOfExperience === 0;
    this.submitDisabled = inputsInvalid && inputsNotPresent;
  }

  closeDialogWindow() {
    this.openNewDialogue = false;
  }

  saveRecord() {
    const record: ProfileModel = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      areaOfExpertise: this.areaOfExpertise,
      empId: this.empId,
      yearsOfExperience: this.yearsOfExperience,
    };
    this.restApi.addProfile(record).subscribe(
      (res) => {
        this.openNewDialogue = false;
        this.getAllEmployees();
      },
      (err) => {
        this.openNewDialogue = false;
        console.log(err);
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Adding new record to DB failed',
        });
      }
    );
  }
}
