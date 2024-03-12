package br.com.vinicius.basiccrud.services;

import br.com.vinicius.basiccrud.dtos.ContactDto;
import br.com.vinicius.basiccrud.exceptions.DuplicateDataException;
import br.com.vinicius.basiccrud.exceptions.ResourceNotFoundException;
import br.com.vinicius.basiccrud.mapper.ContactMapper;
import br.com.vinicius.basiccrud.models.Contact;
import br.com.vinicius.basiccrud.repositories.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImplementation implements ContactService{

    private ContactRepository contactRepository;
    private ContactMapper contactMapper;

    public ContactServiceImplementation(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Page<ContactDto> findAllContacts(int page, int size) {
        //Create pageable to pass as query parameter
        Pageable pageable = PageRequest.of(page, size);
        //Returns all paged contacts
        return contactRepository.findAll(pageable)
                //Convert entity to dto
                .map(contact -> contactMapper.entityToDto(contact));
    }

    @Override
    public ContactDto findContactById(Long id) {
        //Searches for a contact by ID, if not found it throws a handled exception
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No contact found for this ID"));
        //Returns a contact
        return contactMapper.entityToDto(contact);
    }

    @Override
    public Page<ContactDto> findContactsByParameter(String parameter, int page, int size) {
        //Create pageable to pass as query parameter
        Pageable pageable = PageRequest.of(page, size);
        //Returns all paged contacts found according to the given parameter
        return contactRepository.findContactByParameter(parameter, pageable)
                //Convert entity to dto
                .map(contact -> contactMapper.entityToDto(contact));
    }

    @Override
    public String saveContact(ContactDto contactDto) {
        //Check if there is already a contact with the email provided in the body - email must be unique
        if(contactRepository.existsByEmail(contactDto.email())){
            //If exists throws a handled exception
            throw new DuplicateDataException("Email in use for another contact: " + contactDto.email());
        }
        //Check if there is already a contact with the phone provided in the body - phone must be unique
        if(contactRepository.existsByPhone(contactDto.phone())){
            //If exists throws a handled exception
            throw new DuplicateDataException("Phone in use for another contact: " + contactDto.phone());
        }
        //Convert dto to entity
        Contact contact = contactMapper.dtoToEntity(contactDto);
        //Save contact
        contactRepository.save(contact);
        return "Saved Successfully";
    }

    @Override
    public String updateContact(Long id, ContactDto contactDto) {
        //Searches for a contact by ID to update, if not found it throws a handled exception
        Contact contactToUpdate = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No contacts found for this ID!"));

        //Check if there is already a contact with the email provided in the body
        if(contactRepository.existsByEmail(contactDto.email())){
            //Search contact ID by email
            Long contactId = contactRepository.findContactByEmail(contactDto.email()).getId();
            //Compares whether the registered email belongs to the contact to be updated using the ID, if not, it throws a handled exception
            if(contactId != contactToUpdate.getId()){
                throw new DuplicateDataException("Email in use for another contact: " + contactDto.email());
            }
        }
        //Check if there is already a contact with the phone provided in the body
        if(contactRepository.existsByPhone(contactDto.phone())){
            //Search contact ID by phone
            Long contactId = contactRepository.findContactByPhone(contactDto.phone()).getId();
            //Compares whether the registered phone belongs to the contact to be updated using the ID, if not, it throws a handled exception
            if(contactId != contactToUpdate.getId()){
                throw new DuplicateDataException("Phone in use for another contact: " + contactDto.phone());
            }
        }
        //Convert dto to entity
        Contact contactUpdated = contactMapper.dtoToEntity(contactDto);
        contactUpdated.setId(contactToUpdate.getId());
        //Save the updated contact
        contactRepository.save(contactUpdated);
        return "Updated successfully";
    }

    @Override
    public void deleteContact(Long id) {
        //Searches for a contact by ID to delete, if not found it throws a handled exception
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No contact found for this ID"));
        contactRepository.deleteById(id);
    }
}
