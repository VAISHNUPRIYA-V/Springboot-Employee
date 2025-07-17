 package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

 @Service
public class EmployeeService {

@Autowired
     RegisterDetailsRepository repo;

@Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    RolesRepository rolesRepository;

 public List<RegisterDetails> getMethod(){
        return repo.findAll();
    }

    public RegisterDetails getEmployeeById(int EmpId){
        return repo.findById(EmpId).orElse(new RegisterDetails());
    }

//    public Optional<Employee> getEmployeeById(int EmpId){
//        return empRepo.findById(EmpId);
//    }
     

//    public String putMethod(int empId, RegisterDetails details) {
//        RegisterDetails user = repo.findById(empId)
//                .orElseThrow(() -> new RuntimeException("No Such User Present"));
//        user.setName(details.getName());
//        user.setEmail(details.getEmail());
//        user.setPassword(details.getPassword());
//        user.setUserName(details.getUserName());
//        repo.save(user);
//        return "Employee Updated Successfully";
//    }


     public String deleteMethod(int empId) {
         Optional<RegisterDetails> optionalUser = repo.findById(empId);

         if (optionalUser.isEmpty()) {
             return "Employee not found";
         }

         RegisterDetails user = optionalUser.get();

         // Step 1: Clear roles (removes associations from join table)
         user.getRoles().clear();
         repo.save(user);  // Persist the cleared roles

         // Step 2: Now safely delete the user
         repo.deleteById(empId);

         return "Employee deleted successfully";
     }


     public String addNewEmployee(UserDetailsDto register) {
         RegisterDetails registerDetails = new RegisterDetails();
         registerDetails.setEmpId(register.getEmpId());
         registerDetails.setName(register.getName());
         registerDetails.setEmail(register.getEmail());
         registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
         registerDetails.setUserName(register.getUserName());
         Set<Roles> roles = new HashSet<>();
         for(String roleName: register.getRoleNames()){
             Roles role = rolesRepository.findByRoleName(roleName)
                     .orElseThrow(()->new RuntimeException("User not found" + roleName));
             roles.add(role);
         }
         registerDetails.setRoles(roles);
         System.out.println("Registration"+ registerDetails);
         repo.save(registerDetails);
         return "Employee Registered Successfully";
     }

     public RegisterDetails getEmployeeByRole(String role) {
     return repo.findByRole(role).orElse(new RegisterDetails());
     }
     public String updateEmployee(int empId, UserDetailsDto details) {
         RegisterDetails user=repo.findById(empId)
                 .orElseThrow(()->new RuntimeException("No such user present"));
         user.setName(details.getName());
         user.setEmail(details.getEmail());
         user.setPassword(details.getPassword());
         user.setUserName(details.getUserName());
         repo.save(user);
         return "Employee updated successfully";
     }



 }


//    List<Employee> e=new ArrayList<>(Arrays.asList(new Employee(5,"John","Trainer"),new Employee(2,"David","Faculty"))
//    );
//
//    public List<Employee> getMethod() {
//        return e;
//    }
//
//    public Employee getEmployeeById(int EmpId){
//        int ind=0;
//        Boolean flag=false;
//        for(int i=0;i<e.size();i++){
//            if(EmpId==e.get(i).getEmpId()){
//                System.out.println(("Emp id: "+e.get(i).getEmpId()+e.get(i)));
//                ind=i;
//                flag=true;
//                break;
//            }
//        }
//        if(flag){
//            return e.get(ind);
//        }
//        else{
//            return new Employee();
//        }
//    }
//
//    public void postMethod(Employee emp) {
//        e.add(emp);
//    }
//
//    public String UpdateRecord(Employee employee) {
//        int ind=0;
//        boolean flag=false;
//        for(int i=0;i<e.size();i++){
//            if(employee.getEmpId()==e.get(i).getEmpId()){
//                System.out.println(("Emp id: "+e.get(i).getEmpId()+e.get(i)));
//                ind=i;
//                flag=true;
//                break;
//            }
//        }
//        if(flag){
//            e.set(ind, employee);
//            return "Updated!";
//        }
//        else{
//            return "Can't update,id not found!!!";
//        }
//    }
//
//    public String deleteMethod(int EmpId) {
//        boolean flag=false;
//        for(int i=0;i<e.size();i++){
//            if(EmpId==e.get(i).getEmpId()){
//                e.remove(i);
//                flag=true;
//                break;
//            }
//        }
//        if(flag){
//            return"Deleted";
//        }
//        else{
//            return "Can't delete,id not found";
//        }
//
//    }



