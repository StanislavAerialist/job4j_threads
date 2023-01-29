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
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), accounts.get(account.id()), account);
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id, accounts.get(id));
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);
        if (from != null && to != null && (from.amount() - amount >= 0)) {
            accounts.put(fromId, new Account(fromId, from.amount() - amount));
            accounts.put(toId, new Account(toId, to.amount() + amount));
            rsl = true;
        }
        return rsl;
    }
}