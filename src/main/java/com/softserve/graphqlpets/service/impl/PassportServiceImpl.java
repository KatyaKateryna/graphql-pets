package com.softserve.graphqlpets.service.impl;

import com.softserve.graphqlpets.dto.InternalPassport;
import com.softserve.graphqlpets.dto.InternationalPassport;
import com.softserve.graphqlpets.dto.Passport;
import com.softserve.graphqlpets.service.PassportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PassportServiceImpl implements PassportService {

    @Override
    public Passport getPassport(Integer catId, boolean fastLoad) {
        if (!fastLoad) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new InternationalPassport(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        }

        return new InternalPassport(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    }
}
