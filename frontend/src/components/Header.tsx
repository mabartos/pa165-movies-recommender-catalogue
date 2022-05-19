import { Link } from 'react-router-dom';

export const Header = () => (
  <header className="flex h-[10%] bg-blue-600 justify-center items-center">
    <Link to="/pa165/">
      <p className="text-2xl">PA165 Movie Recommender Catalogue</p>
    </Link>
  </header>
);

export default Header;
