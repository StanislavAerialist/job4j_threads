package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        boolean rsl = false;
        if (!accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean update(Account account) {
        boolean rsl = false;
        if (accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean delete(int id) {
        boolean rsl = false;
        if (accounts.containsKey(id)) {
            accounts.remove(id);
            rsl = true;
        }
        return rsl;
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> rsl = Optional.empty();
        if (accounts.containsKey(id)) {
            rsl = Optional.of(accounts.get(id));
        }
        return rsl;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        if (accounts.containsKey(fromId)
                && accounts.containsKey(toId)
                && (accounts.get(fromId).amount() - amount >= 0)) {
            accounts.put(fromId, new Account(fromId, accounts.get(fromId).amount() - amount));
            accounts.put(toId, new Account(toId, accounts.get(toId).amount() + amount));
            rsl = true;
        }
        return rsl;
    }
}