import { render, screen } from '@testing-library/react';
import App from './app/App';

test('renders tarefas title', () => {
  render(<App />);
  const title = screen.getByText(/Minhas Tarefas/i);
  expect(title).toBeInTheDocument();
});
