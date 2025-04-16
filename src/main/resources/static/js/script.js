const modalElement = document.getElementById('editModal');
const modal = new bootstrap.Modal(modalElement);

document.querySelectorAll('.edit-btn').forEach(function(button) {
    button.onclick = function() {
        const tarefaId = button.getAttribute('data-id');
        const tarefaNome = button.getAttribute('data-nome');
        const tarefaCusto = button.getAttribute('data-custo');
        const tarefaDataLimite = button.getAttribute('data-datalimite');

        document.getElementById('modal-id').value = tarefaId;
        document.getElementById('modal-nome').value = tarefaNome;
        document.getElementById('modal-custo').value = tarefaCusto;
        document.getElementById('modal-dataLimite').value = tarefaDataLimite;

        modal.show();
    };
});

const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');
const modalTaskName = document.getElementById('modal-task-name');

let tarefaIdToDelete = null;

function openDeleteModal(button) {
    const tarefaId = button.getAttribute('data-id');
    modalTaskName.innerText = button.getAttribute('data-nome');

    tarefaIdToDelete = tarefaId;

    deleteModal.show();
}

async function confirmDelete() {
    if (!tarefaIdToDelete) {
        return;
    }

    try {
        const response = await fetch(`/tarefas/excluir/${tarefaIdToDelete}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            const tarefaRow = document.querySelector(`#task-${tarefaIdToDelete}`);
            if (tarefaRow) {
                tarefaRow.remove();
            }

            deleteModal.hide();
        } else {
            alert('Erro ao excluir a tarefa.');
        }
    } catch (error) {
        console.error('Erro ao excluir a tarefa:', error);
        alert('Erro ao excluir a tarefa.');
    }
}


document.querySelectorAll('.delete-btn').forEach(function(button) {
    button.addEventListener('click', function() {
        openDeleteModal(button);
    });
});

confirmDeleteBtn.addEventListener('click', confirmDelete);

deleteModal._element.addEventListener('hidden.bs.modal', function () {
    tarefaIdToDelete = null;
});

document.querySelectorAll('.btn-up').forEach(button => {
    button.addEventListener('click', async (e) => {
        const taskId = e.target.getAttribute('data-id');
        const response = await fetch(`/tarefas/subir/${taskId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            location.reload();
        }
    });
});

document.querySelectorAll('.btn-down').forEach(button => {
    button.addEventListener('click', async (e) => {
        const taskId = e.target.getAttribute('data-id');
        const response = await fetch(`/tarefas/descer/${taskId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            location.reload();
        }
    });
});
