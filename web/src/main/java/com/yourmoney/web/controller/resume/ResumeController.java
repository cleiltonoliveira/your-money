package com.yourmoney.web.controller.resume;

import com.yourmoney.domain.model.Resume;
import com.yourmoney.usecases.resume.ResumeFinder;
import com.yourmoney.web.controller.resume.dto.ResumeDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("api/v1")
public class ResumeController {

    private ResumeFinder resumeFinder;
    private ModelMapper modelMapper;

    public ResumeController(ResumeFinder resumeFinder, ModelMapper modelMapper) {
        this.resumeFinder = resumeFinder;
        this.modelMapper = modelMapper;
    }

    @GetMapping("resumo/{year}/{month}")
    public ResponseEntity<?> getMonthFinanceResume(@PathVariable("year") int year, @PathVariable("month") @Min(value = 1, message = "Invalid month") @Max(value = 12, message = "Invalid month") int month) {

        var resume = resumeFinder.getMonthFinanceResume(year, month);

        return new ResponseEntity<>(toDto(resume), HttpStatus.OK);
    }

    private ResumeDto toDto(Resume resume) {
        return modelMapper.map(resume, ResumeDto.class);
    }
}
