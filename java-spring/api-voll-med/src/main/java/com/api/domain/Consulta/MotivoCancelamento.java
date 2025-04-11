package com.api.domain.Consulta;

public enum MotivoCancelamento {
    PacienteDesistiu("Paciente desistiu"),
    MedicoCancelou("Médico cancelou");

    private String descricao;

    private MotivoCancelamento(String descricao){
        this.descricao = descricao;
    }

    public static MotivoCancelamento fromString(String text) {
        text = text.split(",")[0].trim();
        for (MotivoCancelamento motivo : MotivoCancelamento.values()) {
            if (motivo.descricao.equalsIgnoreCase(text)) {
                return motivo;
            }
        }
        throw new IllegalArgumentException("Nenhuma motivo encontrado para a string fornecida: " + text);
    }
}
