//package com.crm.Crm.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.Optional;
//
//import com.crm.Crm.dto.ContactDto;
//import com.crm.Crm.entity.Contact;
//import com.crm.Crm.mapper.ContactMapper;
//import com.crm.Crm.repository.ContactRepository;
//import com.crm.Crm.service.impl.ContactServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ContactServiceImplTest {
//
//    @Mock
//    private ContactRepository contactRepository;
//
//    @Mock
//    private ContactMapper contactMapper;
//
//    @InjectMocks
//    private ContactServiceImpl contactService;
//
//    @Test
//    public void deleteContactTest(){
//        // setup
//        Long id=1L;
//        Contact contact=new Contact();
//        contact.setId(id);
//        // mocking
//        when(contactRepository.findById(id)).thenReturn(Optional.of(contact));
//        // Test
//        contactService.deleteContact(id);
//        // verify
//        verify(contactRepository,times(1)).deleteById(id);
//    }
//
//    @Test
//    public void testUpdateContactDetails() {
//        // Setup
//        Long id = 1L;
//        ContactDto contactDto = new ContactDto();
//        contactDto.setFirstName("John");
//        contactDto.setLastName("Doe");
//        contactDto.setAddress("123 Main St");
//        contactDto.setEmail("john.doe@example.com");
//
//        Contact existingContact = new Contact();
//        existingContact.setId(id);
//        existingContact.setFirstName("Jane");
//        existingContact.setLastName("Doe");
//        existingContact.setAddress("456 Second St");
//        existingContact.setEmail("jane.doe@example.com");
//
//        Contact updatedContact = new Contact();
//        updatedContact.setId(id);
//        updatedContact.setFirstName(contactDto.getFirstName());
//        updatedContact.setLastName(contactDto.getLastName());
//        updatedContact.setAddress(contactDto.getAddress());
//        updatedContact.setEmail(contactDto.getEmail());
//
//        ContactDto updatedContactDto = new ContactDto();
//        updatedContactDto.setId(id);
//        updatedContactDto.setFirstName(updatedContact.getFirstName());
//        updatedContactDto.setLastName(updatedContact.getLastName());
//        updatedContactDto.setAddress(updatedContact.getAddress());
//        updatedContactDto.setEmail(updatedContact.getEmail());
//
//        // Mocking
//        when(contactRepository.findById(id)).thenReturn(Optional.of(existingContact));
//        when(contactRepository.save(existingContact)).thenReturn(updatedContact);
//        when(contactMapper.toDto(updatedContact)).thenReturn(updatedContactDto);
//
//
//        // Test
//        ContactDto result = contactService.updateContactDetails(id, contactDto);
//
//        // Verify
//        assertEquals(id, result.getId());
//        assertEquals(contactDto.getFirstName(), result.getFirstName());
//        assertEquals(contactDto.getLastName(), result.getLastName());
//        assertEquals(contactDto.getAddress(), result.getAddress());
//        assertEquals(contactDto.getEmail(), result.getEmail());
//    }
//}
