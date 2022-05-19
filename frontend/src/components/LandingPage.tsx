import useSWR from 'swr';
import Header from './Header';
import MovieCard from './MovieCard';
import fetcher from '../models/fetcher';
import { MovieCardMode } from '../models/types';

export const LandingPage = () => {
  const { data, error } = useSWR('movies', fetcher);

  if (error) return <div>failed to load</div>
  if (!data) return <div>loading...</div>
  console.log(data)

  return (
    <div className="h-screen">
      <Header/>
      <main className="flex flex-wrap p-20">
        <MovieCard name={'Movie'} id={1}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
        <MovieCard name={'Movie'} id={2}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
        <MovieCard name={'Movie'} id={3}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
        <MovieCard name={'Movie'} id={4}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
        <MovieCard name={'Movie'} id={4}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
        <MovieCard name={'Movie'} id={5}
                   poster={'https://cdn.shopify.com/s/files/1/0057/3728/3618/products/0ef9608d2219e0695a1b79af254f6e44_480x.progressive.jpg?v=1573572669'}
                   duration={120} mode={MovieCardMode.LandingPage}/>
      </main>
    </div>
  );
};

export default LandingPage;
