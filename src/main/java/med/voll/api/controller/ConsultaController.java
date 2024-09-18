package med.voll.api.controller;

import lombok.AllArgsConstructor;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.medico.DadosAgendamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@AllArgsConstructor()
public class ConsultaController {
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    public ResponseEntity agendar(DadosAgendamentoConsulta dados){
        this.agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok("Done!");
    }
}
