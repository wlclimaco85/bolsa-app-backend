package br.com.abracocontabilidade.bolsa.entity;

public enum TipoTransacao {
    COMPRA("Compra"),
    VENDA("Venda");

    private final String label;

    TipoTransacao(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
