package npaepke.haushaltsbuch.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HB_Purpose")
public class Purpose {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_uuid")
    private Purpose parent;

    @OneToMany
    @JoinColumn(name = "parent_uuid")
    private List<Purpose> childPurposes;
}
