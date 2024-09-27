package spb.cloud.general.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import spb.cloud.general.model.dto.ClientDto;
import spb.cloud.general.model.dto.mapper.ClientMapper;
import spb.cloud.general.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<ResponseEntity<ClientDto>> createClient(@RequestBody ClientDto clientDto) {
        log.info("Request received: Create new client: {}", clientDto);
        return clientService.createClient(clientMapper.toEntity(clientDto))
            .map(client -> ResponseEntity.ok().body(clientMapper.toDto(client)));
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(OK)
    public Mono<ResponseEntity<ClientDto>> getClientById(@PathVariable Long clientId) {
        log.info("Request received: Get client with id: {}", clientId);
        return clientService.getClientById(clientId)
            .map(client -> ResponseEntity.ok().body(clientMapper.toDto(client)));
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(OK)
    public Mono<ResponseEntity<ClientDto>> updateClient(@PathVariable Long clientId, @RequestBody ClientDto updatedClientDto) {
        log.info("Request received: Update client: [{}], Original Id: [{}]", updatedClientDto, clientId);
        return clientService.updateClient(clientId, clientMapper.toEntity(updatedClientDto))
            .map(client -> ResponseEntity.ok().body(clientMapper.toDto(client)));
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(OK)
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable Long clientId) {
        log.info("Request received: Delete client with id: {}", clientId);
        return clientService.deleteClient(clientId)
            .then(Mono.fromCallable(() -> ResponseEntity.ok().build()));
    }


}
