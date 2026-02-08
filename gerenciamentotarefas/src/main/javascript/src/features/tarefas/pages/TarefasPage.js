import { useEffect, useState } from 'react';
import TarefaForm from '../components/TarefaForm';
import TarefaList from '../components/TarefaList';
import EmptyState from '../../../shared/components/EmptyState';
import {
  criarTarefa,
  atualizarTarefa,
  concluirTarefa,
  excluirTarefa,
  listarTarefas,
} from '../services/tarefasService';

const initialForm = {
  titulo: '',
  descricao: '',
  status: 'PENDENTE',
  prioridade: 'MEDIA',
};

function TarefasPage() {
  const [tarefas, setTarefas] = useState([]);
  const [pageInfo, setPageInfo] = useState({
    page: 0,
    size: 5,
    totalElements: 0,
    totalPages: 0,
  });
  const [form, setForm] = useState(initialForm);
  const [editingId, setEditingId] = useState(null);
  const [filters, setFilters] = useState({
    titulo: '',
    status: '',
    prioridade: '',
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  async function carregarTarefas(page = pageInfo.page) {
    setLoading(true);
    setError('');
    try {
      const data = await listarTarefas({
        titulo: filters.titulo || undefined,
        status: filters.status || undefined,
        prioridade: filters.prioridade || undefined,
        page,
        size: pageInfo.size,
      });
      setTarefas(data.content);
      setPageInfo({
        page: data.page,
        size: data.size,
        totalElements: data.totalElements,
        totalPages: data.totalPages,
      });
    } catch (err) {
      setError('Não foi possível carregar as tarefas.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    carregarTarefas();
  }, []);

  async function handleSubmit(event) {
    event.preventDefault();
    setError('');
    try {
      if (editingId) {
        await atualizarTarefa(editingId, form);
      } else {
        await criarTarefa(form);
      }
      setForm(initialForm);
      setEditingId(null);
      await carregarTarefas(0);
    } catch (err) {
      setError('Não foi possível salvar a tarefa.');
    }
  }

  async function handleDelete(id) {
    setError('');
    try {
      await excluirTarefa(id);
      await carregarTarefas();
    } catch (err) {
      setError('Não foi possível excluir a tarefa.');
    }
  }

  async function handleConcluir(id) {
    setError('');
    try {
      await concluirTarefa(id);
      await carregarTarefas();
    } catch (err) {
      setError('Não foi possível concluir a tarefa.');
    }
  }

  function handleEdit(tarefa) {
    setEditingId(tarefa.id);
    setForm({
      titulo: tarefa.titulo,
      descricao: tarefa.descricao,
      status: tarefa.status,
      prioridade: tarefa.prioridade,
    });
  }

  function handleCancelEdit() {
    setEditingId(null);
    setForm(initialForm);
  }

  function handleFilterChange(event) {
    const { name, value } = event.target;
    setFilters((prev) => ({ ...prev, [name]: value }));
  }

  function aplicarFiltros() {
    carregarTarefas(0);
  }

  return (
    <section className="tarefas-page">
      <header className="tarefas-header">
        <div>
          <p className="tarefas-eyebrow">Gerenciamento</p>
          <h1 className="tarefas-title">Minhas Tarefas</h1>
          <p className="tarefas-subtitle">
            Crie, organize e acompanhe suas tarefas diárias.
          </p>
        </div>
      </header>

      <div className="tarefas-grid">
        <div className="tarefas-card">
          <h2>Nova tarefa</h2>
          <TarefaForm
            form={form}
            onChange={setForm}
            onSubmit={handleSubmit}
            isEditing={Boolean(editingId)}
            onCancel={handleCancelEdit}
          />
        </div>

        <div className="tarefas-card">
          <h2>Lista de tarefas</h2>
          <div className="tarefas-filters">
            <input
              type="text"
              name="titulo"
              placeholder="Buscar por título"
              value={filters.titulo}
              onChange={handleFilterChange}
            />
            <select name="status" value={filters.status} onChange={handleFilterChange}>
              <option value="">Status</option>
              <option value="PENDENTE">Pendente</option>
              <option value="EM_ANDAMENTO">Em andamento</option>
              <option value="CONCLUIDA">Concluída</option>
            </select>
            <select
              name="prioridade"
              value={filters.prioridade}
              onChange={handleFilterChange}
            >
              <option value="">Prioridade</option>
              <option value="BAIXA">Baixa</option>
              <option value="MEDIA">Média</option>
              <option value="ALTA">Alta</option>
            </select>
            <button className="tarefas-button" type="button" onClick={aplicarFiltros}>
              Filtrar
            </button>
          </div>
          {error && <p className="tarefas-error">{error}</p>}
          {loading ? (
            <p>Carregando...</p>
          ) : tarefas.length === 0 ? (
            <EmptyState
              title="Nenhuma tarefa ainda"
              description="Crie sua primeira tarefa usando o formulário ao lado."
            />
          ) : (
            <>
              <TarefaList
                tarefas={tarefas}
                onDelete={handleDelete}
                onEdit={handleEdit}
                onConcluir={handleConcluir}
              />
              <div className="tarefas-pagination">
                <button
                  className="tarefas-button secondary"
                  type="button"
                  onClick={() => carregarTarefas(pageInfo.page - 1)}
                  disabled={pageInfo.page <= 0}
                >
                  Anterior
                </button>
                <span>
                  Página {pageInfo.page + 1} de {Math.max(pageInfo.totalPages, 1)}
                </span>
                <button
                  className="tarefas-button secondary"
                  type="button"
                  onClick={() => carregarTarefas(pageInfo.page + 1)}
                  disabled={pageInfo.page + 1 >= pageInfo.totalPages}
                >
                  Próxima
                </button>
              </div>
            </>
          )}
        </div>
      </div>
    </section>
  );
}

export default TarefasPage;
