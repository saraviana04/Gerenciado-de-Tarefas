const statusOptions = [
  { value: 'PENDENTE', label: 'Pendente' },
  { value: 'EM_ANDAMENTO', label: 'Em andamento' },
  { value: 'CONCLUIDA', label: 'Concluída' },
];

const prioridadeOptions = [
  { value: 'BAIXA', label: 'Baixa' },
  { value: 'MEDIA', label: 'Média' },
  { value: 'ALTA', label: 'Alta' },
];

function TarefaForm({ form, onChange, onSubmit, isEditing, onCancel }) {
  function handleChange(event) {
    const { name, value } = event.target;
    onChange((prev) => ({ ...prev, [name]: value }));
  }

  return (
    <form className="tarefas-form" onSubmit={onSubmit}>
      <label className="tarefas-field">
        Título
        <input
          type="text"
          name="titulo"
          value={form.titulo}
          onChange={handleChange}
          placeholder="Ex: Estudar Spring Boot"
          required
        />
      </label>

      <label className="tarefas-field">
        Descrição
        <textarea
          name="descricao"
          value={form.descricao}
          onChange={handleChange}
          placeholder="Detalhes da tarefa"
          rows={3}
          required
        />
      </label>

      <label className="tarefas-field">
        Status
        <select name="status" value={form.status} onChange={handleChange}>
          {statusOptions.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </select>
      </label>

      <label className="tarefas-field">
        Prioridade
        <select
          name="prioridade"
          value={form.prioridade}
          onChange={handleChange}
        >
          {prioridadeOptions.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </select>
      </label>

      <div className="tarefas-actions">
        <button className="tarefas-button" type="submit">
          {isEditing ? 'Atualizar tarefa' : 'Salvar tarefa'}
        </button>
        {isEditing && (
          <button className="tarefas-button secondary" type="button" onClick={onCancel}>
            Cancelar
          </button>
        )}
      </div>
    </form>
  );
}

export default TarefaForm;
