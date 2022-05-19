import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LandingPage from './LandingPage';
import Movie from './Movie';

export const Pages = () => (
  <BrowserRouter>
    <Routes>
      <Route path="pa165" element={<LandingPage/>}/>
      <Route path="pa165/movie/:id" element={<Movie/>}/>
    </Routes>
  </BrowserRouter>
);

export default Pages;
