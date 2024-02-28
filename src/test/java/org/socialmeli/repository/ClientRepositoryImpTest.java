package org.socialmeli.repository;

import org.junit.jupiter.api.Test;
import org.socialmeli.repository.implementation.ClientRepositoryImp;
import org.socialmeli.util.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientRepositoryImpTest {

    //@Autowired
    //ClientRepositoryImp clientRepositoryImp;

    //ObjectFactory objectFactory = new ObjectFactory();
//
    //@Test
    //void findExistingClientOkTest() {
    //     Integer id = objectFactory.getValidClientId();
    //
    //}
//
    //@Test
    //void findNonExistingClientOkTest() {
    //    Integer id = objectFactory.getInvalidUserId();
    //}

}
