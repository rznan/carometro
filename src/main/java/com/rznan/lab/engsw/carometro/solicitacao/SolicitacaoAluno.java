package com.rznan.lab.engsw.carometro.solicitacao;

import com.rznan.lab.engsw.carometro.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "SolicitacaoAluno")
@Table(name = "SolicitacaoAluno")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitacaoAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    // Relacionamento com a entidade Aluno (dados preenchidos pelo aluno na solicitação)
    @OneToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id")
    private Aluno aluno;

    // Status da solicitação (pendente por padrão)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;


}

