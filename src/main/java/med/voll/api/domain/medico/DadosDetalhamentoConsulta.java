package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,
    Long idMedico,
    Long idPaciente,
    LocalDateTime data,
    Especialidade especialidade
    ){}