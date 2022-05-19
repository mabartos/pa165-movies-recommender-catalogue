import Header from './Header';
import MovieCard from './MovieCard';
import { MovieCardMode } from '../models/types';

export const Movie = () => {
  //TODO Tahanie informacii
  const movie = {
    movie: 'Doctor Strange in the Multiverse of Madness',
    poster: 'https://m.media-amazon.com/images/M/MV5BNWM0ZGJlMzMtZmYwMi00NzI3LTgzMzMtNjMzNjliNDRmZmFlXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_.jpg',
    duration: 126,
    description: 'Doctor Strange teams up with a mysterious teenage girl from his dreams who can travel across multiverses, to battle multiple threats, including other-universe versions of himself, which threaten to wipe out millions across the multiverse. They seek help from Wanda the Scarlet Witch, Wong and others.',
    director: 'Sam Raimi',
    actors: [ 'Benedict Cumberbatch', 'Elizabeth Olsen', 'Chitewel Ejiofor' ]
  };

  const recommendedMovies = [
    {
      name: 'Movie',  id: 1,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    },
    {
      name: 'Movie', id: 2,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    },
    {
      name: 'Some novie with absrudhlt long name that i just made up', id: 3,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    },
    {
      name: 'Movie', id: 4,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    },
    {
      name: 'Movie', id: 5,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    },
    {
      name: 'Movie',  id: 6,
      poster: 'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669',
      duration: 120
    }
  ];

  function formatDuration(num: number) {
    const hours = Math.floor(num / 60)
    return hours ? (`${hours} hours ${num % 60} minutes`) : (`${num % 60} minutes`)
  }

  return (
    <div>
      <Header/>
      <div className="grid grid-cols-3 h-[90vh]">
        <div className="flex flex-col col-span-2 h-full">
          <div className="flex flex-row p-4 border-solid border-r-4 border-slate-900 bg-slate-300 h-full w-full">
            <div className="p-4 flex-shrink-0 h-full w-2/5">
              <img className="h-full w-full" src={movie.poster} alt="Poster" />
            </div>
            <div className="flex flex-col p-4">
              <p className="text-2xl font-bold">{movie.movie}</p>
              <p>{movie.description}</p>
              <p className="mt-auto"><b>Duration:</b> {formatDuration(movie.duration)}</p>
              <p className="text-xl"><b>Director:</b> {movie.director}</p>
              <p className="text-xl"><b>Actors:</b> {movie.actors.map((actor) => `${actor}, `)}</p>
            </div>
          </div>

        </div>
        <div className="h-full">
          <p className="text-2xl text-center font-bold">Recommended movies</p>
          <div className="flex flex-row flex-wrap p-4 fle">
            {recommendedMovies.map((movie) => <MovieCard key={movie.id} {...movie} mode={MovieCardMode.Recommend}/>)}
          </div>
        </div>

      </div>
    </div>
  );
};

export default Movie;
