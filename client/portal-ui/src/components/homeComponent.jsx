import React, { Component } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import EmployeeService from "./services/employeeService";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employees: [],
      isLoaded: false,
      service: new EmployeeService(),
    };
  }

  componentDidMount() {
    this.state.service.getEmployeeDetails().then((data) => {
      this.setState({
        isLoaded: true,
        employees: data,
        service: new EmployeeService(),
      });
    });
  }

  render() {
    return (
          <DataTable value={this.state.employees} responsiveLayout="scroll">
            <Column field="empId" header="Id"></Column>
            <Column field="firstName" header="First Name"></Column>
            <Column field="lastName" header="Last Name"></Column>
            <Column field="email" header="Email"></Column>
            <Column field="areaOfExpertise" header="Area of Expertise"></Column>
            <Column
              field="yearsOfExperience"
              header="Years of Experience"
            ></Column>
          </DataTable>
    );
  }
}
export default Home;
