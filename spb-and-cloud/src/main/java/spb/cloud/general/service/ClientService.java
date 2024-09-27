package spb.cloud.general.service;

import spb.cloud.general.model.dto.mapper.ClientMapper;
import spb.cloud.general.model.entity.Client;
import spb.cloud.general.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Mono<Client> getClientById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Mono<Client> createClient(Client client) {
        log.info("Creating client: {}", client);
        return clientRepository.save(client);
    }

    public Mono<Client> updateClient(Long clientId, Client client) {
        log.info("Updating client: {}", client);
        return clientRepository.findById(clientId)
            .switchIfEmpty(Mono.error(new RuntimeException("Client not found"))) //TODO: Create custom exception
            .doOnNext(existingClient -> log.info("Client found: {}", existingClient))
            .doOnNext(existingClient -> clientMapper.partialUpdate(client, existingClient))
            .flatMap(clientRepository::save);
    }

    public Mono<Void> deleteClient(Long clientId) {
        return clientRepository.findById(clientId)
            .switchIfEmpty(Mono.error(new RuntimeException("Client not found"))) //TODO: Create custom exception
            .doOnNext(client -> log.info("Deleting client: {}", client))
            .flatMap(clientRepository::delete);
    }
}
