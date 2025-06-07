package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SolicitacaoAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataSolicitacao;

    // Relacionamento com a entidade Aluno (dados preenchidos pelo aluno na solicitação)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    // Status da solicitação (pendente por padrão)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;


}

