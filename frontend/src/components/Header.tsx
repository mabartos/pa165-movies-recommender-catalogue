import { Link } from 'react-router-dom';
import { getUser, logout } from '../services/auth';
import Login from '../assets/login.svg'
import Logout from '../assets/logout.svg'

function onClick() {
  logout();
  window.location.reload();
}

export const Header = () => {
  const user = getUser();

  return (
    <header className="flex h-[10vh] bg-blue-600 items-center">
      <Link to="/pa165/" className="ml-auto">
        <p className="text-2xl">PA165 Movie Recommender Catalogue</p>
      </Link>
      {user === null ?
        <Link to="/pa165/login" className="ml-auto h-[50%] px-4">
          <img className="h-full" src={Login} alt="Login"/>
        </Link>
        :
        <button className="ml-auto h-[50%] px-4" onClick={onClick}>
          <img className="h-full" src={Logout} alt="Login"/>
        </button>
      }
    </header>
  );
}

export default Header;
