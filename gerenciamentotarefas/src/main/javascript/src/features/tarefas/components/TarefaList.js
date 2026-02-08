function TarefaList({ tarefas, onDelete, onEdit, onConcluir }) {
  return (
    <ul className="tarefas-list">
      {tarefas.map((tarefa) => (
        <li key={tarefa.id} className="tarefas-item">
          <div>
            <h3>{tarefa.titulo}</h3>
            <p>{tarefa.descricao}</p>
            <div className="tarefas-tags">
              <span className="tarefas-tag">{tarefa.status}</span>
              <span className="tarefas-tag">{tarefa.prioridade}</span>
            </div>
          </div>
          <div className="tarefas-item-actions">
            <button
              className="tarefas-link"
              type="button"
              onClick={() => onEdit(tarefa)}
            >
              Editar
            </button>
            <button
              className="tarefas-link"
              type="button"
              onClick={() => onConcluir(tarefa.id)}
            >
              Concluir
            </button>
            <button
              className="tarefas-link danger"
              type="button"
              onClick={() => onDelete(tarefa.id)}
            >
              Excluir
            </button>
          </div>
        </li>
      ))}
    </ul>
  );
}

export default TarefaList;
