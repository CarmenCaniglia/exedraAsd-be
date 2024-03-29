package carmencaniglia.exedraAsd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "schede_allenamento")
@Getter
@Setter
public class SchedaAllenamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String titolo;
    private String descrizione;

   /* @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;*/
}
