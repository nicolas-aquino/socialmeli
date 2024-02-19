package org.socialmeli.service;

import org.socialmeli.entity.Client;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
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
    public User getUserById(Integer userId) {
        User user  = clientRepositoryImp.findOne(userId);
        if(user == null){
             user  = vendorRepositoryImp.findOne(userId);
              if (user == null) throw new NotFoundException("El usuario ingresado no existe ");
        }
        return user;
    }

    public Vendor getVendorById(Integer vendorId){
            Vendor vendor = vendorRepositoryImp.findOne(vendorId);
            if(vendor == null ) throw new NotFoundException("El vendedor no existe");
             return vendor;

    }
    public void userFollowVendor(Integer userId, Integer vendorId){

        if(userId.equals(vendorId))  throw new BadRequestException("Un usuario no se puede seguir a si mismo");
        User user = getUserById(userId);
        Vendor vendor = getVendorById(vendorId);


        boolean alreadyFollowed = vendor.getFollowers().stream().anyMatch(u -> u.getUserId().equals(userId));
        if(alreadyFollowed){
                throw new BadRequestException("Un usuario no se puede seguir a si mismo");
        }


        user.getFollowing().add(vendor);
        vendor.getFollowers().add(user);
    }
}
