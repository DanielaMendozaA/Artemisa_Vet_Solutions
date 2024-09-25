import { IUser } from "@/models/interfaces";
import { emptyUserState } from "@/state/redux/states";
import { jwtDecode } from "jwt-decode";
import { useNavigate, Outlet, Navigate } from "react-router-dom"

interface IProps {
  privateValidation?: boolean;
  isForAuth?: boolean,
  isAdminValidation?: boolean;
}
interface IToken {
  email: string;
  exp: number;
  iat: number;
  id: string;
  role: string;
  permissions: string[];
}


const Guard = ({ isForAuth, privateValidation, isAdminValidation }: IProps) => {
  const navigate = useNavigate();
  const token = sessionStorage.getItem('token');

  if (token && token !== '') {
    const decodedToken: IToken = jwtDecode(token);
    if (decodedToken.exp * 1000 < new Date().getTime()) {
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('user');
      console.log(decodedToken);
      return <Navigate to={'/login'} />;
    }
  }

  if(isForAuth) {
    if( token && sessionStorage.getItem('user') !== null) {
      return <Navigate to={'/'} />;
    }
    else {
    return <Outlet />;
    }
  }
  else if(privateValidation) {
    if(isAdminValidation) {
      if(token && sessionStorage.getItem('user') !== null) {
        const user: IUser = JSON.parse(sessionStorage.getItem('user') as string);
        if(user.role === 'admin') {
          return <Outlet />;
        }
        else {
          return <Navigate to= {'/login'}/>;
        }
      }
      else {
        return <Navigate to= {'/login'}/>;
      }
    }
    else if(token && sessionStorage.getItem('user') !== null) {
      return <Outlet />;
    }
    else {
      return <Navigate to= {'/login'}/>;
    }
  }
};

export default Guard;