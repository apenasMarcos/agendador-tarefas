-- data.sql
-- Criação da tabela TAREFA, caso não exista
CREATE TABLE IF NOT EXISTS tarefa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    custo DECIMAL(10, 2) NOT NULL,
    data_limite DATE NOT NULL,
    ordem INT NOT NULL
);

-- Criação de algumas tarefas iniciais
INSERT INTO tarefa (nome, custo, data_limite, ordem) VALUES
('Comprar ingredientes para o jantar', 50.00, '2024-11-06', 1),
('Finalizar relatório de vendas', 200.00, '2024-11-07', 2),
('Estudar para a prova de Java', 0.00, '2024-11-10', 3),
('Marcar consulta médica', 150.00, '2024-11-12', 4),
('Comprar presente de aniversário', 120.00, '2024-11-15', 5),
('Comprar xbox para meu sobrinho', 2575.62, '2024-12-15', 6);
