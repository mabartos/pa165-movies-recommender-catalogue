import instance from '../models/axios';
import jwtDecode from 'jwt-decode';


export const login = async (name: string, password: string) => {
  try {
    const response = await instance.post('login', {
      name, password
    });
    localStorage.setItem('user', JSON.stringify(jwtDecode(response.data)));
  } catch (error) {
    console.log(error)
  }
}

export const logout = () => {
  localStorage.removeItem('user');
}

export const getUser = () => {
  const user = localStorage.getItem('user');
  return user ? JSON.parse(user) : null;
}
