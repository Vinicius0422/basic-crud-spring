package br.com.vinicius.basiccrud.mapper;

import br.com.vinicius.basiccrud.dtos.ContactDto;
import br.com.vinicius.basiccrud.models.Contact;
import br.com.vinicius.basiccrud.utils.ContactUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ContactMapper {

    private ContactUtils contactUtils;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ContactMapper(ContactUtils contactUtils) {
        this.contactUtils = contactUtils;
    }

    public Contact dtoToEntity(ContactDto contactDto){
        //Method to transform dto into entity
        Contact contact = new Contact(
                contactDto.firstName().toLowerCase(),
                contactDto.lastName().toLowerCase(),
                contactDto.age(),
                contactDto.email().toLowerCase(),
                contactDto.phone(),
                LocalDate.parse(contactDto.birthDate(), formatter),
                contactDto.occupation().toLowerCase(),
                contactDto.company().toLowerCase(),
                contactDto.street().toLowerCase(),
                contactDto.country().toLowerCase(),
                contactDto.city().toLowerCase(),
                contactDto.neighborhood().toLowerCase(),
                contactDto.zipCode()
        );

        return contact;
    }

    public ContactDto entityToDto(Contact contact){
        //Method to transform entity into dto
        ContactDto contactDto = new ContactDto(
                contactUtils.capitalizeFirstLetter(contact.getFirstName()),
                contactUtils.capitalizeFirstLetter(contact.getLastName()),
                contact.getAge(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getBirthDate().format(formatter),
                contactUtils.capitalizeFirstLetter(contact.getOccupation()),
                contactUtils.capitalizeFirstLetter(contact.getCompany()),
                contactUtils.capitalizeFirstLetter(contact.getStreet()),
                contactUtils.capitalizeFirstLetter(contact.getCountry()),
                contactUtils.capitalizeFirstLetter(contact.getCity()),
                contactUtils.capitalizeFirstLetter(contact.getNeighborhood()),
                contact.getZipCode()
        );

        return contactDto;
    }
}
