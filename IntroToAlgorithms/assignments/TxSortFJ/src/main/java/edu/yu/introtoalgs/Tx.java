package edu.yu.introtoalgs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Tx extends TxBase {
    /**
     * Constructor.
     *
     * @param sender   non-null initiator of the transaction
     * @param receiver non-null recipient
     * @param amount   positive-integer-valued amount transfered in the
     *                 transaction.
     */
    private final Account sender;
    private final Account receiver;
    private final int amount;
    private LocalDateTime localDateTime;
    private final long Id;

    public Tx(Account sender, Account receiver, int amount) {
        super(sender, receiver, amount);
        validate(sender, receiver, amount);
        this.sender = sender;
        this.receiver =receiver;
        this.amount = amount;
        this.localDateTime = generateLocalDateTime();
        this.Id = generateId();

    }

    private long generateId() {
        Random random = new Random();
        return 10000000 + random.nextInt(90000000);
    }

    private LocalDateTime generateLocalDateTime() {
        return LocalDateTime.now();
    }

    private void validate(Account sender, Account receiver, int amount) {
        if(sender == null)
            throw new IllegalArgumentException();
        if(receiver == null)
            throw new IllegalArgumentException();
        if(amount < 0)
            throw new IllegalArgumentException();
    }

    @Override
    public Account receiver() {
        return this.receiver;
    }

    @Override
    public Account sender() {
        return  this.sender;
    }

    @Override
    public int amount() {
        return this.amount;
    }

    /**
     * Returns a unique non-negative identifier.
     */
    @Override
    public long id() {
        return this.Id;
    }

    /**
     * Returns the time that the Tx was created or null.
     */
    @Override
    public LocalDateTime time() {
        return this.localDateTime;
    }

    /**
     * Returns the time that the Tx was created or null.
     */
    @Override
    public void setTimeToNull() {
        this.localDateTime = null;

    }

    @Override
    public int compareTo(TxBase o) {
        if(this.localDateTime == null && o.time() == null)
            return 0;
        if(this.localDateTime == null)
            return -1;
        if(o.time() == null)
            return 1;

        return this.localDateTime.compareTo(o.time());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tx tx = (Tx) o;
        return amount == tx.amount && Id == tx.Id  && Objects.equals(localDateTime, tx.localDateTime);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, amount, localDateTime, Id);
    }
}
