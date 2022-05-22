import Header from './Header';
import MovieCard from './MovieCard';
import { MovieCardMode } from '../models/types';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import instance from '../models/axios';
import useSWR from 'swr';
import fetcher from '../models/fetcher';
import { getUser } from '../services/auth';
import formatDuration from '../services/formatDuration';

export const Movie = () => {
  const [ showReviews, setShowReviews ] = useState<boolean>(false);
  const changeShowStatus = () => {
    setShowReviews((prevState => !prevState));
  };

  const { register, handleSubmit, getValues, watch } = useForm();
  watch();

  const sendReview = async (data: any) => {
    const headers = {
      'Content-Type': 'application/json'
    };
    const reviewData = {
      ...data,
      movieId: id
    };
    console.log(reviewData);
    // await instance.post('/reviews', reviewData, { headers });
    alert('Review created');
  };

  const { id } = useParams();
  const { data: movie, error: movieError } = useSWR(`movies/${id}`, fetcher)
  const { data: reviews, error: reviewError } = useSWR(`reviews/search/${id}`, fetcher)
  const { data: recommended, error: recommendedError } = useSWR(`movies/${id}/recommended`, fetcher)

  if (movieError) return <div>failed to load</div>;
  if (!movie) return <div>loading...</div>;

  if (reviewError) return <div>failed to load</div>;
  if (!reviews) return <div>loading...</div>;

  if (recommendedError) return <div>failed to load</div>;
  if (!recommended) return <div>loading...</div>;

  const recommendedMovies = recommended.slice(0, 5)

  return (
    <div>
      <Header/>
      <div className="grid grid-cols-3 h-[90vh]">
        <div className="flex flex-col col-span-2 h-full">
          <div className="flex flex-row p-4 border-solid border-r-4 border-slate-900 bg-slate-300 h-full w-full">
            <div className="p-4 flex-shrink-0 h-full w-2/5">
              <img className="h-full w-full" src={movie.poster} alt="Poster"/>
            </div>
            <div className="flex flex-col p-4">
              <p className="text-2xl font-bold">{movie.name}</p>
              <p>{movie.description}</p>
              <p className="mt-auto"><b>Duration:</b> {formatDuration(movie.duration)}</p>
              <p className="text-xl"><b>Director:</b> {movie.director.name}</p>
              <button className="w-max p-4 self-center bg-blue-600 border-2 rounded-3xl border-slate-900"
                      onClick={changeShowStatus}>
                {showReviews ? 'Show recommended movies' : 'Show reviews'}
              </button>
            </div>
          </div>

        </div>
        <div className={`h-full ${showReviews && 'hidden'}`}>
          <p className="text-2xl text-center font-bold">Recommended movies</p>
          <div className="flex flex-row flex-wrap p-4">
            {recommendedMovies.map((movie: any) => <MovieCard key={movie.id} {...movie} mode={MovieCardMode.Recommend}/>)}
          </div>
        </div>
        <div className={`h-full flex flex-col ${showReviews || 'hidden'}`}>
          <p className="text-2xl text-center font-bold">Reviews</p>
          <div className="">
            //TODO After get reviews

          </div>
          {getUser() &&
            <form
              onSubmit={handleSubmit(sendReview)}
              className="flex flex-col p-2 m-2 mt-auto bg-slate-300 border-4 rounded-3xl border-slate-900"
            >
              <span className="text-2xl text-center font-bold">Write your review</span>
              <label className="flex justify-between">
                <span className="font-bold">Script: {getValues('script')}</span>
                <input type="range" min={1} max={10} {...register('script', { required: true })}/>
              </label>
              <label className="flex justify-between">
                <span className="font-bold">Idea: {getValues('idea')}</span>
                <input type="range" min={1} max={10} {...register('idea', { required: true })}/>
              </label>
              <label className="flex justify-between">
                <span className="font-bold">Visual edits: {getValues('visualsEdit')}</span>
                <input type="range" min={1} max={10} {...register('visualsEdit', { required: true })}/>
              </label>
              <label className="flex justify-between">
                <span className="font-bold">Music: {getValues('music')}</span>
                <input type="range" min={1} max={10} {...register('music', { required: true })}/>
              </label>
              <label className="flex justify-between">
                <span className="font-bold">Acting: {getValues('acting')}</span>
                <input type="range" min={1} max={10} {...register('acting', { required: true })}/>
              </label>
              <label>
              <textarea
                className="w-full border-2 border-slate-900"
                {...register('text', { required: true, minLength: 50 })}
                placeholder={"At least 50 characters."}
              />
              </label>
              <button type="submit" className="w-max p-2 self-center bg-blue-600 border-2 rounded-3xl border-slate-900">
                Send review
              </button>
            </form>
          }
        </div>

      </div>
    </div>
  );
};

export default Movie;
