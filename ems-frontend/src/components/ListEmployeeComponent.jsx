import React, {useState, useEffect} from 'react'
import { deleteEmployee, lisEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployeeComponent = () => {

//useState hook allows us to use state variables in a functional component
const [employees, setEmployees] = useState([])

const navigator = useNavigate();

//In order to make the rest api call in the react components, we need to use useEffect hook
useEffect(() => {
    getAllEmployees();
}, [])

function getAllEmployees(){
    lisEmployees().then((response) => {
        setEmployees(response.data);
      }).catch(error => {
        console .error(error);
      })
}

function addNewEmployee(){
    navigator('/add-employee')
}

function updateEmployee(id){
    navigator(`/edit-employee/${id}`)
}

function removeEmployee(id){
    console.log(id);

    deleteEmployee(id).then((response) => {
        getAllEmployees();
    }).catch(error => {
        console.error(error);
    })
}
  return (

    <div className='container'>

         <h2 className='text-center'>List of Employees</h2>
         <button className='btn btn-secondary mb-2' onClick={addNewEmployee}>Add Employee</button>
         <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email Id</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    employees.map(employee => 
                        <tr key = {employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>
                                <button className='btn btn-info me-4' onClick={() => updateEmployee(employee.id)}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeEmployee(employee.id)}>Delete</button>
                            </td>
                        </tr>)
                }
               
            </tbody>
         </table>

    </div>
  )
}

export default ListEmployeeComponent