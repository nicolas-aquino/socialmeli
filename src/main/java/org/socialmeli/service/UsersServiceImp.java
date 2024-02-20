package org.socialmeli.service;

import org.socialmeli.dto.response.FollowerCountDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.socialmeli.entity.Client;
import org.socialmeli.entity.User;
import org.socialmeli.entity.Vendor;
import org.socialmeli.exception.BadRequestException;
import org.socialmeli.exception.NotFoundException;
import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.VendorsFollowingListDto;
import org.socialmeli.repository.ClientRepositoryImp;
import org.socialmeli.repository.VendorRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

@Service
public class UsersServiceImp implements IUsersService {
    @Autowired
    ClientRepositoryImp clientRepositoryImp;

    @Autowired
    VendorRepositoryImp vendorRepositoryImp;

    //ObjectMapper mapper = new ObjectMapper();

    @Override
    public User getUserById(Integer userId) {
        User user  = clientRepositoryImp.findOne(userId);
        if(user == null) {
             user  = vendorRepositoryImp.findOne(userId);
              if (user == null) throw new NotFoundException("El usuario ingresado no existe ");
        }
        return user;
    }

    public Vendor getVendorById(Integer vendorId) {
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
    public VendorFollowersListDTO getFollowersList(UserIdDto userId, String order) {
        Vendor vendor = vendorRepositoryImp.findOne(userId.getUserId());
        if (vendor == null) {
            throw new NotFoundException(String.format("No se encontró un usuario con id %d", userId.getUserId()));
        }

        List<User> followerUsers = vendor.getFollowers();

        switch (order){
            case "name_asc" -> followerUsers = ordenarListaUsuariosPor(followerUsers, comparing(User::getUserName));
                //break;
            case "name_desc" -> followerUsers = ordenarListaUsuariosPor(followerUsers, comparing(User::getUserName).reversed());
                //break;
            default -> throw new BadRequestException("El ordenamiento pedido es inválido");
        }

        return new VendorFollowersListDTO(vendor, followerUsers);
    }

    private List<User> ordenarListaUsuariosPor(List<User> followerUsers, Comparator<User> comparing) {
        return followerUsers.stream().sorted(comparing).toList();
    }

    @Override
    public VendorsFollowingListDto getFollowingList(UserIdDto userIdDto, String order) {
        Client client = clientRepositoryImp.findOne(userIdDto.getUserId());
        Vendor vendor = vendorRepositoryImp.findOne(userIdDto.getUserId());

        VendorsFollowingListDto clientFollowing = getVendorsFollowingListDto(order, client);
        if (clientFollowing != null) return clientFollowing;

        VendorsFollowingListDto vendorFollowing = getVendorsFollowingListDto(order, vendor);
        if (vendorFollowing != null) return vendorFollowing;

        throw new NotFoundException(String.format("No se encontró un usuario con el ID %d.", userIdDto.getUserId()));
    }

    private VendorsFollowingListDto getVendorsFollowingListDto(String order, User user) {
        if (user != null) {
            List<Vendor> following = user.getFollowing();
            switch (order) {
                case "name_asc":
                    following = following.stream().sorted(comparing(User::getUserName)).toList();
                    break;
                case "name_desc":
                    following = following.stream().sorted(comparing(User::getUserName).reversed()).toList();
                    break;
                default:
                    throw new BadRequestException("El ordenamiento pedido es inválido");
            }
            return new VendorsFollowingListDto(user.getUserId(), user.getUserName(), following);
        }
        return null;
    }
}
