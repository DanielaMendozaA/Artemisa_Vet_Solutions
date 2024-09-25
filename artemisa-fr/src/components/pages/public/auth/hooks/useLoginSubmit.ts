
import { AuthService } from "@/services/auth.service";
import { setUser } from "@/state/redux/states";
import { jwtDecode } from 'jwt-decode';
import { useDispatch } from "react-redux";

type IProps = {
  email: string;
  password: string;
  dispatch: ReturnType<typeof useDispatch>;
  navigate?: (path: string) => void;
}

export const useLoginSubmit = async ({ email, password, dispatch, navigate }: IProps): Promise<any> => {
  try {
    const response = await AuthService.login({ email, password });
    console.log("Respuesta del servidor:", response);
    storeToken(response.data.token);
    const user = {
      name: response.data.name,
      email: response.data.email,
      id: response.data.id,
      role: response.data.role
    }
    dispatch(setUser(user));
    if (navigate) {
      user.role === 'admin' ? navigate('/') : navigate('/');
    }
    return response.statusCode;

  }
  catch (error: any) {
    console.error("Error en la petición:", error.message);
    return error.message;
  }
}

const storeToken = (token: string) => {
  sessionStorage.setItem("token", token);
  const decodedToken = jwtDecode(token);
  sessionStorage.setItem("user", JSON.stringify(decodedToken));
}