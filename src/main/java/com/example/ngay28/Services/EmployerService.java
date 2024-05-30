package com.example.ngay28.Services;

import com.example.ngay28.Entities.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void addEmployer(Employer employer) {
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
}
