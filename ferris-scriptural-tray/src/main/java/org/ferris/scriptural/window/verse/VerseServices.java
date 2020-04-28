package org.ferris.scriptural.window.verse;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.ferris.scriptural.window.initialization.InitializationEvent;
import org.slf4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class VerseServices {

    @Inject
    protected Logger log;

    @Inject
    protected VerseRepository repository;

    protected SecureRandom random;
    protected List<Verse> unpicked, picked;


    protected void init(@Observes @Priority(20) InitializationEvent evnt) {
        log.info("Initialize verse services");

        // Random
        random = new SecureRandom();

        // picked
        picked = new LinkedList<>();

        // unpicked
        unpicked = repository.findAll();
        unpicked.forEach(v -> log.info(v.toString()));
    }

    private void reset() {
        if (unpicked.isEmpty()) {
            log.info("All verses have been picked, let's start again");
            unpicked.addAll(picked);
            picked.clear();
        }
    }

    public Optional<Verse> pick() {
        // Reset if needed
        reset();

        // To return
        Optional<Verse> retval = Optional.empty();

        if (unpicked.isEmpty()) {
            log.info("Verse list is empty. Cannot pick next verse.");
        } else {
            log.info(String.format("Pick next verse from list of %d", unpicked.size()));
            // Let:
            // unpicked.size() == 12
            //
            // Then:
            // nextInt(12) returns lowest 0 and highest 11
            int next = this.random.nextInt(unpicked.size());

            // unpicked[0]  == first element
            // unpicked[11] == last element
            Verse v = unpicked.remove(next);

            // Add to the picked list
            picked.add(v);

            // Return it
            retval = Optional.of(v);
        }

        // Return
        return retval;
    }
}
