package org.socialmeli.service;

import org.socialmeli.dto.UserDTO;
import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.repository.ClientRepositoryImp;
import org.socialmeli.repository.VendorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements IUsersService {
    @Autowired
    ClientRepositoryImp clientRepositoryImp;

    @Autowired
    VendorRepositoryImp vendorRepositoryImp;

    @Override
    public VendorFollowersListDTO getFollowersList(Integer userId) {
        Vendor vendor = vendorRepositoryImp.findOne(userId);
        if (vendor == null) {
            throw new NotFoundException(String.format("No se encontró un usuario con id %d", userId));
        }
        return new VendorFollowersListDTO(vendor);
    }
}
