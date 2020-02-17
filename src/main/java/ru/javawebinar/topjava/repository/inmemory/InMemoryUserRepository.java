package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    //private AtomicInteger counter = new AtomicInteger(UserUtil.NOT_EXIST_USER_ID);

    {
        UserUtil.USERS.forEach(this::save);
    }


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(UserUtil.getCounter().incrementAndGet());
            log.debug("add user id: {}", UserUtil.getCounter().get());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, existUser) -> user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        //return (List<User>) repository.values();
        return  UserUtil.getTos(repository.values());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return UserUtil.findByEmail(repository.values(), email);
    }
}
