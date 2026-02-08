import api from '../../../services/api';

export async function listarTarefas(params) {
  const response = await api.get('/api/tarefas', { params });
  return response.data;
}

export async function criarTarefa(payload) {
  const response = await api.post('/api/tarefas', payload);
  return response.data;
}

export async function atualizarTarefa(id, payload) {
  const response = await api.put(`/api/tarefas/${id}`, payload);
  return response.data;
}

export async function concluirTarefa(id) {
  const response = await api.patch(`/api/tarefas/${id}/concluir`);
  return response.data;
}

export async function excluirTarefa(id) {
  await api.delete(`/api/tarefas/${id}`);
}
