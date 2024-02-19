package org.socialmeli.service;

import org.socialmeli.dto.VendorFollowersListDTO;
import org.socialmeli.dto.request.UserIdDto;
import org.socialmeli.dto.response.MessageDTO;
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

    @Override
    public MessageDTO unfollowVendor(UserIdDto userIdDto, UserIdDto vendorIdDto) {
        Integer userId = userIdDto.getUserId();
        Integer vendorId = vendorIdDto.getUserId();

        Client userClient = clientRepositoryImp.findOne(userId);
        Vendor userVendor = vendorRepositoryImp.findOne(userId);

        if (userClient == null && userVendor == null) {
             throw new NotFoundException("No se encontró el usuario con id " + userId);
        }
        if (userClient != null) {
            boolean removed = userClient.getFollowing().removeIf(v -> v.getUserId().equals(vendorId));
            if (!removed)
                throw new NotFoundException("No se encontro el vendedor con id " + vendorId);
        }
        if (userVendor != null) {
            boolean removedFollowing = userVendor.getFollowing().removeIf(v -> v.getUserId().equals(vendorId));
            boolean removedFollower = userVendor.getFollowers().removeIf(u -> u.getUserId().equals(userId));
            if (!removedFollowing)
                throw new NotFoundException("No se encontro el vendedor con id " + vendorId);
            if (!removedFollower)
                throw new NotFoundException("No se encontro el seguidor con id " + userClient
                        + "en los seguidores del vendedor con id " + vendorId);
        }

        return new MessageDTO("Usuario" + userId + "ha dejado de seguir al vendedor con id " + vendorId);
    }
}
