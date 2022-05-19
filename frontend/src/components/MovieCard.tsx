import { Link } from 'react-router-dom';

export interface MovieCardProps {
  id: number,
  name: string,
  poster: string,
  duration: number,
}

export const MovieCard = ({ id, name, poster, duration }: MovieCardProps) => {
  return (
    <Link to={`/pa165/movie/${id}`} className="basis-1/5 rounded-lg h-96">
      <div className="w-full h-5/6">
        <img className="w-full h-full rounded-t-lg" src={poster} alt="Poster"/>
      </div>
      <p className="px-2"><b>{name}</b></p>
      <p className="px-2">Duration: {duration} minutes</p>
    </Link>
  );
};

export default MovieCard;
