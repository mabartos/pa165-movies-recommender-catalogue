import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LandingPage from './LandingPage';
import Movie from './Movie';
import PageNotFound from './PageNotFound';

export const Pages = () => (
  <BrowserRouter>
    <Routes>
      <Route path="pa165" element={<LandingPage/>}/>
      <Route path="pa165/movie/:id" element={<Movie/>}/>
      <Route path="*" element={<PageNotFound />}/>
    </Routes>
  </BrowserRouter>
);

export default Pages;
