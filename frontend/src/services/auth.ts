import instance from '../models/axios';
import jwtDecode from 'jwt-decode';

export const login = async (name: string, password: string) => {
  try {
    const response = await instance.post('login', {
      name, password
    });
    sessionStorage.setItem('user', JSON.stringify(jwtDecode(response.data)));
    return response;
  } catch (error) {
    return error;
  }
}

export const logout = () => {
  sessionStorage.removeItem('user');
}

export const getUser = () => {
  const user = sessionStorage.getItem('user');
  return user ? JSON.parse(user) : null;
}
