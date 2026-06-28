package br.com.abracocontabilidade.bolsa.mapper;

import br.com.abracocontabilidade.bolsa.dto.TransacaoDto;
import br.com.abracocontabilidade.bolsa.entity.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public TransacaoDto paraDto(Transacao transacao) {
        if (transacao == null) {
            return null;
        }

        return TransacaoDto.builder()
                .id(transacao.getId())
                .usuarioId(transacao.getUsuarioId())
                .ativoId(transacao.getAtivo() != null ? transacao.getAtivo().getId() : null)
                .quantidade(transacao.getQuantidade())
                .preco(transacao.getPreco())
                .dataHora(transacao.getDataHora())
                .corretora(transacao.getCorretora())
                .tipo(transacao.getTipo())
                .build();
    }

    public Transacao paraEntidade(TransacaoDto dto) {
        if (dto == null) {
            return null;
        }

        return Transacao.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .quantidade(dto.getQuantidade())
                .preco(dto.getPreco())
                .dataHora(dto.getDataHora())
                .corretora(dto.getCorretora())
                .tipo(dto.getTipo())
                .build();
    }
}
