 package com.example.springbootfirst.services;

import com.example.springbootfirst.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HelloWorldService {
    List<Employee> e=new ArrayList<>(Arrays.asList(new Employee(5,"John","Trainer"),new Employee(2,"David","Faculty"))
    );

    public List<Employee> getMethod() {
        return e;
    }

    public Employee getEmployeeById(int EmpId){
        int ind=0;
        Boolean flag=false;
        for(int i=0;i<e.size();i++){
            if(EmpId==e.get(i).getEmpId()){
                System.out.println(("Emp id: "+e.get(i).getEmpId()+e.get(i)));
                ind=i;
                flag=true;
                break;
            }
        }
        if(flag){
            return e.get(ind);
        }
        else{
            return new Employee();
        }
    }

    public void postMethod(Employee emp) {
        e.add(emp);
    }

    public String UpdateRecord(Employee employee) {
        int ind=0;
        boolean flag=false;
        for(int i=0;i<e.size();i++){
            if(employee.getEmpId()==e.get(i).getEmpId()){
                System.out.println(("Emp id: "+e.get(i).getEmpId()+e.get(i)));
                ind=i;
                flag=true;
                break;
            }
        }
        if(flag){
            e.set(ind, employee);
            return "Updated!";
        }
        else{
            return "Can't update,id not found!!!";
        }
    }

    public String deleteMethod(int EmpId) {
        boolean flag=false;
        for(int i=0;i<e.size();i++){
            if(EmpId==e.get(i).getEmpId()){
                e.remove(i);
                flag=true;
                break;
            }
        }
        if(flag){
            return"Deleted";
        }
        else{
            return "Can't delete,id not found";
        }

    }


}
