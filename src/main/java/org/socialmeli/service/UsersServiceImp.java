package org.socialmeli.service;

import org.socialmeli.dto.response.FollowerCountDto;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.entity.Client;
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
                throw new BadRequestException("Ya se esta siguiendo al vendedor");
        }


        user.getFollowing().add(vendor);
        vendor.getFollowers().add(user);
    }

    @Override
    public FollowerCountDto vendorFollowersCount(Integer userId) {
        Vendor vendor = vendorRepositoryImp.findOne(userId);

        if(vendor == null) throw new NotFoundException(String.format("No se encontró un usuario con id %d", userId));


        return  new FollowerCountDto(userId,vendor.getUserName(),vendor.getFollowers().size());
    }

    @Override
    public VendorFollowersListDTO getFollowersList(Integer userId) {
        Vendor vendor = vendorRepositoryImp.findOne(userId);
        if (vendor == null) {
            throw new NotFoundException(String.format("No se encontró un usuario con id %d", userId));
        }
        return new VendorFollowersListDTO(vendor);
    }

    @Override
    public VendorsFollowingListDto getFollowingList(UserIdDto userIdDto) {
        Client client = clientRepositoryImp.findOne(userIdDto.getUserId());
        Vendor vendor = vendorRepositoryImp.findOne(userIdDto.getUserId());

        if (client != null) {
            return new VendorsFollowingListDto(client.getUserId(), client.getFollowing());
        }

        if (vendor != null) {
            return new VendorsFollowingListDto(vendor.getUserId(), vendor.getFollowing());
        }

        throw new NotFoundException("No existe ningún usuario con ese ID.");
    }
}
