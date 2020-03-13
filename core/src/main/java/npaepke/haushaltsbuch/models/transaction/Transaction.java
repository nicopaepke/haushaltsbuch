package npaepke.haushaltsbuch.models.transaction;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import npaepke.haushaltsbuch.models.Account;
import npaepke.haushaltsbuch.models.Purpose;

@Getter
@Setter
@Entity
@Table(name = "HB_Transaction")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "purpose")
    private Purpose purpose;

    @ManyToOne
    @JoinColumn(name = "source_account")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target_account")
    private Account targetAccount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private ETransactionType transactionType;

    @Column(name = "value")
    private double value;

    @Column(name = "start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column(name = "end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "receipt_number")
    private int receiptNumber;

    @Column(name = "creation_time_stamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTimeStamp;

}
