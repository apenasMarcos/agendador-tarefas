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
('Renovar assinatura da Netflix', 39.90, '2025-05-02', 1),
('Enviar currículo para novas vagas', 0.00, '2025-05-05', 2),
('Fazer backup do computador', 0.00, '2025-05-15', 3),
('Organizar documentos pessoais', 0.00, '2025-05-20', 4),
('Atualizar portfolio online', 0.00, '2025-05-22', 5),
('Pagar IPVA do carro', 800.00, '2025-05-25', 6),
('Assistir workshop de design UX', 0.00, '2025-05-28', 7),
('Montar móvel novo da sala', 150.00, '2025-05-30', 8),
('Planejar viagem de férias', 5000.00, '2025-06-01', 9),
('Reservar hotel para conferência', 1200.00, '2025-06-10', 10),
('Comprar livros para curso de programação', 300.00, '2025-06-15', 11),
('Fazer revisão geral do carro', 400.00, '2025-06-20', 12),
('Estudar para certificação AWS', 0.00, '2025-06-25', 13),
('Atualizar cadastro bancário', 0.00, '2025-07-01', 14),
('Comprar ingredientes para o jantar', 50.00, '2025-11-06', 15),
('Finalizar relatório de vendas', 200.00, '2025-11-07', 16),
('Estudar para a prova de Java', 0.00, '2025-11-10', 17),
('Marcar consulta médica', 150.00, '2025-11-12', 18),
('Comprar presente de aniversário', 120.00, '2025-11-15', 19),
('Comprar xbox para meu sobrinho', 2575.62, '2025-12-15', 20);