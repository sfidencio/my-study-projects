package com.github.sfidencio.vendas.domain.service.imp;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author sfidencio
 * @date 17/12/2023
 */
@EnableScheduling
@Log4j2
public class CancelaPedidoOldSchedule {

    /**
     * 1000 * 60 * 60 * 24 = 1 dia
     * 1000 * 60 * 60 * 24 * 30 = 1 mes
     * 1000 * 60 * 60 * 24 * 365 = 1 ano
     * fixedDelay ? - Especifica o intervalo entre o término da execução do método e o início da próxima execução
     * fixedRate ? - Especifica o intervalo entre o início da execução do método e o início da próxima execução
     * initialDelay ? - Especifica o intervalo entre a inicialização do contexto do Spring e a primeira execução do método
     * cron ? - Especifica uma expressão cron para definir quando o método deve ser executado
     * zone ? - Especifica a zona de tempo a ser usada para a expressão cron
     * <p>
     * 0 0 12 * * ? - Executa todos os dias as 12:00
     * 0 0 12 * * ? * - Executa todos os dias as 12:00
     * 0 0 12 * * ? 2023 - Executa todos os dias as 12:00 em 2023
     * 0 0 12 * * ? 2023/2 - Executa todos os dias as 12:00 em 2023 e 2025
     * 0 0 12 ? * MON-FRI - Executa de segunda a sexta as 12:00
     * 0 0 12 ? * 6L - Executa no último sábado do mês as 12:00
     * 0 0 12 ? * 6L 2023 - Executa no último sábado do mês as 12:00 em 2023
     * 0 0 12 ? * 6#3 - Executa no terceiro sábado do mês as 12:00
     * 0 0 12 ? * 6#3 2023 - Executa no terceiro sábado do mês as 12:00 em 2023
     * 0 0 12 LW * ? - Executa no último dia útil do mês as 12:00
     * 0 0 12 1L * ? - Executa no último dia do mês as 12:00
     * 0 0 12 1W * ? - Executa no primeiro dia útil do mês as 12:00
     * 0 0 12 ? * 2#1 - Executa no primeiro domingo do mês as 12:00
     * 0 0 12 ? * 2#1 2023 - Executa no primeiro domingo do mês as 12:00 em 2023
     * 0 0 12 ? * 2#2 - Executa no segundo domingo do mês as 12:00
     * </p>
     */
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void cancelarPedido() {
        log.info("Cancelando pedidos acima de 1 ano");
        log.info("Implementar lógica para cancelar pedido");
    }

}
