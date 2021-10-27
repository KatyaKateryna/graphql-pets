package com.softserve.graphqlpets.service.impl;

import com.softserve.graphqlpets.dto.Passport;
import com.softserve.graphqlpets.service.PassportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PassportServiceImpl implements PassportService {

    @Override
    public Passport getPassport(Integer catId) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Passport(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    }
}
