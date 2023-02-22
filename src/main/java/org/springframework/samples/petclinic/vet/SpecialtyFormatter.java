package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class SpecialtyFormatter implements Formatter<Specialty>
{

    private final VetService vetService;

    @Autowired
    public SpecialtyFormatter( VetService vetService )
    {
        this.vetService = vetService;
    }

    @Override
    public String print(Specialty specialty, Locale locale) {
        return specialty.getName() ;
    }

    @Override
    public Specialty parse(String text, Locale locale) throws ParseException {
        Collection<Specialty> specialties = this.vetService.findSpecialties();
        for (Specialty name : specialties) {
            if ( name.getName().equals( text ) )
            {
                return name;
            }
        }
        throw new ParseException("specialty not found: " + text, 0);
    }

}
