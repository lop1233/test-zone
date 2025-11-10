package test.infra;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import test.entities.GuestEntity;

@ApplicationScoped
public class GuestsRepository implements PanacheRepositoryBase<GuestEntity, Long> {
}
