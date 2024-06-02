package com.example.ngay28.Services;

import com.example.ngay28.Entities.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final List<Employer> employers;

    public List<Employer> getAllEmployers() {
        return employers;
    }

    public Optional<Employer> getEmployerById(Long id) {
        return employers.stream()
                .filter(employer -> employer.getId().equals(id))
                .findFirst();
    }

    private long lastEmployerId = 4;

    public void addEmployer(Employer employer) {
        // Generate a unique ID for the new Employer
        long newId = ++lastEmployerId;
        employer.setId(newId);

        // Add the Employer to the list
        employers.add(employer);
    }
    public void updateEmployer(Employer employer) {
        var employerOptional = getEmployerById(employer.getId());
        if (employerOptional.isPresent()) {
            Employer employerUpdate = employerOptional.get();
            employerUpdate.setEmployer_name(employer.getEmployer_name());
            employerUpdate.setEmail(employer.getEmail());
            employerUpdate.setSdt(employer.getSdt());
            employerUpdate.setDiaChi(employer.getDiaChi());
            employerUpdate.setNgaySinh(employer.getNgaySinh());
        }
    }
    public void deleteEmployerById(Long id) {
        getEmployerById(id).ifPresent(employers::remove);
    }

    //Các ràng buộc--------------
    //Kiểm tra tên
    public boolean isValidEmployerName(String employerName) {
        if (employerName == null || employerName.trim().isEmpty()) {
            return false;
        }

        // Check if the name contains any digits
        if (containsDigits(employerName)) {
            return false;
        }

        return true;
    }

    private boolean containsDigits(String str) {
        return str.matches(".*\\d.*");
    }

    //Kiêm tra email
    public boolean isValidEmail(String email) {
        // Regex pattern for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Kiểm tra xem chuỗi có chứa ký tự không phải số hay không
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }

        // Kiểm tra độ dài của số điện thoại
        if (phoneNumber.length() != 10 && phoneNumber.length() != 11) {
            return false;
        }

        return true;
    }

    public boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    public boolean isValidBirthDate(Date birthDate) {
        // Kiểm tra xem ngày sinh có hợp lệ không
        return birthDate != null && birthDate.before(new Date());
    }
}
