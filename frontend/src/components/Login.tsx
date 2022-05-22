import Header from './Header';
import { useState } from 'react';
import { login } from '../services/auth';
import { useNavigate } from 'react-router-dom';

export const Login = () => {
  const navigate = useNavigate();
  const [ name, setName ] = useState<string>('');
  const [ password, setPassword ] = useState<string>('');

  async function handleSubmit(event: any) {
    event.preventDefault();
    const response: any = await login(name, password);
    response.status === 200 ? navigate('/pa165/', { replace: true }) : alert('Invalid credentials');
  }

  return (
    <div className="h-[100vh]">
      <Header/>
      <div className="grid place-items-center h-[90%]">
        <form onSubmit={handleSubmit}
              className="flex flex-col w-1/4 gap-3 border-2 border-black rounded-lg bg-slate-300 p-4">
          <p className="text-2xl text-center font-bold">Login</p>
          <label className="flex justify-between">
            Username:
            <input
              type="text"
              className="border-2 border-black rounded-lg"
              value={name}
              onChange={event => setName(event.target.value)}/>
          </label>
          <label className="flex justify-between">
            Password:
            <input
              type="password"
              className="border-2 border-black rounded-lg"
              value={password}
              onChange={event => setPassword(event.target.value)}
            />
          </label>
          <button className="ml-auto mr-auto border-2 border-black rounded-lg w-max p-2 bg-white">
            Login
          </button>
        </form>
      </div>
    </div>
  );
};
