package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    private final ReportRepository reportRepository;
    
    public ReportService(ReportRepository repository) {
        this.reportRepository = repository;
    }
    
    public List<Report> getMyList(Employee employee) {
        
        return reportRepository.findByEmployee(employee);
    }

    public List<Report> getReportList() {
        
        return reportRepository.findAll();
    }
    
    public Report getReport(Integer id) {
        return reportRepository.findById(id).get();
    }
    
    @Transactional
    public Report saveReport(Report report) {

        //report.getEmployee().setReports(report);
        report.setCreatedAt(LocalDateTime.now());
        report.setUpdatedAt(LocalDateTime.now());
        
        return reportRepository.save(report);
    }
    

}
