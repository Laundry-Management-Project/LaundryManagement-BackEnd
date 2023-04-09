package project.laundry.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.laundry.data.entity.Business;
import project.laundry.data.entity.Customer;
import project.laundry.data.form.signUpForm;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.exception.FormNullPointerException;
import project.laundry.exception.UserNullPointerException;
import project.laundry.repository.CustomerRepository;
import project.laundry.repository.OwnerRepository;

import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class FormValidator {

    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;

    public void signUpValidate(signUpForm form) {
        // form에 있는 값이 null이 발생하면 throw FormNullPointerException
        if(form == null || Stream.of(form.getId(), form.getPassword(), form.getPhone(), form.getName()).anyMatch(Objects::isNull)) {
            throw new FormNullPointerException();
        }
    }

    public void saveReservationValidate(Customer customer, Business business) {
        if(customer == null) {
            throw new UserNullPointerException();
        }
        if(business == null) {
            throw new DataIntegrityViolationException();
        }
    }

    public boolean isDuplicateCustomer(String id) {
        return customerRepository.findByCustomer_id(id) != null;
    }

    public boolean isDuplicateOwner(String id) {
        return ownerRepository.findByOwner_id(id) != null;
    }

}
