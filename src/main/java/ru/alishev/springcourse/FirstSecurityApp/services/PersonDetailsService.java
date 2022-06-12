package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService( PeopleRepository peopleRepository) {

        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

    public List<Person> getAllUsers() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        return peopleRepository.getById(id);
    }


   public Person updatePerson(int id, Person oldPerson) {
        oldPerson.setId(oldPerson.getId()) ; // pass the associated id for which you want to update and set that id to the same person [ basically setting the same id to the oldPerson ] this way it will not create new entry because here we are not making new ID ]
        oldPerson.setUsername(oldPerson.getUsername());

        oldPerson.setFull_name(oldPerson.getFull_name());
        oldPerson.setPhone(oldPerson.getPhone());
        oldPerson.setEmail(oldPerson.getEmail());

        return peopleRepository.save(oldPerson); // now save [old person with updated content but same id as it was before ]
    }
}
