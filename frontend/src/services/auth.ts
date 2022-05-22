import instance from '../models/axios';


export const login = async (username: string, password: string) => {
  try {
    const response = await instance.post('login');
    localStorage.setItem('user', JSON.stringify(response.data));
  } catch (error) {
    console.log(error)
  }
}

export const logout = () => {
  localStorage.removeItem('user');
}

export const getUser = () => {
  const user = localStorage.getItem('user');
  return user ? JSON.parse(user) : {};
}
