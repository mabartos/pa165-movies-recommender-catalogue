import Header from './Header';
import MovieCard from './MovieCard';

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
      name: 'Movie', id: 3,
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


  return (
    <div className="h-screen">
      <Header/>
      <div className="grid grid-cols-3 h-[90%]">
        <div className="flex flex-col col-span-2 h-full">
          <div className="flex flex-row p-4 border-solid border-b-4 border-r-4 border-slate-900 bg-slate-300">
            <div className="max-h-96 max-w-[18rem] p-4 flex-shrink-0">
              <img className="h-full" src={movie.poster} alt="Poster"/>
            </div>
            <div className="flex flex-col p-4">
              <p className="text-2xl font-bold">{movie.movie}</p>
              <p>{movie.description}</p>
              <p className="mt-auto text-xl"><b>Director:</b> {movie.director}</p>
              <p className="text-xl"><b>Actors:</b> {movie.actors.map((actor) => `${actor}, `)}</p>
            </div>
          </div>
          <div className="h-full flex flex-row p-4 border-solid border-b-d border-r-4 h-full">
            {recommendedMovies.map((movie) => <MovieCard key={movie.id} {...movie}/>)}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Movie;
