package com.CockroachDb.service;


import ch.qos.logback.core.net.server.Client;
import com.CockroachDb.Entity.ClientData;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<ClientData> getAccounts();

    Optional<ClientData> getAccountById(long id);

    ClientData create(ClientData account);

    String delete(ClientData account);

    String  update(long id , ClientData data );
}
