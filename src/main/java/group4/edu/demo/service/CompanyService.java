package group4.edu.demo.service;

import group4.edu.demo.model.Company;
import group4.edu.demo.model.UserDemo; // Đảm bảo import UserDemo model
import group4.edu.demo.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    // Lấy danh sách tất cả công ty
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Lấy công ty theo ID
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Xóa công ty theo ID
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    public void saveOrUpdate(Company company) {
        if (company.getUsers() != null) {
            for (UserDemo user : company.getUsers()) {
                user.setCompany(company); // Đảm bảo mỗi UserDemo được liên kết lại với Company
            }
        }
        companyRepository.save(company);
    }
}
