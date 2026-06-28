package br.com.abracocontabilidade.bolsa.mapper;

import br.com.abracocontabilidade.bolsa.dto.AtivoDto;
import br.com.abracocontabilidade.bolsa.entity.Ativo;
import org.springframework.stereotype.Component;

@Component
public class AtivoMapper {

    public AtivoDto paraDto(Ativo ativo) {
        if (ativo == null) {
            return null;
        }

        return AtivoDto.builder()
                .id(ativo.getId())
                .ticker(ativo.getTicker())
                .nome(ativo.getNome())
                .setor(ativo.getSetor())
                .precoAtual(ativo.getPrecoAtual())
                .dataAtualizacao(ativo.getDataAtualizacao())
                .build();
    }

    public Ativo paraEntidade(AtivoDto dto) {
        if (dto == null) {
            return null;
        }

        return Ativo.builder()
                .id(dto.getId())
                .ticker(dto.getTicker())
                .nome(dto.getNome())
                .setor(dto.getSetor())
                .precoAtual(dto.getPrecoAtual())
                .dataAtualizacao(dto.getDataAtualizacao())
                .build();
    }
}
