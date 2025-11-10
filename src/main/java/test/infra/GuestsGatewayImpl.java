package test.infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import test.domain.Guest;
import test.domain.Status;
import test.entities.GuestEntity;
import test.gateway.GuestsGateway;

import java.util.List;

@ApplicationScoped
public class GuestsGatewayImpl implements GuestsGateway {

    private final GuestsRepository guestsRepository;

    @Inject
    public GuestsGatewayImpl(GuestsRepository guestsRepository) {
        this.guestsRepository = guestsRepository;
    }

    @Override
    @Transactional
    public Guest save(Guest guest) {
        GuestEntity entity = GuestEntity.fromDomain(guest);
        guestsRepository.persistAndFlush(entity);
        return entity.toDomain();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Guest> findAll() {
        return guestsRepository.listAll().stream()
                .map(GuestEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Guest> findByStatus(Status status) {
        return guestsRepository.find("status", status).list().stream()
                .map(GuestEntity::toDomain)
                .toList();
    }
}
