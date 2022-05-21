import useSWR from 'swr';
import Header from './Header';
import MovieCard from './MovieCard';
import fetcher from '../models/fetcher';
import { MovieCardMode } from '../models/types';
import { Link } from 'react-router-dom';

export const LandingPage = () => {
  const { data, error } = useSWR('movies', fetcher);

  if (error) return <div>failed to load</div>;
  if (!data) return <div>loading...</div>;

  return (
    <div className="h-screen">
      <Header/>
      <main className="flex flex-wrap p-20">
        {data.map((movie: any) => <MovieCard key={movie.id} {...movie} mode={MovieCardMode.LandingPage}/>)}
      </main>
    </div>
  );
};

export default LandingPage;
