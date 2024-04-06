package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Phone;
import uz.pdp.repository.PhoneRepository;

import java.util.List;
import java.util.Optional;


@RestController
public class PhoneController {

    static List<Phone> phoneList;
    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping(value = "/phone", method = RequestMethod.GET)

    public List<Phone> gePhones() {
        phoneList = phoneRepository.findAll();
        return phoneList;
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET)
    public Phone getStudent(@PathVariable Integer id) {
        Optional<Phone> optionalStudent = phoneRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Phone phone = optionalStudent.get();
            return phone;
        } else {
            return new Phone();
        }

    }
    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public String addPhone(@RequestBody Phone phone) {
        boolean isThere = false;
        for (Phone currentPhone1 : phoneList) {
            if (currentPhone1.getMacAddress().equals(phone.getMacAddress())) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            return "Phone not saved,please enter another  MacAddress ";
        } else {
            phoneRepository.save(phone);
            return "phone added";
        }
    }

   @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public String editPhone(@PathVariable Integer id, @RequestBody Phone phone) {
       boolean isThere = false;
       for (Phone currentPhone1 : phoneList) {
           if (currentPhone1.getMacAddress().equals(phone.getMacAddress())) {
               isThere = true;
               break;
           }
       }   if (isThere) {
           return "Phone not edited,please enter another MacAddress ";

       }   else {
           Optional<Phone> optionalPhone = phoneRepository.findById(id);
           if (optionalPhone.isPresent()) {
               Phone editingPhone = optionalPhone.get();
               editingPhone.setName(phone.getName());
               editingPhone.setModel(phone.getModel());
               editingPhone.setMacAddress(phone.getMacAddress());
               editingPhone.setInfo(phone.getInfo());
               phoneRepository.save(editingPhone);
               return "Phone edited";
           }
           return "Phone not found";
       }
   }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public String deletePhone(@PathVariable Integer id) {
        phoneRepository.deleteById(id);
        return "Phone deleted";
    }


}
