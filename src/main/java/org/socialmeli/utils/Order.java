package org.socialmeli.utils;

import java.util.Comparator;
import java.util.List;

public class Order <E>{


    public  List<E> ordenarListaUsuariosPor(List<E> followerUsers, Comparator<E> comparing) {
        return followerUsers.stream().sorted(comparing).toList();
    }
}

