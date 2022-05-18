package ru.alishev.springcourse.FirstSecurityApp.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

/*    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToUpdate = peopleRepository.getById(id);
        personToUpdate.setFull_name(updatedPerson.getFull_name());
        personToUpdate.setPhone(updatedPerson.getPhone());
        personToUpdate.setEmail(updatedPerson.getEmail());
        peopleRepository.save(personToUpdate);
        System.out.println(personToUpdate.toString());
    }*/

/*    @Transactional
    public void update(int id, Person updatedPerson) {

        personToBeUpdated.setFull_name(updatedPerson.getFull_name());
        personToBeUpdated.setPhone(updatedPerson.getPhone());
        personToBeUpdated.setEmail(updatedPerson.getEmail());

    }*/
}
