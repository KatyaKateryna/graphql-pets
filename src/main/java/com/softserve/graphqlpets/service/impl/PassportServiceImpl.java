package com.softserve.graphqlpets.service.impl;

import com.softserve.graphqlpets.dto.InternalPassport;
import com.softserve.graphqlpets.dto.InternationalPassport;
import com.softserve.graphqlpets.dto.Passport;
import com.softserve.graphqlpets.service.PassportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PassportServiceImpl implements PassportService {

    @Override
    public Passport getPassport(UUID catId, boolean fastLoad) {
        if (!fastLoad) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new InternationalPassport(LocalDate.now());
        }

        return new InternalPassport(LocalDate.now());
    }
}
