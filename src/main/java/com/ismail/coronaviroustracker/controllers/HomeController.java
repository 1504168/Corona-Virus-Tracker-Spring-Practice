package com.ismail.coronaviroustracker.controllers;

import com.ismail.coronaviroustracker.model.LocationStats;
import com.ismail.coronaviroustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allLocation = coronaVirusDataService.getAllLocation();
        allLocation.sort(Comparator.comparingLong(LocationStats::getNewCase));
        allLocation = allLocation.stream()
                .filter((location) -> location.getNewCase() > 0)
                .toList();
        long totalWorldWideCase = allLocation.stream()
                .mapToLong(location -> location.getLastDayTotal())
                .sum();
        long totalWorldWideNewCase = allLocation.stream()
                .mapToLong(location -> location.getNewCase())
                .sum();
        model.addAttribute("totalWorldWideCase", totalWorldWideCase);
        model.addAttribute("totalWorldWideNewCase", totalWorldWideNewCase);
        model.addAttribute("locationStats", allLocation);
        return "home";
    }
    
}
