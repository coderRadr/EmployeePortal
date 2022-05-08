import { Component } from "react";

class EmployeeService extends Component {
  getEmployeeDetails() {
    return fetch("/getEmployeeDtls").then((res) => res.json());
  }
}
export default EmployeeService;
