import { Link } from 'react-router-dom';
import { MovieCardMode } from '../models/types';

export interface MovieCardProps {
  id: number,
  name: string,
  poster: string,
  duration: number,
  mode: MovieCardMode
}

export const MovieCard = ({ id, name, poster, duration, mode }: MovieCardProps) => {
  return (
    <Link to={`/pa165/movie/${id}`} className={`rounded-lg my-4 ${ mode === MovieCardMode.LandingPage ? "basis-1/5 h-96" : "basis-1/3 h-64"}`}>
      <div className="w-full h-5/6">
        <img className="w-full h-full rounded-t-lg" src={poster} alt="Poster"/>
      </div>
      <p className="px-2 truncate w-36"><b>{name}</b></p>
      <p className="px-2">Duration: {duration} minutes</p>
    </Link>
  );
};

export default MovieCard;
